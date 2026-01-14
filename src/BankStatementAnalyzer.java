import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Month;
import java.util.List;

public class BankStatementAnalyzer {

    private static final String FILE_PATH = "src/BankTransactionDetails";
    private final BankStatementParser parser;

    public BankStatementAnalyzer(BankStatementParser parser) {
        this.parser = parser;
    }

    public void analyze() throws IOException {
        final Path transactionDetails = Path.of(FILE_PATH);
        final List<String> lines = Files.readAllLines(transactionDetails);
        final List<BankTransaction> transactions = parser.parseLinesFrom(lines);

        BankStatementProcessor processor = new BankStatementProcessor(transactions);

        collectSummery(processor);
    }

    private void collectSummery(final BankStatementProcessor processor) {
        System.out.println("total: " + processor.calculateTotalAmount());
        for (Month month : Month.values()) {
            System.out.println(month + " : " + processor.calculateTotalForMonth(month));
        }
    }
}
