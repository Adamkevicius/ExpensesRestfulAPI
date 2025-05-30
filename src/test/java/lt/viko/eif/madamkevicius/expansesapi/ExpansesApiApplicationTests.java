package lt.viko.eif.madamkevicius.expansesapi;

import lt.viko.eif.madamkevicius.expansesapi.model.entity.Expense;
import lt.viko.eif.madamkevicius.expansesapi.model.entity.MonthlyExpense;
import lt.viko.eif.madamkevicius.expansesapi.repo.ExpenseRepo;
import lt.viko.eif.madamkevicius.expansesapi.repo.MonthlyExpenseRepo;
import lt.viko.eif.madamkevicius.expansesapi.repo.PersonRepo;
import lt.viko.eif.madamkevicius.expansesapi.service.MapperService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.Month;
import java.util.Base64;
import java.util.List;

@SpringBootTest
class ExpansesApiApplicationTests {

    @Test
    void contextLoads() {
    }

    private final String FILE_PATH = "src/main/resources/secretKey.txt";

    @Autowired
    private ExpenseRepo expenseRepo;

    @Autowired
    private MapperService mapperService;

    @Autowired
    private MonthlyExpenseRepo monthlyExpenseRepo;

    @Autowired
    private PersonRepo personRepo;

    @Test
    public void getSecretKey() {
        try(BufferedReader br = new BufferedReader(new FileReader("secret-key.txt"))) {
            System.out.println(br.readLine());
        } catch (IOException e) {
            throw new RuntimeException("Error while reading a file" + e);
        }
    }

    @Test void generateKey() {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
            SecretKey key = keyGen.generateKey();
            String secretKey = Base64.getEncoder().encodeToString(key.getEncoded());
            System.out.println(secretKey);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Test void getExpensesByMonth() {
        List<Expense> expenseList = expenseRepo.findAllByMonthAndYear(
                Month.valueOf("May".toUpperCase()).getValue(), LocalDate.now().getYear(), 1
        );

        List<MonthlyExpense> monthlyExpense = monthlyExpenseRepo.findAllByYearAndMonthAndPersonId(
                2025, Month.valueOf("May".toUpperCase()).getValue(), 1
        );

        System.out.println(monthlyExpense);

        System.out.println(expenseList);

    }
}
