package app.product.subproduct;

import app.product.Product;

public class Hamburger extends Product {

    boolean isBurgerSet;
    int setPrice;

    public Hamburger(int id, String name, int price, int kcal, boolean isBurgerSet, int setPrice) {
        super(id, name, price, kcal);
        this.isBurgerSet = isBurgerSet;
        this.setPrice = setPrice;
    }

    public boolean isBurgerSet() {
        return isBurgerSet;
    }

    public void setBurgerSet(boolean burgerSet) {
        isBurgerSet = burgerSet;
    }

    public int getSetPrice() {
        return setPrice;
    }
}
