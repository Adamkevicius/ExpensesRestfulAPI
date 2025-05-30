package lt.viko.eif.madamkevicius.expansesapi.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "person")
public class Person extends BaseEntity {
    private String username;

    private String password;

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

    @Override
    public String toString() {
        return "Person{" +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
