package app;

import app.product.Product;
import app.product.subproduct.Drink;
import app.product.subproduct.Hamburger;
import app.product.subproduct.Side;
import app.repository.ProductRepository;

import java.util.Scanner;

public class OrderApp {

    ProductRepository repository = new ProductRepository();

    public void start() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to BurgerQueen!");
        System.out.println("-".repeat(60));

        // 메뉴 출력
        System.out.println("# 햄버거");
        for(Product product: repository.getAllProducts()) {
            if(product instanceof Hamburger) {
                System.out.printf("(%d) %5s %dKcal %d원\n",
                        product.getId(),
                        product.getName(),
                        product.getKcal(),
                        product.getPrice()
                );
            }
        }
        System.out.println();

        System.out.println("# 사이드");
        for(Product product: repository.getAllProducts()) {
            if(product instanceof Side) {
                System.out.printf("(%d) %5s %dKcal %d원\n",
                        product.getId(),
                        product.getName(),
                        product.getKcal(),
                        product.getPrice()
                );
            }
        }
        System.out.println();

        System.out.println("# 음료수");
        for(Product product: repository.getAllProducts()) {
            if(product instanceof Drink) {
                System.out.printf("(%d) %5s %dKcal %d원\n",
                        product.getId(),
                        product.getName(),
                        product.getKcal(),
                        product.getPrice()
                );
            }
        }
        System.out.println();

        System.out.println("(0) 장바구니");
        System.out.println("(+) 주문하기");
        System.out.println("-".repeat(60));
        System.out.print("메뉴를 선택하세요: ");
        String selectMenu = scanner.nextLine();

    }
}
