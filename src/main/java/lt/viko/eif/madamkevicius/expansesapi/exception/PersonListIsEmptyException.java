package lt.viko.eif.madamkevicius.expansesapi.exception;

public class PersonListIsEmptyException extends RuntimeException {
    public PersonListIsEmptyException() {
        super("Person list is empty.");
    }
}
