import java.time.Month;

public class BankTransactionInJanuaryAndExpensive implements BankTransactionFilter{

    @Override
    public boolean test(BankTransaction bankTransaction) {
        return bankTransaction.getDate().getMonth()== Month.JANUARY && bankTransaction.getAmount()>1000;
    }
}
