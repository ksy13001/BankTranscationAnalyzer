import java.util.List;

public interface BankStatementParser {
    BankTransaction parseLineFrom(String line);
    List<BankTransaction> parseLinesFrom(List<String> lines);
}
