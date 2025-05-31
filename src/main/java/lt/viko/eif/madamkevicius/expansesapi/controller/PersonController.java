package lt.viko.eif.madamkevicius.expansesapi.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lt.viko.eif.madamkevicius.expansesapi.exception.PersonNotFoundByIdException;
import lt.viko.eif.madamkevicius.expansesapi.model.entity.Person;
import lt.viko.eif.madamkevicius.expansesapi.model_assembler.PersonAssembler;
import lt.viko.eif.madamkevicius.expansesapi.repo.PersonRepo;
import lt.viko.eif.madamkevicius.expansesapi.service.PersonService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "Person operations", description = "Endpoint for managing person records")
@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonRepo personRepo;

    private final PersonService personService;

    private final PersonAssembler personAssembler;

    public PersonController(PersonRepo personRepo, PersonService personService, PersonAssembler personAssembler) {
        this.personRepo = personRepo;
        this.personService = personService;
        this.personAssembler = personAssembler;
    }

    @Tag(name = "get", description = "Get all users")
    @GetMapping
    public CollectionModel<EntityModel<Person>> getAll() {
        return personAssembler.toCollectionModel(personService.getAll());
    }

    @Tag(name = "get-by-id", description = "Get user by id")
    @GetMapping("/{id}")
    public EntityModel<Person> getById(@PathVariable int id) {
        return personAssembler
                .toGetByIdModel(personRepo.findById(id).orElseThrow(() -> new PersonNotFoundByIdException(id)));
    }

    @Tag(name = "get-by-username", description = "Get user by username")
    @GetMapping("/username/{username}")
    public EntityModel<Person> getByUsername(@PathVariable String username) {
        return personAssembler.toGetByUsernameModel(personService.getByUsername(username));
    }
}
