package lt.viko.eif.madamkevicius.expansesapi.model.dto;

public class ExpenseDTO {
    private String expenseType;

    private String description;

    private double amount;

    public ExpenseDTO() {
    }

    public String getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(String expenseType) {
        this.expenseType = expenseType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "ExpenseDTO{" +
                "expenseType='" + expenseType + '\'' +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                '}';
    }
}
