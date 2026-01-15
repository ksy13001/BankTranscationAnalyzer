import java.time.LocalDate;
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

    public double calculateMaximumTransactionAmountBetweenDates(LocalDate start, LocalDate end){
        return bankTransactions.stream()
                .filter(bankTransaction -> {
                    LocalDate transactionDate = bankTransaction.getDate();
                    return !transactionDate.isBefore(start)&&!transactionDate.isAfter(end);
                })
                .mapToDouble(BankTransaction::getAmount)
                .max()
                .orElse(0.0);
    }

    public double calculateMinimumTransactionAmountBetweenDates(LocalDate start, LocalDate end){
        return bankTransactions.stream()
                .filter(bankTransaction -> {
                    LocalDate transactionDate = bankTransaction.getDate();
                    return !transactionDate.isBefore(start)&&!transactionDate.isAfter(end);
                })
                .mapToDouble(BankTransaction::getAmount)
                .min()
                .orElse(0.0);
    }

    public List<BankTransaction> selectInMonth(final Month month) {
        return bankTransactions.stream()
                .filter(bankTransaction -> isTransactionInMonth(bankTransaction, month))
                .toList();
    }

    public List<BankTransaction> findTransactionsGreaterThan(final double amount) {
        return bankTransactions.stream()
                .filter(bankTransaction -> isTransactionAmountGreaterThan(bankTransaction, amount))
                .toList();
    }

    public List<BankTransaction> findTransactionsByMonthAndAmount(final Month month, final double amount) {
        return bankTransactions.stream()
                .filter(bt -> isTransactionInMonth(bt, month) && isTransactionAmountGreaterThan(bt, amount))
                .toList();
    }

    private boolean isTransactionInMonth(final BankTransaction bankTransaction, final Month month) {
        return bankTransaction.getDate().getMonth().equals(month);
    }

    private boolean isTransactionAmountGreaterThan(final BankTransaction bankTransaction, final double amount) {
        return bankTransaction.getAmount() > amount;
    }
}
