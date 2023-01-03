package app.repository;

import app.product.Product;
import app.product.subproduct.Drink;
import app.product.subproduct.Hamburger;
import app.product.subproduct.Side;

public class ProductRepository {
    Product[] products = new Product[] {
        new Hamburger(1, "새우버거", 3500, 500, false, 4500),
        new Hamburger(2, "치킨버거", 4000, 600, false, 5000),
        new Side(3, "감자튀김", 1000, 300, 0),
        new Side(4, "어니언링", 1000, 300, 0),
        new Drink(5, "코카콜라", 1000, 200, true),
        new Drink(6, "제로콜라", 1000, 0, false)
    };

    public Product[] getAllProducts() {
        return products;
    }
}
