import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class BankTransactionAnalyzerSimple {

    public static void main(String[] args) throws IOException {
        String filePath = "src/BankTransactionDetails";
        final Path transactionDetails = Path.of(filePath);
        final List<String> lines = Files.readAllLines(transactionDetails);

        double total = 0d;

        for (final String line : lines) {
            String[] transaction = line.split(",");
            double amount = Double.parseDouble(transaction[1]);
            if (amount > 0) {
                total += amount;
            } else {
                total += amount;
            }
        }

        System.out.println("total: " + total);
    }
}
