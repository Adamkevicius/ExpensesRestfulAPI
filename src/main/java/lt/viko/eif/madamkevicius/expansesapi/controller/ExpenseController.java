package lt.viko.eif.madamkevicius.expansesapi.controller;

import lt.viko.eif.madamkevicius.expansesapi.model.dto.ExpenseDTO;
import lt.viko.eif.madamkevicius.expansesapi.model.dto.MonthlyExpenseDTO;
import lt.viko.eif.madamkevicius.expansesapi.model.dto.UpdateExpenseDTO;
import lt.viko.eif.madamkevicius.expansesapi.model.dto.DeleteResponseDTO;
import lt.viko.eif.madamkevicius.expansesapi.model_assembler.ExpenseModelAssembler;
import lt.viko.eif.madamkevicius.expansesapi.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    private final ExpenseModelAssembler expenseModelAssembler;

    @Autowired
    public ExpenseController(ExpenseService expenseService, ExpenseModelAssembler expenseModelAssembler) {
        this.expenseService = expenseService;
        this.expenseModelAssembler = expenseModelAssembler;
    }

    @PostMapping
    public EntityModel<ExpenseDTO> create(@RequestBody ExpenseDTO expenseRequest) {
        ExpenseDTO createdExpense = expenseService.addExpense(expenseRequest);

        return expenseModelAssembler.toCreateModel(createdExpense);
    }

    @Cacheable(cacheNames = "expenses")
    @GetMapping
    public CollectionModel<EntityModel<ExpenseDTO>> getAll() {
        List<EntityModel<ExpenseDTO>> expenses = expenseService.getExpenses().stream()
                .map(expenseModelAssembler::toModel).toList();

        return CollectionModel
                .of(expenses, linkTo(methodOn(ExpenseController.class)
                        .getAll()).withSelfRel().withTitle("Get all expenses").withType("GET"));
    }

    @GetMapping("/{month}")
    public CollectionModel<EntityModel<MonthlyExpenseDTO>> getMonthly(@PathVariable String month) {
        List<MonthlyExpenseDTO> monthlyExpenses = expenseService.getMonthlyExpenses(month);

        return expenseModelAssembler.toMonthlyModel(monthlyExpenses, month);
    }

    @PutMapping
    public EntityModel<UpdateExpenseDTO> update(@RequestBody UpdateExpenseDTO updateRequest) {
        return expenseModelAssembler.toUpdateModel(expenseService.updateExpense(updateRequest));
    }

    @DeleteMapping
    public EntityModel<DeleteResponseDTO> deleteAll() {
        String result = expenseService.deleteAllExpenses();

        return expenseModelAssembler.toDeleteAllModel(new DeleteResponseDTO(result));
    }

    @Transactional
    @DeleteMapping("/{description}")
    public EntityModel<DeleteResponseDTO> deleteByDescription(@PathVariable String description) {
        String result = expenseService.deleteByDescription(description);

        return expenseModelAssembler.toDeleteByDescriptionModel(new DeleteResponseDTO(result), description);
    }
}
