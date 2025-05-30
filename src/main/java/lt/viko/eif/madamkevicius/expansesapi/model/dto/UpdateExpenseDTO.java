package lt.viko.eif.madamkevicius.expansesapi.model.dto;

public class UpdateExpenseDTO {
    private String oldDescription;

    private String newDescription;

    private double newAmount;

    public UpdateExpenseDTO() {
    }

    public String getOldDescription() {
        return oldDescription;
    }

    public void setOldDescription(String oldDescription) {
        this.oldDescription = oldDescription;
    }

    public String getNewDescription() {
        return newDescription;
    }

    public void setNewDescription(String newDescription) {
        this.newDescription = newDescription;
    }

    public double getNewAmount() {
        return newAmount;
    }

    public void setNewAmount(double newAmount) {
        this.newAmount = newAmount;
    }
}
