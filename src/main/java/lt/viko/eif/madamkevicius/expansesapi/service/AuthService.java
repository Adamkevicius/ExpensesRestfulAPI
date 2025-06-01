package lt.viko.eif.madamkevicius.expansesapi.service;

import lt.viko.eif.madamkevicius.expansesapi.model.dto.PersonDTO;
import lt.viko.eif.madamkevicius.expansesapi.model.entity.Person;
import lt.viko.eif.madamkevicius.expansesapi.repo.PersonRepo;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final PersonRepo userRepo;

    private final BCryptPasswordEncoder encoder;

    private final AuthenticationManager authManager;

    private final JWTService jwtService;

    public AuthService(PersonRepo userRepo, AuthenticationManager authManager, JWTService jwtService) {
        this.userRepo = userRepo;
        this.authManager = authManager;
        this.jwtService = jwtService;
        this.encoder = new BCryptPasswordEncoder(12);
    }

    public String register(PersonDTO personDTO) {
        Person person = new Person();
        person.setUsername(personDTO.getUsername());
        person.setPassword(encoder.encode(personDTO.getPassword()));
        userRepo.save(person);
        return jwtService.generateToken(personDTO.getUsername());
    }

    public String verifyUser(PersonDTO personDTO) {
        Authentication auth = authManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(
                                personDTO.getUsername(), personDTO.getPassword())
                );

        if(auth.isAuthenticated()) {
            return jwtService.generateToken(
                    personDTO.getUsername()
            );
        }

        return "Failed to authenticate";
    }
}
