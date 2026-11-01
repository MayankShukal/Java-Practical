
package practical6;

@FunctionalInterface
public interface DiscountRule {
    double apply(double price);
}
