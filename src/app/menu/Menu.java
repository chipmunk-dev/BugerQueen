package app.menu;

import app.product.Product;
import app.product.subproduct.Drink;
import app.product.subproduct.Hamburger;
import app.product.subproduct.Side;
import app.repository.ProductRepository;

public class Menu {

    private ProductRepository repository = new ProductRepository();

    public Menu(ProductRepository repository) {
        this.repository = repository;
    }

    public void printMenu() {
        System.out.println("# 메뉴");
        System.out.println("-".repeat(60));
        System.out.println("# 햄버거");
        for(Product product: repository.getAllProducts()) {
            if(product instanceof Hamburger) {
                printEachProduct(product);
            }
        }
        System.out.println();

        System.out.println("# 사이드");
        for(Product product: repository.getAllProducts()) {
            if(product instanceof Side) {
                printEachProduct(product);
            }
        }
        System.out.println();

        System.out.println("# 음료수");
        for(Product product: repository.getAllProducts()) {
            if(product instanceof Drink) {
                printEachProduct(product);
            }
        }
        System.out.println();

        System.out.println("(0) 장바구니");
        System.out.println("(+) 주문하기");
        System.out.println("-".repeat(60));
    }

    private static void printEachProduct(Product product) {
        System.out.printf("(%d) %5s %dKcal %d원\n",
                product.getId(),
                product.getName(),
                product.getKcal(),
                product.getPrice()
        );
    }
}
