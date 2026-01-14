import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.Month;
import java.util.List;

public class BankStatementAnalyzer {

    private static final String RESOURCE_NAME = "BankTransactionDetails";
    private final BankStatementParser parser;

    public BankStatementAnalyzer(BankStatementParser parser) {
        this.parser = parser;
    }

    public void analyze() throws IOException {
        final List<String> lines = readLinesFromResource(RESOURCE_NAME);
        final List<BankTransaction> transactions = parser.parseLinesFrom(lines);

        BankStatementProcessor processor = new BankStatementProcessor(transactions);

        collectSummery(processor);
    }

    private List<String> readLinesFromResource(final String resourceName) throws IOException {
        InputStream input = BankStatementAnalyzer.class.getClassLoader().getResourceAsStream(resourceName);
        if (input == null) {
            throw new IllegalStateException("Resource not found: " + resourceName);
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8))) {
            return reader.lines().toList();
        }
    }

    private void collectSummery(final BankStatementProcessor processor) {
        System.out.println("total: " + processor.calculateTotalAmount());
        for (Month month : Month.values()) {
            System.out.println(month + " : " + processor.calculateTotalForMonth(month));
        }
    }
}
