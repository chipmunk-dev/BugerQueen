package app;

import app.product.Product;
import app.product.ProductRepository;
import app.product.subproduct.BurgerSet;
import app.product.subproduct.Drink;
import app.product.subproduct.Hamburger;
import app.product.subproduct.Side;

import java.util.Scanner;
import java.util.stream.Stream;

public class Cart {
    private Product[] items = new Product[0];
    private Scanner scanner = new Scanner(System.in);
    private ProductRepository productRepository;
    private Menu menu;

    public Cart(ProductRepository productRepository, Menu menu) {
        this.productRepository = productRepository;
        this.menu = menu;
    }

    public void addToCart(int productId) {
        Product product = productRepository.findById(productId);

        Product newProduct;
        if (product instanceof Hamburger) newProduct = new Hamburger((Hamburger) product);
        else if (product instanceof Side) newProduct = new Side((Side) product);
        else if (product instanceof Drink) newProduct = new Drink((Drink) product);
        else newProduct = new BurgerSet((BurgerSet) product);

        chooseOption(newProduct);

        if(newProduct instanceof Hamburger) {
            Hamburger hamburger = (Hamburger) newProduct;
            if(hamburger.isBurgerSet()) {
                newProduct = composeSet(hamburger);
            }
        }

        Product[] newItems = new Product[items.length + 1];
        System.arraycopy(items, 0, newItems, 0, items.length);
        newItems[newItems.length-1] = newProduct;
        items = newItems;

        System.out.printf("[📣] %s를(을) 장바구니에 담았습니다.\n", newProduct.getName());
    }

    private void chooseOption(Product product) {
        String input;

        if(product instanceof Hamburger) {
            System.out.printf("단품으로 주문하시겠어요? (1)_단품(%d원) (2)_세트(%d원)\n",
                    product.getPrice(),
                    ((Hamburger) product).getBurgerSetPrice()
            );
            input = scanner.nextLine();
            if(input.equals("2")) {
                ((Hamburger) product).setBurgerSet(true);
            }
        } else if (product instanceof Side) {
            System.out.println("케첩은 몇개가 필요하신가요?");
            input = scanner.nextLine();
            ((Side) product).setKetchup(Integer.parseInt(input));
        } else if (product instanceof Drink) {
            System.out.println("빨대가 필요하신가요? (1)_예 (2)_아니오");
            input = scanner.nextLine();
            if(input.equals("2")) {
                ((Drink) product).setHasStraw(false);
            }
        }
    }

    private BurgerSet composeSet(Hamburger hamburger) {
        System.out.println("사이드를 골라주세요");
        menu.printSide(true);

        String sideId = scanner.nextLine();
        Side side = (Side) productRepository.findById(Integer.parseInt(sideId));
        chooseOption(side);

        System.out.println("음료를 골라주세요.");
        menu.printDrink(true);

        String drinkId = scanner.nextLine();
        Drink drink = (Drink) productRepository.findById(Integer.parseInt(drinkId));
        chooseOption(drink);

        String name = hamburger.getName();
        int price = hamburger.getBurgerSetPrice();
        int kcal = hamburger.getKcal() + side.getKcal() + drink.getKcal();

        BurgerSet burgerSet = new BurgerSet(name, price, kcal, hamburger, side, drink);

        return burgerSet;
    }

    public void printCart() {
        System.out.println("🧺 장바구니");
        System.out.println("-".repeat(60));

        printCartItemDetails();

        System.out.println("-".repeat(60));
        System.out.printf("합계 : %d원\n", calculateToTotalPrice());

        System.out.println("이전으로 돌아가려면 엔터를 누르세요. ");
        scanner.nextLine();
    }

    protected void printCartItemDetails() {
        for(Product product: items) {
            if(product instanceof BurgerSet) System.out.printf("%8s %d원 (%s(케첩 %d개), %s(빨대 %s))\n",
                    product.getName(),
                    product.getPrice(),
                    ((BurgerSet) product).getSide().getName(),
                    ((BurgerSet) product).getSide().getKetchup(),
                    ((BurgerSet) product).getDrink().getName(),
                    ((BurgerSet) product).getDrink().isHasStraw() ? "있음" : "없음"
            );
            else if(product instanceof Hamburger) System.out.printf("%8s %d원 (단품)\n",
                    product.getName(),
                    product.getPrice()
            );
            else if(product instanceof Side) System.out.printf("%8s %d원 (케첩 %d개)\n",
                    product.getName(),
                    product.getPrice(),
                    ((Side) product).getKetchup()
            );
            else if(product instanceof Drink) System.out.printf("%8s %d원 (빨대 %s)\n",
                    product.getName(),
                    product.getPrice(),
                    ((Drink) product).isHasStraw() ? "있음" : "없음"
            );
        }
    }

    protected int calculateToTotalPrice() {
        int sum = 0;
        for(Product item: items) {
            sum += item.getPrice();
        }
        return sum;
    }
}
