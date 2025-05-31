package lt.viko.eif.madamkevicius.expansesapi.repo;

import lt.viko.eif.madamkevicius.expansesapi.model.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepo extends JpaRepository<Person, Integer> {
    Person findByUsername(String username);
}
