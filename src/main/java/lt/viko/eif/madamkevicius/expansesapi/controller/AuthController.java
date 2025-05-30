package lt.viko.eif.madamkevicius.expansesapi.controller;

import lt.viko.eif.madamkevicius.expansesapi.model.dto.PersonDTO;
import lt.viko.eif.madamkevicius.expansesapi.model.dto.AuthResponseDTO;
import lt.viko.eif.madamkevicius.expansesapi.model_assembler.AuthModelAssembler;
import lt.viko.eif.madamkevicius.expansesapi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class AuthController {

    private final PersonService personService;

    private final AuthModelAssembler personModelAssembler;


    @Autowired
    public AuthController(PersonService userService, AuthModelAssembler personModelAssembler) {
        this.personService = userService;
        this.personModelAssembler = personModelAssembler;
    }

    @PostMapping("/register")
    public EntityModel<AuthResponseDTO> register(@RequestBody PersonDTO personDTO) {
        String jwtTokenForCreatedPerson = personService.register(personDTO);

        return (personModelAssembler.toRegisterModel(new AuthResponseDTO(jwtTokenForCreatedPerson)));
    }

    @PostMapping("/login")
    public EntityModel<AuthResponseDTO> login(@RequestBody PersonDTO personDTO) {
        String jwtTokenForVerifiedPerson = (personService.verifyUser(personDTO));

        return (personModelAssembler.toLoginModel(new AuthResponseDTO(jwtTokenForVerifiedPerson)));
    }
}
