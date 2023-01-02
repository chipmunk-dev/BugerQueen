package app;

import app.product.Product;
import app.product.subproduct.Drink;
import app.product.subproduct.Hamburger;
import app.product.subproduct.Side;

public class Menu {

    private final Product[] products;

    public Menu(Product[] products) {
        this.products = products;
    }

    public void printMenu() {
        System.out.println("[👇] Menu");
        System.out.println("-".repeat(66));

        printHamburger();
        printSide();
        printDrink();

        System.out.println();
        System.out.println("🛒 (0) Cart");
        System.out.println("🪄 (+) Order");
        System.out.println("-".repeat(66));
        System.out.print("[📣] 메뉴를 선택해주세요: ");
    }

    private void printDrink() {
        System.out.println("🥤 Drink");
        for(Product product : products) {
            if(product instanceof Drink) {
                System.out.println(product.toString());
            }
        }
        System.out.println();
    }

    private void printSide() {
        System.out.println("🍟 Side");
        for(Product product : products) {
            if(product instanceof Side) {
                System.out.println(product.toString());
            }
        }
        System.out.println();
    }

    private void printHamburger() {
        System.out.println("🍔 Hamburger");
        for(Product product : products) {
            if(product instanceof Hamburger) {
                System.out.println(product.toString());
            }
        }
        System.out.println();
    }

}
