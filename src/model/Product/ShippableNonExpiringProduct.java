package src.model.Product;
import src.model.Product.ProductInterface.Shippable;

public class ShippableNonExpiringProduct extends Product implements Shippable {
    private final Double weight;

    public ShippableNonExpiringProduct(String name, Double price, Integer quantity, Double weight) {
        super(name, price, quantity);
        this.weight = weight != null ? weight : 0.0;
    }

    @Override
    public Double getWeight() {
        return weight;
    }
}
