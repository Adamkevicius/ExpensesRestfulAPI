package lt.viko.eif.madamkevicius.expansesapi.service;

import lt.viko.eif.madamkevicius.expansesapi.model.dto.ExpenseDTO;
import lt.viko.eif.madamkevicius.expansesapi.model.dto.MonthlyExpenseDTO;
import lt.viko.eif.madamkevicius.expansesapi.model.entity.Expense;
import lt.viko.eif.madamkevicius.expansesapi.model.entity.MonthlyExpense;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MapperService {
    public ExpenseDTO toExpenseDto(Expense expense) {
        ExpenseDTO expenseDTO = new ExpenseDTO();
        expenseDTO.setExpenseType(expense.getExpenseType().getType());
        expenseDTO.setDescription(expense.getDescription());
        expenseDTO.setAmount(expense.getAmount());
        return expenseDTO;
    }

    public List<ExpenseDTO> toExpenseDto(List<Expense> expenseList) {
        List<ExpenseDTO> expenseDTOSList = new ArrayList<>();
        for(Expense expense : expenseList) {
            expenseDTOSList.add(toExpenseDto(expense));
        }
        return expenseDTOSList;
    }

    public List<MonthlyExpenseDTO> toMonthlyExpenseDto(
            List<MonthlyExpense> monthlyExpenseList,
            List<Expense> expenseList) {
        return monthlyExpenseList.stream()
                .map(monthlyExpenses -> new MonthlyExpenseDTO(
                        monthlyExpenses.getYear(),
                        monthlyExpenses.getMonth(),
                        toExpenseDto(expenseList),
                        monthlyExpenses.getTotalAmount()
                )).toList();
    }
}
