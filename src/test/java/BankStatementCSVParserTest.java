import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

class BankStatementCSVParserTest {
    private final BankStatementCSVParser bankStatementCSVParser = new BankStatementCSVParser();

    @Test
    void shouldParseOneCorrectLine() throws Exception {
        // given
        final String line = "14-01-2026,1000,KSY";
        BankTransaction expected = new BankTransaction(LocalDate.of(2026, 1, 14), 1000, "KSY");
        // when
        BankTransaction result = bankStatementCSVParser.parseLineFrom(line);
        // then
        Assertions.assertEquals(expected.getAmount(), result.getAmount());
        Assertions.assertEquals(expected.getDate(), result.getDate());
        Assertions.assertEquals(expected.getDescription(), result.getDescription());
    }

    @Test
    void shouldThrowExceptionWhenDateFormatIsWrong(){
        // given
        final String line = "2026-01-14,1000,KSY";
        // when && then
        Assertions.assertThrows( DateTimeParseException.class, ()->bankStatementCSVParser.parseLineFrom(line));
    }

    @Test
    void shouldThrowExceptionWhenDataIsNull(){
        // given
        final String line = "";
        // when && then
        Assertions.assertThrows(DateTimeParseException.class, ()->bankStatementCSVParser.parseLineFrom(line));
    }

    @Test
    void shouldThrowExceptionWhenDateTypeIsWrong(){
        // given
        final String line = "14-01-2026,천원,KSY";
        // when && then
        Assertions.assertThrows(NumberFormatException.class, ()->bankStatementCSVParser.parseLineFrom(line));
    }

}