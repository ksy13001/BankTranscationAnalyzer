import java.util.List;

@FunctionalInterface
public interface BankTransactionSummarizer {
    double summerize(double amount, BankTransaction bankTransactions);
}
