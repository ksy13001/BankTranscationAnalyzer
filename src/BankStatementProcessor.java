import java.time.Month;
import java.util.List;

public class BankStatementProcessor {

    private final List<BankTransaction> bankTransactions;

    public BankStatementProcessor(final List<BankTransaction> bankTransactions) {
        this.bankTransactions = bankTransactions;
    }

    public double calculateTotalAmount() {
        return bankTransactions.stream().map(BankTransaction::getAmount)
                .reduce(0d, Double::sum);
    }

    public double calculateTotalForCategory(final String category) {
        return bankTransactions.stream()
                .filter(bankTransaction -> bankTransaction.getDescription().equals(category))
                .map(BankTransaction::getAmount)
                .reduce(0d, Double::sum);
    }

    public double calculateTotalForMonth(final Month month) {
        return selectInMonth(month).stream()
                .map(BankTransaction::getAmount)
                .reduce(0d, Double::sum);
    }

    public List<BankTransaction> selectInMonth(final Month month) {
        return bankTransactions.stream()
                .filter(bankTransaction -> bankTransaction.getDate().getMonth() == month)
                .toList();
    }
}
