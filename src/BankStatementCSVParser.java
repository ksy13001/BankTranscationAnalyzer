import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class BankStatementCSVParser {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public List<BankTransaction> parseLinesFromCSV(final List<String> lines){
        return lines.stream().map(this::parserFromSCV).toList();
    }

    private BankTransaction parserFromSCV(final String line){
        String[] column = line.split(",");

        return new BankTransaction(
                LocalDate.parse(column[0], DATE_FORMATTER),
                Double.parseDouble(column[1]),
                column[2]);
    }



}
