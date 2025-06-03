package lt.viko.eif.madamkevicius.expansesapi.model_assembler;

import io.micrometer.common.lang.NonNull;
import lt.viko.eif.madamkevicius.expansesapi.controller.ExpenseController;
import lt.viko.eif.madamkevicius.expansesapi.controller.PersonController;
import lt.viko.eif.madamkevicius.expansesapi.model.dto.ExpenseDTO;
import lt.viko.eif.madamkevicius.expansesapi.model.dto.UpdateExpenseDTO;
import lt.viko.eif.madamkevicius.expansesapi.model.entity.Person;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.StreamSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class PersonAssembler implements RepresentationModelAssembler<Person, EntityModel<Person>> {
    @NonNull
    @Override
    public EntityModel<Person> toModel(@NonNull Person person) {
        return EntityModel.of(person,
                linkTo(methodOn(PersonController.class)
                        .getById(person.getId()))
                        .withRel("get-by-id").withTitle("Get person by id").withType("GET"),
                linkTo(methodOn(PersonController.class)
                        .getByUsername(person.getUsername()))
                        .withRel("get-by-username").withTitle("Get person by username").withType("GET"),
                linkTo(methodOn(ExpenseController.class)
                        .create(new ExpenseDTO())).withRel("create").withTitle("Create expense").withType("POST")
                , linkTo(methodOn(ExpenseController.class)
                        .getAll()).withRel("all").withTitle("Get all expenses").withType("GET"),
                linkTo(methodOn(ExpenseController.class)
                        .getMonthly("{month}")).withRel("get-monthly")
                        .withTitle("Get expenses by month").withType("GET")
                , linkTo(methodOn(ExpenseController.class)
                        .update(new UpdateExpenseDTO())).withRel("update").withTitle("Update expense").withType("PUT")
                , linkTo(methodOn(ExpenseController.class)
                        .deleteByDescription("{description}")).withRel("delete")
                        .withTitle("Delete expense by description").withType("DELETE")
                ,linkTo(methodOn(ExpenseController.class)
                        .deleteAll()).withRel("delete-by-description").withTitle("Delete expense by description")
                        .withType("DELETE"));
    }

    @NonNull
    @Override
    public CollectionModel<EntityModel<Person>> toCollectionModel(@NonNull Iterable<? extends Person> person) {
        List<EntityModel<Person>> people =
                StreamSupport.stream(person.spliterator(), false).map(this::toModel).toList();

        return CollectionModel.of(people,
                linkTo(methodOn(PersonController.class)
                        .getAll()).withSelfRel().withTitle("Get-all-people").withType("GET"));
    }

    public EntityModel<Person> toGetByIdModel(Person person) {
        EntityModel<Person> getByIdModel = EntityModel.of(person);

        getByIdModel.add(linkTo(methodOn(PersonController.class).getById(person.getId()))
                .withSelfRel().withTitle("Get person by id").withType("GET"));

        getByIdModel.add(
                linkTo(methodOn(PersonController.class)
                        .getByUsername(person.getUsername()))
                        .withRel("get-by-username").withTitle("Get person by username").withType("GET"),
                linkTo(methodOn(ExpenseController.class)
                        .create(new ExpenseDTO())).withRel("create").withTitle("Create expense").withType("POST")
                , linkTo(methodOn(ExpenseController.class)
                        .getAll()).withRel("all").withTitle("Get all expenses").withType("GET"),
                linkTo(methodOn(ExpenseController.class)
                        .getMonthly("{month}")).withRel("get-monthly")
                        .withTitle("Get expenses by month").withType("GET")
                , linkTo(methodOn(ExpenseController.class)
                        .update(new UpdateExpenseDTO())).withRel("update").withTitle("Update expense").withType("PUT")
                , linkTo(methodOn(ExpenseController.class)
                        .deleteByDescription("{description}")).withRel("delete")
                        .withTitle("Delete expense by description").withType("DELETE")
                ,linkTo(methodOn(ExpenseController.class)
                        .deleteAll()).withRel("delete-by-description").withTitle("Delete expense by description")
                        .withType("DELETE"));

        return getByIdModel;
    }

    public EntityModel<Person> toGetByUsernameModel(Person person) {
        EntityModel<Person> getByUsernameModel = EntityModel.of(person);

        getByUsernameModel.add(linkTo(methodOn(PersonController.class).getByUsername(person.getUsername()))
                .withSelfRel().withTitle("Get person by username").withType("GET"));

        getByUsernameModel.add(
                linkTo(methodOn(PersonController.class)
                        .getById(person.getId()))
                        .withRel("get-by-id").withTitle("Get person by id").withType("GET"),
                linkTo(methodOn(ExpenseController.class)
                        .create(new ExpenseDTO())).withRel("create").withTitle("Create expense").withType("POST")
                , linkTo(methodOn(ExpenseController.class)
                        .getAll()).withRel("all").withTitle("Get all expenses").withType("GET"),
                linkTo(methodOn(ExpenseController.class)
                        .getMonthly("{month}")).withRel("get-monthly")
                        .withTitle("Get expenses by month").withType("GET")
                , linkTo(methodOn(ExpenseController.class)
                        .update(new UpdateExpenseDTO())).withRel("update").withTitle("Update expense").withType("PUT")
                , linkTo(methodOn(ExpenseController.class)
                        .deleteByDescription("{description}")).withRel("delete")
                        .withTitle("Delete expense by description").withType("DELETE")
                ,linkTo(methodOn(ExpenseController.class)
                        .deleteAll()).withRel("delete-by-description").withTitle("Delete expense by description")
                        .withType("DELETE"));

        return getByUsernameModel;
    }

}
