package lt.viko.eif.madamkevicius.expansesapi.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "person")
public class Person extends BaseEntity {
    private String username;

    private String password;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Expense> expenseList;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MonthlyExpense> monthlyExpenseList;

    public Person() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Expense> getExpenseList() {
        return expenseList;
    }

    public void setExpenseList(List<Expense> expenseList) {
        this.expenseList = expenseList;
    }

    public List<MonthlyExpense> getMonthlyExpenseList() {
        return monthlyExpenseList;
    }

    public void setMonthlyExpenseList(List<MonthlyExpense> monthlyExpenseList) {
        this.monthlyExpenseList = monthlyExpenseList;
    }

    @Override
    public String toString() {
        return "Person{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", expenseList=" + expenseList +
                ", monthlyExpenseList=" + monthlyExpenseList +
                '}';
    }
}
