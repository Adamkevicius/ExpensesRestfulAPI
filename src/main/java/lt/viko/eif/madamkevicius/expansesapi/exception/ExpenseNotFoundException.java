package lt.viko.eif.madamkevicius.expansesapi.exception;

public class ExpenseNotFoundException extends RuntimeException {
    public ExpenseNotFoundException(String description) {
        super("Expense with description " + description + " not found.");
    }
}
