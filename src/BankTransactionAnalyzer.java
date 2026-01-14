import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Month;
import java.time.MonthDay;
import java.util.List;

public class BankTransactionAnalyzer {

    private static final String FILE_PATH = "src/BankTransactionDetails";
    private static final BankStatementCSVParser parser = new BankStatementCSVParser();

    public static void main(String[] args) throws IOException {
        final Path transactionDetails = Path.of(FILE_PATH);
        final List<String> lines = Files.readAllLines(transactionDetails);
        final List<BankTransaction> transactions = parser.parseLinesFromCSV(lines);

        BankStatementProcessor processor = new BankStatementProcessor(transactions);

        collectSummery(processor);
    }

    private static void collectSummery(final BankStatementProcessor processor) {
        System.out.println("total: " + processor.calculateTotalAmount());
        for (Month month : Month.values()) {
            System.out.println(month + " : " + processor.calculateTotalForMonth(month));
        }
    }
}
