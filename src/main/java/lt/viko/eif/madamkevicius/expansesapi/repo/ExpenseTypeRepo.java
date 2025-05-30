package lt.viko.eif.madamkevicius.expansesapi.repo;

import lt.viko.eif.madamkevicius.expansesapi.model.entity.ExpenseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ExpenseTypeRepo extends JpaRepository<ExpenseType, Integer> {
    Optional<ExpenseType> findExpenseTypeByType(String type);
}
