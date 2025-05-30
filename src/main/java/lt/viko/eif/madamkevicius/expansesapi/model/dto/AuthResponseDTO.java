package lt.viko.eif.madamkevicius.expansesapi.model.dto;

public class AuthResponseDTO {

    private String jwtToken;

    public AuthResponseDTO(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
