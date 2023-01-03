package app.discount.discountpolicy;

public class FixedAmountDiscountPolicy {

    private final int discountAmount;

    public FixedAmountDiscountPolicy(int discountAmount) {
        this.discountAmount = discountAmount;
    }

    public int calculateDiscountedPrice(int price) {
        int discountPrice = price - discountAmount;
        return Math.max(discountPrice, 0);
    }
}
