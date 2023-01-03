package app.discount;

import app.discount.discountCondition.DiscountCondition;

public class Discount {
    private DiscountCondition[] discountConditions;

    public Discount(DiscountCondition[] discountConditions) {
        this.discountConditions = discountConditions;
    }

    public int discount(int price) {
        int discountPrice = price;

        for(DiscountCondition condition : discountConditions) {
            condition.checkDiscountCondition();
            if(condition.isSatisfied()) {
                discountPrice = condition.applyDiscount(discountPrice);
            }
        }

        return discountPrice;
    }
}
