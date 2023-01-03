package app;

import app.discount.discountCondition.CozDiscountCondition;
import app.discount.discountCondition.KidDiscountCondition;
import app.product.Product;

public class Order {
    private Cart cart;

    public Order(Cart cart) {
        this.cart = cart;
    }

    public void makeOption() {

        CozDiscountCondition cozDiscountCondition = new CozDiscountCondition();
        KidDiscountCondition kidDiscountCondition = new KidDiscountCondition();

        cozDiscountCondition.checkDiscountCondition();
        kidDiscountCondition.checkDiscountCondition();

        int totalPrice = cart.calculateToTotalPrice();
        int finalPrice = totalPrice;

        if(cozDiscountCondition.isSatisfied()) {
            finalPrice = cozDiscountCondition.applyDiscount(finalPrice);
        }
        if(kidDiscountCondition.isSatisfied()) {
            finalPrice = kidDiscountCondition.applyDiscount(finalPrice);
        }

        System.out.println("[📣] 주문이 완료되었습니다. ");
        System.out.println("[📣] 주문 내역은 다음과 같습니다. ");
        System.out.println("-".repeat(60));

        cart.printCartItemDetails();

        System.out.println("-".repeat(60));
        System.out.printf("금액 합계      : %d원\n", totalPrice);
        System.out.printf("할인 적용 금액 : %d원\n", finalPrice);
    }
}
