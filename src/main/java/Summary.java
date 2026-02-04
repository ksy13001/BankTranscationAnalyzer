import java.time.Month;
import java.util.List;
import java.util.Map;

public class Summary {
    private final double total;
    private final List<Map<Month, Double>> maxAmountPerMonth;
    private final List<Map<Month, Double>> minAmountPerMonth;

    private Summary(double total, List<Map<Month, Double>> maxAmountPerMonth, List<Map<Month, Double>> minAmountPerMonth) {
        this.total = total;
        this.maxAmountPerMonth = maxAmountPerMonth;
        this.minAmountPerMonth = minAmountPerMonth;
    }

    public double getTotal() {
        return total;
    }

    public List<Map<Month, Double>> getMaxAmountPerMonth() {
        return maxAmountPerMonth;
    }

    public List<Map<Month, Double>> getMinAmountPerMonth() {
        return minAmountPerMonth;
    }

    public static Summary create(double total, List<Map<Month, Double>> maxAmountPerMonth, List<Map<Month, Double>> minAmountPerMonth) {
        return new Summary(total, maxAmountPerMonth, minAmountPerMonth);
    }
}
