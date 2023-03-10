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

        System.out.printf("[ð£] %së¥¼(ì) ì¥ë°êµ¬ëì ë´ììµëë¤.\n", newProduct.getName());
    }

    private void chooseOption(Product product) {
        String input;

        if(product instanceof Hamburger) {
            System.out.printf("ë¨íì¼ë¡ ì£¼ë¬¸íìê² ì´ì? (1)_ë¨í(%dì) (2)_ì¸í¸(%dì)\n",
                    product.getPrice(),
                    ((Hamburger) product).getBurgerSetPrice()
            );
            input = scanner.nextLine();
            if(input.equals("2")) {
                ((Hamburger) product).setBurgerSet(true);
            }
        } else if (product instanceof Side) {
            System.out.println("ì¼ì²©ì ëªê°ê° íìíì ê°ì?");
            input = scanner.nextLine();
            ((Side) product).setKetchup(Integer.parseInt(input));
        } else if (product instanceof Drink) {
            System.out.println("ë¹¨ëê° íìíì ê°ì? (1)_ì (2)_ìëì¤");
            input = scanner.nextLine();
            if(input.equals("2")) {
                ((Drink) product).setHasStraw(false);
            }
        }
    }

    private BurgerSet composeSet(Hamburger hamburger) {
        System.out.println("ì¬ì´ëë¥¼ ê³¨ë¼ì£¼ì¸ì");
        menu.printSide(true);

        String sideId = scanner.nextLine();
        Side side = (Side) productRepository.findById(Integer.parseInt(sideId));
        chooseOption(side);

        System.out.println("ìë£ë¥¼ ê³¨ë¼ì£¼ì¸ì.");
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
        System.out.println("ð§º ì¥ë°êµ¬ë");
        System.out.println("-".repeat(60));

        printCartItemDetails();

        System.out.println("-".repeat(60));
        System.out.printf("í©ê³ : %dì\n", calculateToTotalPrice());

        System.out.println("ì´ì ì¼ë¡ ëìê°ë ¤ë©´ ìí°ë¥¼ ëë¥´ì¸ì. ");
        scanner.nextLine();
    }

    protected void printCartItemDetails() {
        for(Product product: items) {
            if(product instanceof BurgerSet) System.out.printf("%8s %dì (%s(ì¼ì²© %dê°), %s(ë¹¨ë %s))\n",
                    product.getName(),
                    product.getPrice(),
                    ((BurgerSet) product).getSide().getName(),
                    ((BurgerSet) product).getSide().getKetchup(),
                    ((BurgerSet) product).getDrink().getName(),
                    ((BurgerSet) product).getDrink().isHasStraw() ? "ìì" : "ìì"
            );
            else if(product instanceof Hamburger) System.out.printf("%8s %dì (ë¨í)\n",
                    product.getName(),
                    product.getPrice()
            );
            else if(product instanceof Side) System.out.printf("%8s %dì (ì¼ì²© %dê°)\n",
                    product.getName(),
                    product.getPrice(),
                    ((Side) product).getKetchup()
            );
            else if(product instanceof Drink) System.out.printf("%8s %dì (ë¹¨ë %s)\n",
                    product.getName(),
                    product.getPrice(),
                    ((Drink) product).isHasStraw() ? "ìì" : "ìì"
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
