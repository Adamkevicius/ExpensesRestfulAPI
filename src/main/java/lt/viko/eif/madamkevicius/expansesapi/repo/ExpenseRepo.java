package lt.viko.eif.madamkevicius.expansesapi.repo;

import lt.viko.eif.madamkevicius.expansesapi.model.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ExpenseRepo extends JpaRepository<Expense, Integer> {

    @Query("select e from Expense e " +
            "where extract(month from e.creationDate) = :month " +
            "AND extract(year from e.creationDate) = :year " +
            "AND e.person.id = :personId")
    List<Expense> findAllByMonthAndYear
            (@Param("month") int month,
             @Param("year") int year,
             @Param("personId") int personId);

    void deleteByDescription(String description);

    boolean existsByDescription(String description);

    Optional<Expense> findByDescriptionAndPersonId(String description, int personId);
}
