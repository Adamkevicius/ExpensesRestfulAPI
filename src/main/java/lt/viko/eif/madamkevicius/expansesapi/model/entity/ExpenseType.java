package lt.viko.eif.madamkevicius.expansesapi.model.entity;

import jakarta.persistence.Entity;

import jakarta.persistence.Table;

@Entity
@Table(name = "expense_type")
public class ExpenseType extends BaseEntity {
    private String type;

    public ExpenseType() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ExpenseType{" +
                ", type='" + type + '\'' +
                '}';
    }
}
