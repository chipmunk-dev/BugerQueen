package app.discount.discountCondition;

import app.discount.discountpolicy.FixedRateDiscountPolicy;

import java.util.Scanner;

public class CozDiscountCondition {

    private FixedRateDiscountPolicy discountPolicy = new FixedRateDiscountPolicy(10);
    private boolean isSatisfied;

    public boolean isSatisfied() {
        return isSatisfied;
    }

    private void setSatisfied(boolean satisfied) {
        isSatisfied = satisfied;
    }

    public void checkDiscountCondition() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("코드스테이츠 수강생입니까? (1)_예 (2)_아니오");
        String input = scanner.nextLine();

        if(input.equals("1")) {
            setSatisfied(true);
        } else {
            setSatisfied(false);
        }
    }

    public int applyDiscount(int price) {
        return discountPolicy.calculateDiscountedPrice(price);
    }

}
