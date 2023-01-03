package app;

import app.discount.Discount;
import app.discount.discountCondition.CozDiscountCondition;
import app.discount.discountCondition.DiscountCondition;
import app.discount.discountCondition.KidDiscountCondition;
import app.discount.discountpolicy.FixedAmountDiscountPolicy;
import app.discount.discountpolicy.FixedRateDiscountPolicy;
import app.product.Product;
import app.product.ProductRepository;

import java.util.Scanner;

public class OrderApp {

    public void start(){

        Scanner scanner = new Scanner(System.in);
        ProductRepository productRepository = new ProductRepository();
        Product[] products = productRepository.getAllProducts();
        Menu menu = new Menu(products);
        Cart cart = new Cart(productRepository, menu);
        Order order = new Order(cart, new Discount(new DiscountCondition[] {
                new CozDiscountCondition(new FixedRateDiscountPolicy(10)),
                new KidDiscountCondition(new FixedAmountDiscountPolicy(500))
        }));

        System.out.println("🍟 BurgerQueen Order Service");

        while (true) {
            menu.printMenu();
            String input = scanner.nextLine();

            if(input.equals("+")) {
                order.makeOption();
                break;
            } else {
                int menuNumber = Integer.parseInt(input);

                if(menuNumber == 0) {
                    cart.printCart();
                } else if (menuNumber >= 1 && menuNumber <= products.length) {
                    cart.addToCart(menuNumber);
                }
            }
        }

    }
}
