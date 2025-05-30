package lt.viko.eif.madamkevicius.expansesapi.model.dto;

public class DeleteResponseDTO {
    private String message;

    public DeleteResponseDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
