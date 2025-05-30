package lt.viko.eif.madamkevicius.expansesapi.model_assembler;

import io.micrometer.common.lang.NonNull;
import lt.viko.eif.madamkevicius.expansesapi.controller.ExpenseController;
import lt.viko.eif.madamkevicius.expansesapi.model.dto.ExpenseDTO;
import lt.viko.eif.madamkevicius.expansesapi.model.dto.MonthlyExpenseDTO;
import lt.viko.eif.madamkevicius.expansesapi.model.dto.UpdateExpenseDTO;
import lt.viko.eif.madamkevicius.expansesapi.model.dto.DeleteResponseDTO;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ExpenseModelAssembler implements RepresentationModelAssembler<ExpenseDTO, EntityModel<ExpenseDTO>> {
    @NonNull
    @Override
    public EntityModel<ExpenseDTO> toModel(@NonNull ExpenseDTO expenseDTO) {
        return EntityModel.of(expenseDTO,
                linkTo(methodOn(ExpenseController.class)
                        .create(new ExpenseDTO())).withRel("create").withTitle("Create expense").withType("POST"),
                linkTo(methodOn(ExpenseController.class)
                        .getMonthly("{month}")).withRel("get-monthly")
                        .withTitle("Get expenses by month").withType("GET"),
                linkTo(methodOn(ExpenseController.class)
                        .update(new UpdateExpenseDTO())).withRel("update").withTitle("Update expense").withType("PUT"),
                linkTo(methodOn(ExpenseController.class)
                        .deleteAll()).withRel("delete")
                        .withTitle("Delete all expenses").withType("DELETE"),
                linkTo(methodOn(ExpenseController.class)
                        .deleteByDescription("{description}")).withRel("delete-by-description").withTitle("Delete expense by description")
                        .withType("DELETE"));
    }

    public EntityModel<ExpenseDTO> toCreateModel(ExpenseDTO expenseDTO) {
        EntityModel<ExpenseDTO> createModel = EntityModel.of(expenseDTO);

        createModel.add(linkTo(methodOn(ExpenseController.class)
                .create(new ExpenseDTO())).withSelfRel().withTitle("Create expense").withType("POST"));

        createModel.add(
                linkTo(methodOn(ExpenseController.class)
                        .getAll()).withRel("all").withTitle("Get all expenses").withType("GET"),
                linkTo(methodOn(ExpenseController.class)
                        .getMonthly("{month}")).withRel("get-monthly")
                        .withTitle("Get expenses by month").withType("GET"),
                linkTo(methodOn(ExpenseController.class)
                        .update(new UpdateExpenseDTO())).withRel("update").withTitle("Update expense").withType("PUT"),
                linkTo(methodOn(ExpenseController.class)
                        .deleteAll()).withRel("delete")
                        .withTitle("Delete all expenses").withType("DELETE"),
                linkTo(methodOn(ExpenseController.class)
                        .deleteByDescription("{description}")).withRel("delete-by-description").withTitle("Delete expense by description")
                        .withType("DELETE"));

        return createModel;
    }

    public CollectionModel<EntityModel<MonthlyExpenseDTO>> toMonthlyModel
            (List<MonthlyExpenseDTO> monthlyExpenses, String month) {
        List<EntityModel<MonthlyExpenseDTO>> monthlyExpenseModels = monthlyExpenses.stream()
                .map(expense -> EntityModel.of(expense,
                        linkTo(methodOn(ExpenseController.class)
                                .getMonthly(month)).withSelfRel()
                                .withTitle("Get monthly expenses").withType("GET")
                )).toList();

        return CollectionModel.of(
                monthlyExpenseModels,
                linkTo(methodOn(ExpenseController.class)
                        .create(new ExpenseDTO())).withRel("create")
                        .withTitle("Create expense").withType("POST"),
                linkTo(methodOn(ExpenseController.class)
                        .getAll()).withRel("all")
                        .withTitle("Get all expenses").withType("GET"),
                linkTo(methodOn(ExpenseController.class)
                        .update(new UpdateExpenseDTO())).withRel("update")
                        .withTitle("Update expense").withType("PUT"),
                linkTo(methodOn(ExpenseController.class)
                        .deleteAll()).withRel("delete")
                        .withTitle("Delete all expenses").withType("DELETE"),
                linkTo(methodOn(ExpenseController.class)
                        .deleteByDescription("example-description")).withRel("delete-by-description")
                        .withTitle("Delete expense by description").withType("DELETE")
        );
    }

    public EntityModel<UpdateExpenseDTO> toUpdateModel(UpdateExpenseDTO updateExpenseDTO) {
        EntityModel<UpdateExpenseDTO> updateModel = EntityModel.of(updateExpenseDTO);

        updateModel.add(linkTo(methodOn(ExpenseController.class).update(new UpdateExpenseDTO())).withSelfRel()
                .withTitle("Update expense").withType("PUT"));

        updateModel.add(
                linkTo(methodOn(ExpenseController.class)
                        .create(new ExpenseDTO())).withRel("create").withTitle("Create expense").withType("POST"),
                linkTo(methodOn(ExpenseController.class)
                        .getAll()).withRel("all").withTitle("Get all expenses").withType("GET"),
                linkTo(methodOn(ExpenseController.class)
                        .getMonthly("{month}")).withRel("get-monthly")
                        .withTitle("Get expenses by month").withType("GET"),
                linkTo(methodOn(ExpenseController.class)
                        .deleteAll()).withRel("delete")
                        .withTitle("Delete all expenses").withType("DELETE"),
                linkTo(methodOn(ExpenseController.class)
                        .deleteByDescription("{description}")).withRel("delete-by-description").withTitle("Delete expense by description")
                        .withType("DELETE"));

        return updateModel;
    }

    public EntityModel<DeleteResponseDTO> toDeleteAllModel(DeleteResponseDTO deleteResponse) {
        EntityModel<DeleteResponseDTO> deleteAllModel = EntityModel.of(deleteResponse);

        deleteAllModel.add(linkTo(methodOn(ExpenseController.class).deleteAll())
                .withSelfRel().withTitle("Delete all expenses").withType("DELETE"));

        deleteAllModel.add(
                linkTo(methodOn(ExpenseController.class)
                        .create(new ExpenseDTO())).withRel("create").withTitle("Create expense").withType("POST"),
                linkTo(methodOn(ExpenseController.class)
                        .getAll()).withRel("all").withTitle("Get all expenses").withType("GET"),
                linkTo(methodOn(ExpenseController.class)
                        .getMonthly("{month}")).withRel("get-monthly")
                        .withTitle("Get expenses by month").withType("GET"),
                linkTo(methodOn(ExpenseController.class)
                        .update(new UpdateExpenseDTO())).withRel("update").withTitle("Update expense").withType("PUT"),
                linkTo(methodOn(ExpenseController.class)
                        .deleteAll()).withRel("delete-by-description").withTitle("Delete expense by description")
                        .withType("DELETE"));

        return deleteAllModel;
    }

    public EntityModel<DeleteResponseDTO> toDeleteByDescriptionModel(DeleteResponseDTO deleteResponse, String description) {
        EntityModel<DeleteResponseDTO> deleteByDescriptionModel = EntityModel.of(deleteResponse);

        deleteByDescriptionModel.add(linkTo(methodOn(ExpenseController.class).deleteByDescription(description))
                .withSelfRel().withTitle("Delete expense by description").withType("DELETE"));

        deleteByDescriptionModel.add(
                linkTo(methodOn(ExpenseController.class)
                        .create(new ExpenseDTO())).withRel("create").withTitle("Create expense").withType("POST"),
                linkTo(methodOn(ExpenseController.class)
                        .getAll()).withRel("all").withTitle("Get all expenses").withType("GET"),
                linkTo(methodOn(ExpenseController.class)
                        .getMonthly("{month}")).withRel("get-monthly")
                        .withTitle("Get expenses by month").withType("GET"),
                linkTo(methodOn(ExpenseController.class)
                        .update(new UpdateExpenseDTO())).withRel("update").withTitle("Update expense").withType("PUT"),
                linkTo(methodOn(ExpenseController.class)
                        .deleteAll()).withRel("delete")
                        .withTitle("Delete all expenses").withType("DELETE"));

        return deleteByDescriptionModel;
    }
}
