package lt.viko.eif.madamkevicius.expansesapi.exception;

public class ExpenseListIsEmptyException extends RuntimeException {
    public ExpenseListIsEmptyException(String message) {
        super(message);
    }
}
