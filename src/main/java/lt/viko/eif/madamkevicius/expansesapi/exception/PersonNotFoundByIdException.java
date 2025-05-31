package lt.viko.eif.madamkevicius.expansesapi.exception;

public class PersonNotFoundByIdException extends RuntimeException {
    public PersonNotFoundByIdException(int id) {
        super("Person with ID " + id + " not found.");
    }
}
