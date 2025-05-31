package lt.viko.eif.madamkevicius.expansesapi.service;

import lt.viko.eif.madamkevicius.expansesapi.model.dto.ExpenseDTO;
import lt.viko.eif.madamkevicius.expansesapi.model.dto.MonthlyExpenseDTO;
import lt.viko.eif.madamkevicius.expansesapi.model.dto.UpdateExpenseDTO;
import lt.viko.eif.madamkevicius.expansesapi.exception.ExpenseListIsEmptyException;
import lt.viko.eif.madamkevicius.expansesapi.exception.ExpenseTypeNotFoundException;
import lt.viko.eif.madamkevicius.expansesapi.model.entity.Expense;
import lt.viko.eif.madamkevicius.expansesapi.model.entity.MonthlyExpense;
import lt.viko.eif.madamkevicius.expansesapi.repo.ExpenseRepo;
import lt.viko.eif.madamkevicius.expansesapi.repo.ExpenseTypeRepo;
import lt.viko.eif.madamkevicius.expansesapi.repo.MonthlyExpenseRepo;
import lt.viko.eif.madamkevicius.expansesapi.repo.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseRepo expenseRepo;

    private final ExpenseTypeRepo expenseTypeRepo;

    private final PersonRepo personRepo;

    private final MapperService mapperService;

    private final MonthlyExpenseRepo monthlyExpenseRepo;


    @Autowired
    public ExpenseService(ExpenseRepo expenseRepo, ExpenseTypeRepo expenseTypeRepo, PersonRepo personRepo, MapperService mapperService, MonthlyExpenseRepo monthlyExpenseRepo) {
        this.expenseRepo = expenseRepo;
        this.expenseTypeRepo = expenseTypeRepo;
        this.personRepo = personRepo;
        this.mapperService = mapperService;
        this.monthlyExpenseRepo = monthlyExpenseRepo;
    }

    public ExpenseDTO addExpense(ExpenseDTO expenseDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String personUsername = authentication.getName();

        Expense newExpense = new Expense();
        newExpense.setDescription(expenseDTO.getDescription());
        newExpense.setCreationDate(LocalDateTime.now());
        newExpense.setAmount(expenseDTO.getAmount());
        newExpense.setPerson(personRepo.findByUsername(personUsername));
        newExpense.setExpenseType(
                expenseTypeRepo.findExpenseTypeByType(expenseDTO.getExpenseType())
                        .orElseThrow(() -> new ExpenseTypeNotFoundException(expenseDTO.getExpenseType()))
        );
        expenseRepo.save(newExpense);

        return expenseDTO;
    }

    public List<ExpenseDTO> getExpenses() {
        List<Expense> expensesList = expenseRepo.findAll();

        if(expensesList.isEmpty()) {
            throw new ExpenseListIsEmptyException("Expenses were not found");
        }

        List<ExpenseDTO> expenses = new ArrayList<>();

        for (Expense expense : expensesList) {
            expenses.add(mapperService.toExpenseDto(expense));
        }

        return expenses;
    }

    public List<MonthlyExpenseDTO> getMonthlyExpenses(String requestMonth) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String personUsername = authentication.getName();

        int year = LocalDate.now().getYear();
        int month = Month.valueOf(requestMonth.toUpperCase()).getValue();
        int personId = personRepo.findByUsername(personUsername).getId();

        List<Expense> expenseList = expenseRepo.findAllByMonthAndYear(
                month, year, personId
        );

        if(expenseList.isEmpty()) {
            throw new ExpenseListIsEmptyException("Expenses for the month " + requestMonth + " were not found");
        }

        List<MonthlyExpense> monthlyExpensesList = monthlyExpenseRepo.findAllByYearAndMonthAndPersonId(
                year, month, personId
        );

        return mapperService.toMonthlyExpenseDto(monthlyExpensesList, expenseList);
    }

    public UpdateExpenseDTO updateExpense(UpdateExpenseDTO updateExpenseDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String personUsername = authentication.getName();

        Expense expense = expenseRepo.findByDescriptionAndPersonId(
                        updateExpenseDTO.getOldDescription(), personRepo.findByUsername(personUsername).getId())
                .orElseThrow(() -> new ExpenseTypeNotFoundException(updateExpenseDTO.getOldDescription()));

        if (!updateExpenseDTO.getOldDescription().equals(expense.getDescription())) {
            expense.setDescription(updateExpenseDTO.getNewDescription());
        }

        if (updateExpenseDTO.getNewAmount() != 0 || updateExpenseDTO.getNewAmount() != expense.getAmount()) {
            expense.setAmount(updateExpenseDTO.getNewAmount());
        }

        expenseRepo.save(expense);

        return updateExpenseDTO;
    }

    public String deleteAllExpenses() {
        expenseRepo.deleteAll();

        if (expenseRepo.findAll().isEmpty()) {
            return "All expenses deleted successfully";
        }

        return "Error while deleting expenses";
    }

    public String deleteByDescription(String description) {
        if (!expenseRepo.existsByDescription(description)) {
            return "Expense with description " + description + " does not exist";
        }

        expenseRepo.deleteByDescription(description);

        return "Expense with description " + description + " deleted successfully";
    }
}
