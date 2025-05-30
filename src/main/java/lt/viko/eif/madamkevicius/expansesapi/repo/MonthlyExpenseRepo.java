package lt.viko.eif.madamkevicius.expansesapi.repo;

import lt.viko.eif.madamkevicius.expansesapi.model.entity.MonthlyExpense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonthlyExpenseRepo extends JpaRepository<MonthlyExpense, Integer> {
    @Query("select me from MonthlyExpense me where me.year = :year " +
            "and me.month = :month and me.person.id = :personId")
    List<MonthlyExpense> findAllByYearAndMonthAndPersonId
            (@Param("year") int year, @Param("month") int month, @Param("personId") int person_id);
}
