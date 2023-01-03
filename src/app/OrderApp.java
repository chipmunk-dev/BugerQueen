package app;

import app.cart.Cart;
import app.menu.Menu;
import app.product.Product;
import app.product.subproduct.Drink;
import app.product.subproduct.Hamburger;
import app.product.subproduct.Side;
import app.repository.ProductRepository;

import java.util.Scanner;

public class OrderApp {

    ProductRepository repository = new ProductRepository();
    Menu menu = new Menu(repository);
    Cart cart = new Cart(repository);

    public void start() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to BurgerQueen!");
        // 메뉴 출력
        while (true) {
            menu.printMenu();
            System.out.print("메뉴를 선택하세요: ");
            String input = scanner.nextLine();

            if(input.equals("+")) {
                // 주문하기
                break;
            } else {
                int selectMenu = Integer.parseInt(input);
                Product[] products = repository.getAllProducts();

                if(0<selectMenu && selectMenu<=products.length) {
                    cart.addToCart(selectMenu);
                } else if(selectMenu == 0) {
                    cart.printCartItems();
                }
            }
        }

    }
}
