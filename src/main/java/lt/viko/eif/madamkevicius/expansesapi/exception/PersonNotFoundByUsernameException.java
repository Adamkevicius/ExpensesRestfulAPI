package lt.viko.eif.madamkevicius.expansesapi.exception;

public class PersonNotFoundByUsernameException extends RuntimeException {
    public PersonNotFoundByUsernameException(String username) {
        super("Person with username " + username + " not found.");
    }
}
