package lt.viko.eif.madamkevicius.expansesapi.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lt.viko.eif.madamkevicius.expansesapi.model.dto.ExpenseDTO;
import lt.viko.eif.madamkevicius.expansesapi.model.dto.MonthlyExpenseDTO;
import lt.viko.eif.madamkevicius.expansesapi.model.dto.UpdateExpenseDTO;
import lt.viko.eif.madamkevicius.expansesapi.model.dto.DeleteResponseDTO;
import lt.viko.eif.madamkevicius.expansesapi.model_assembler.ExpenseModelAssembler;
import lt.viko.eif.madamkevicius.expansesapi.service.ExpenseService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Tag(name = "Expense operations", description = "Endpoint for managing expenses")
@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    private final ExpenseModelAssembler expenseModelAssembler;

    public ExpenseController(ExpenseService expenseService, ExpenseModelAssembler expenseModelAssembler) {
        this.expenseService = expenseService;
        this.expenseModelAssembler = expenseModelAssembler;
    }

    @Tag(name = "create", description = "Creates a new expense")
    @PostMapping
    public EntityModel<ExpenseDTO> create(@RequestBody ExpenseDTO expenseRequest) {
        ExpenseDTO createdExpense = expenseService.addExpense(expenseRequest);

        return expenseModelAssembler.toCreateModel(createdExpense);
    }

    @Tag(name = "find", description = "Finds all expenses")
    @Cacheable(cacheNames = "expenses")
    @GetMapping
    public CollectionModel<EntityModel<ExpenseDTO>> getAll() {
        List<EntityModel<ExpenseDTO>> expenses = expenseService.getExpenses().stream()
                .map(expenseModelAssembler::toModel).toList();

        return CollectionModel
                .of(expenses, linkTo(methodOn(ExpenseController.class)
                        .getAll()).withSelfRel().withTitle("Get all expenses").withType("GET"));
    }

    @Tag(name = "find-monthly", description = "Finds monthly expenses by month")
    @GetMapping("/{month}")
    public CollectionModel<EntityModel<MonthlyExpenseDTO>> getMonthly(@PathVariable String month) {
        List<MonthlyExpenseDTO> monthlyExpenses = expenseService.getMonthlyExpenses(month);

        return expenseModelAssembler.toMonthlyModel(monthlyExpenses, month);
    }

    @Tag(name = "update", description = "Updates an existing expense")
    @PutMapping
    public EntityModel<UpdateExpenseDTO> update(@RequestBody UpdateExpenseDTO updateRequest) {
        return expenseModelAssembler.toUpdateModel(expenseService.updateExpense(updateRequest));
    }

    @Tag(name = "delete-all", description = "Deletes all expenses")
    @DeleteMapping
    public EntityModel<DeleteResponseDTO> deleteAll() {
        String result = expenseService.deleteAllExpenses();

        return expenseModelAssembler.toDeleteAllModel(new DeleteResponseDTO(result));
    }

    @Tag(name = "delete-by-description", description = "Deletes an expense by description")
    @Transactional
    @DeleteMapping("/{description}")
    public EntityModel<DeleteResponseDTO> deleteByDescription(@PathVariable String description) {
        String result = expenseService.deleteByDescription(description);

        return expenseModelAssembler.toDeleteByDescriptionModel(new DeleteResponseDTO(result), description);
    }
}
