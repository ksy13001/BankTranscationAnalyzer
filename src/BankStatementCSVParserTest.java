import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class BankStatementCSVParserTest {
    private final BankStatementCSVParser bankStatementCSVParser = new BankStatementCSVParser();

    @Test
    public void shouldParseOneCorrectLine() throws Exception {
        final String line = "14-01-2026,1000,KSY";
        BankTransaction result = bankStatementCSVParser.parseLineFrom(line);
        BankTransaction expected = new BankTransaction(LocalDate.of(2026, 1, 14), 1000, "KSY");
        Assertions.assertEquals(expected.getAmount(), result.getAmount());
        Assertions.assertEquals(expected.getDate(), result.getDate());
        Assertions.assertEquals(expected.getDescription(), result.getDescription());


    }

}