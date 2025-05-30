package lt.viko.eif.madamkevicius.expansesapi.model_assembler;

import io.micrometer.common.lang.NonNull;
import lt.viko.eif.madamkevicius.expansesapi.controller.ExpenseController;
import lt.viko.eif.madamkevicius.expansesapi.controller.AuthController;
import lt.viko.eif.madamkevicius.expansesapi.model.dto.ExpenseDTO;
import lt.viko.eif.madamkevicius.expansesapi.model.dto.PersonDTO;
import lt.viko.eif.madamkevicius.expansesapi.model.dto.UpdateExpenseDTO;
import lt.viko.eif.madamkevicius.expansesapi.model.dto.AuthResponseDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


@Component
public class AuthModelAssembler implements RepresentationModelAssembler<AuthResponseDTO, EntityModel<AuthResponseDTO>> {

    @NonNull
    @Override
    public EntityModel<AuthResponseDTO> toModel(@NonNull AuthResponseDTO jwtToken) {
        return EntityModel.of(jwtToken,
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
                        .withType("DELETE")
        );
    }

    public EntityModel<AuthResponseDTO> toRegisterModel(AuthResponseDTO jwtToken) {
        EntityModel<AuthResponseDTO> registerModel = EntityModel.of(jwtToken);

        registerModel.add(linkTo(methodOn(AuthController.class).register(new PersonDTO()))
                        .withSelfRel().withTitle("Register").withType("POST"));

        registerModel.add(toModel(jwtToken).getLinks());

        return registerModel;

    }

    public EntityModel<AuthResponseDTO> toLoginModel(AuthResponseDTO jwtToken) {
        EntityModel<AuthResponseDTO> loginModel = EntityModel.of(jwtToken);

        loginModel.add(linkTo(methodOn(AuthController.class)
                        .login(new PersonDTO())).withSelfRel().withTitle("Login").withType("POST"));

        loginModel.add(toModel(jwtToken).getLinks());

        return loginModel;
    }
}

