package lt.viko.eif.madamkevicius.expansesapi.exception;

public class ExpenseTypeNotFoundException extends RuntimeException {
    public ExpenseTypeNotFoundException(String expenseType) {
        super("Expense type with type " + expenseType + " not found.");
    }
}
