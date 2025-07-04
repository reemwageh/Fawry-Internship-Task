package src.model;

public class CartItem {
    public Product product;
    public int quantity;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return product.price * quantity;
    }

    public boolean isShippable() {
        return product.requiresShipping();
    }

    public double getWeight() {
        if (product instanceof Shippable) {
            return ((Shippable) product).getWeight() * quantity;
        }
        return 0;
    }
}
