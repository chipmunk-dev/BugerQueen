package app;

import app.product.Product;
import app.product.subproduct.BurgerSet;

import java.util.Scanner;

public class Cart {
    private Product[] items = new Product[0];
    private Scanner scanner = new Scanner(System.in);

    public void printCart() {
        System.out.println("🧺 장바구니");
        System.out.println("-".repeat(60));

        for(Product product: items) {
            if(product instanceof BurgerSet) System.out.printf("%8s %d원 (%s(%케첩 %d개), %s(빨대 %s)",
                    product.getName(),
                    ((BurgerSet) product).getSide().getName(),
                    ((BurgerSet) product).getSide().getKetchup(),
                    ((BurgerSet) product).getDrink().getName(),
                    ((BurgerSet) product).getDrink().isHasStraw() ? "있음" : "없음");

        }

        System.out.println("-".repeat(60));
        System.out.printf("합계 : %d원\n");

        System.out.println("이전으로 돌아가려면 엔터를 누르세요. ");
        scanner.nextLine();
    }
}
