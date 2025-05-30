package lt.viko.eif.madamkevicius.expansesapi.model.dto;

import java.util.List;

public class MonthlyExpenseDTO {
    private int year;

    private int month;

    private List<ExpenseDTO> expenseList;

    private double totalAmount;

    public MonthlyExpenseDTO(int year, int month, List<ExpenseDTO> expenseList, double totalAmount) {
        this.year = year;
        this.month = month;
        this.expenseList = expenseList;
        this.totalAmount = totalAmount;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public List<ExpenseDTO> getExpenseList() {
        return expenseList;
    }

    public void setExpenseList(List<ExpenseDTO> expenseList) {
        this.expenseList = expenseList;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "MonthlyExpenseDTO{" +
                "year=" + year +
                ", month=" + month +
                ", expenseList=" + expenseList +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
