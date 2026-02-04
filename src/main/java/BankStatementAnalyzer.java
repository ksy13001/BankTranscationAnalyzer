import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class BankStatementAnalyzer {

    private static final String RESOURCE_NAME = "BankTransactionDetails";
    private final BankStatementParser parser;

    public BankStatementAnalyzer(BankStatementParser parser) {
        this.parser = parser;
    }

    public void analyze() throws IOException {
        final List<String> lines = readLinesFromResource();
        final List<BankTransaction> transactions = parser.parseLinesFrom(lines);

        BankStatementProcessor processor = new BankStatementProcessor(transactions);

        collectSummery(processor);
    }

    private List<String> readLinesFromResource() throws IOException {
        InputStream input = BankStatementAnalyzer.class.getClassLoader().getResourceAsStream(BankStatementAnalyzer.RESOURCE_NAME);
        if (input == null) {
            throw new IllegalStateException("Resource not found: " + BankStatementAnalyzer.RESOURCE_NAME);
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
        processor.findTransactions(bankTransaction -> bankTransaction.getAmount() > 1000)
                .forEach(System.out::println);
        double maxAmountInJanuary = processor.calculateMaximumTransactionAmountBetweenDates(
                LocalDate.of(2017, 1, 1),
                LocalDate.of(2017,1 ,31));
        double minAmountInJanuary = processor.calculateMinimumTransactionAmountBetweenDates(
                LocalDate.of(2017, 1, 1),
                LocalDate.of(2017,1 ,31));

        System.out.println("max Amount in January : " + maxAmountInJanuary);
        System.out.println("min Amount in January : " + minAmountInJanuary);
    }
}
