package app.cart;

import app.product.Product;
import app.repository.ProductRepository;

import java.util.Scanner;

public class Cart {

    private Product[] items = new Product[0];
    private ProductRepository repository;

    public Cart(ProductRepository repository) {
        this.repository = repository;
    }

    private void setItems(Product[] items) {
        this.items = items;
    }

    // 1. 장바구니 추가
    public void addToCart(int productId) {
        Product findItem = repository.findById(productId);

        // 2. 메뉴에 따른 옵션 선택

        if(findItem != null) {
            Product[] newItems = new Product[items.length + 1];
            System.arraycopy(items, 0, newItems, 0, items.length);
            newItems[newItems.length-1] = findItem;
            setItems(newItems);

            System.out.printf("# %s를(을) 장바구니에 담았습니다.\n", findItem.getName());
        }
    }

    // 3. 장바구니 출력
    public void printCartItems() {
        Scanner scanner = new Scanner(System.in);
        int totalPrice = 0;

        System.out.println("# 장바구니 목록");
        System.out.println("-".repeat(60));

        // 장바구니 목록 출력
        for(Product item: items) {
            System.out.printf("%8s %d원\n", item.getName(), item.getPrice());
            totalPrice += item.getPrice();
        }

        System.out.println("-".repeat(60));

        System.out.printf("합계 : %d원\n", totalPrice);
        System.out.println("이전으로 돌아가려면 엔터를 누르세요.");
        scanner.nextLine();
    }

}
