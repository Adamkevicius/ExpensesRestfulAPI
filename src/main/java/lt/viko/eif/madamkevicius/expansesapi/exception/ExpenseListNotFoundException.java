package lt.viko.eif.madamkevicius.expansesapi.exception;

public class ExpenseListNotFoundException extends RuntimeException {
    public ExpenseListNotFoundException(String message) {
        super(message);
    }
}
