import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Month;
import java.util.List;

public class BankTransactionAnalyzerSimple {

    public static void main(String[] args) throws IOException {
        BankStatementCSVParser parser = new BankStatementCSVParser();
        String filePath = "src/BankTransactionDetails";
        final Path transactionDetails = Path.of(filePath);
        final List<String> lines = Files.readAllLines(transactionDetails);

        double total = 0d;
        final List<BankTransaction> transactions = parser.parseLinesFromCSV(lines);

        total = calculateTotalAmount(transactions);
        System.out.println("total: " + total);
        System.out.println("total in January " + calculateTotalAmount(selectInMonth(transactions, Month.JANUARY)));
    }

    public static double calculateTotalAmount(final List<BankTransaction> transactions) {
        return transactions.stream().map(BankTransaction::getAmount).reduce(0d, Double::sum);
    }

    public static List<BankTransaction> selectInMonth(final List<BankTransaction> bankTransactions, final Month month) {
        return bankTransactions.stream()
                .filter(bankTransaction->bankTransaction.getDate().getMonth() == month)
                .toList();
    }
}
