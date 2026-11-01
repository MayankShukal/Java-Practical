package practical6;

import java.util.ArrayList;
import java.util.List;

public class DiscountEngine {

    public static List<Double> applyDiscount(
            List<Double> prices, DiscountRule rule) {

        List<Double> result = new ArrayList<>();

        for (double price : prices) {
            result.add(rule.apply(price));
        }

        return result;
    }
}
