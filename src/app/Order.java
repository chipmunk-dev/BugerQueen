package app;

import app.discount.Discount;
import app.discount.discountCondition.CozDiscountCondition;
import app.discount.discountCondition.DiscountCondition;
import app.discount.discountCondition.KidDiscountCondition;
import app.discount.discountpolicy.FixedAmountDiscountPolicy;
import app.discount.discountpolicy.FixedRateDiscountPolicy;
import app.product.Product;

public class Order {
    private Cart cart;
    private Discount discount;

    public Order(Cart cart, Discount discount) {
        this.cart = cart;
        this.discount = discount;
    }

    public void makeOption() {

        int totalPrice = cart.calculateToTotalPrice();
        int finalPrice = discount.discount(totalPrice);

        System.out.println("[📣] 주문이 완료되었습니다. ");
        System.out.println("[📣] 주문 내역은 다음과 같습니다. ");
        System.out.println("-".repeat(60));

        cart.printCartItemDetails();

        System.out.println("-".repeat(60));
        System.out.printf("금액 합계      : %d원\n", totalPrice);
        System.out.printf("할인 적용 금액 : %d원\n", finalPrice);
    }
}
