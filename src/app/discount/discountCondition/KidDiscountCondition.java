package app.discount.discountCondition;

import app.discount.discountpolicy.DiscountPolicy;
import app.discount.discountpolicy.FixedAmountDiscountPolicy;

import java.util.Scanner;

public class KidDiscountCondition implements DiscountCondition {

    private DiscountPolicy discountPolicy;
    private boolean isSatisfied;

    public KidDiscountCondition(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }

    public boolean isSatisfied() {
        return isSatisfied;
    }

    private void setSatisfied(boolean satisfied) {
        isSatisfied = satisfied;
    }

    public void checkDiscountCondition() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("나이를 입력해주세요");
        int age = Integer.parseInt(scanner.nextLine());

        setSatisfied(age < 20);
    }

    public int applyDiscount(int price) {
        return discountPolicy.calculateDiscountedPrice(price);
    }
}
