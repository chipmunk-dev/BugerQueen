package app.discount.discountpolicy;

public class FixedRateDiscountPolicy implements DiscountPolicy {

    private final int discountRate;

    public FixedRateDiscountPolicy(int discountRate) {
        this.discountRate = discountRate;
    }

    public int calculateDiscountedPrice(int price) {
        return price - (price * discountRate / 100);
    }
}
