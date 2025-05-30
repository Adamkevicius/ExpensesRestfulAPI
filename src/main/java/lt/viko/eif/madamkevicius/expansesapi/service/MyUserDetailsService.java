package lt.viko.eif.madamkevicius.expansesapi.service;

import lt.viko.eif.madamkevicius.expansesapi.model.entity.Person;
import lt.viko.eif.madamkevicius.expansesapi.model.PersonPrincipal;
import lt.viko.eif.madamkevicius.expansesapi.repo.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final PersonRepo userRepo;

    @Autowired
    public MyUserDetailsService(PersonRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = userRepo.findByUsername(username);

        if(person == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return new PersonPrincipal(person);
    }
}
