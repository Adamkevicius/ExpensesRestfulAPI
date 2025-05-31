package lt.viko.eif.madamkevicius.expansesapi.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lt.viko.eif.madamkevicius.expansesapi.model.dto.PersonDTO;
import lt.viko.eif.madamkevicius.expansesapi.model.dto.AuthResponseDTO;
import lt.viko.eif.madamkevicius.expansesapi.model_assembler.AuthModelAssembler;
import lt.viko.eif.madamkevicius.expansesapi.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Authentication operations", description = "Endpoint for user authentication")
@RestController
@RequestMapping("/user")
public class AuthController {

    private final AuthService personService;

    private final AuthModelAssembler personModelAssembler;


    @Autowired
    public AuthController(AuthService userService, AuthModelAssembler personModelAssembler) {
        this.personService = userService;
        this.personModelAssembler = personModelAssembler;
    }

    @Tag(name = "register", description = "Registers a new user")
    @PostMapping("/register")
    public EntityModel<AuthResponseDTO> register(@RequestBody PersonDTO personDTO) {
        String jwtTokenForCreatedPerson = personService.register(personDTO);

        return (personModelAssembler.toRegisterModel(new AuthResponseDTO(jwtTokenForCreatedPerson)));
    }

    @Tag(name = "login", description = "Logs in as an existing user")
    @PostMapping("/login")
    public EntityModel<AuthResponseDTO> login(@RequestBody PersonDTO personDTO) {
        String jwtTokenForVerifiedPerson = (personService.verifyUser(personDTO));

        return (personModelAssembler.toLoginModel(new AuthResponseDTO(jwtTokenForVerifiedPerson)));
    }
}
