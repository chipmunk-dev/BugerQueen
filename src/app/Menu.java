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
        System.out.println("[π] Menu");
        System.out.println("-".repeat(66));

        printHamburger(true);
        printSide(true);
        printDrink(true);

        System.out.println();
        System.out.println("π (0) Cart");
        System.out.println("πͺ (+) Order");
        System.out.println("-".repeat(66));
        System.out.print("[π£] λ©λ΄λ₯Ό μ νν΄μ£ΌμΈμ: ");
    }

    public void printDrink(boolean printPrice) {
        System.out.println("π₯€ Drink");
        for(Product product : products) {
            if(product instanceof Drink) {
                printEachMenu(product, false);
            }
        }
        System.out.println();
    }

    public void printSide(boolean printPrice) {
        System.out.println("π Side");
        for(Product product : products) {
            if(product instanceof Side) {
                printEachMenu(product, printPrice);
            }
        }
        System.out.println();
    }

    private void printHamburger(boolean printPrice) {
        System.out.println("π Hamburger");
        for(Product product : products) {
            if(product instanceof Hamburger) {
                printEachMenu(product, printPrice);
            }
        }
        System.out.println();
    }

    private static void printEachMenu(Product product, boolean printPrice) {
        if(printPrice) {
            System.out.printf("(%d) %s %5dKcal %5dμ\n",
                    product.getId(),
                    product.getName(),
                    product.getKcal(),
                    product.getPrice()
            );
        } else {
            System.out.printf("(%d) %s %5dKcal\n",
                    product.getId(),
                    product.getName(),
                    product.getKcal()
            );
        }
    }

}
