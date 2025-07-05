package src.model.Product;
import src.model.Product.ProductInterface.Expirable;
import src.model.Product.ProductInterface.Shippable;

import java.time.LocalDate;

public class ShippableExpiringProduct extends Product implements Shippable, Expirable {
    private final Double weight;
    private final LocalDate expiryDate;

    public ShippableExpiringProduct(String name, Double price, Integer quantity, Double weight, LocalDate expiryDate) {
        super(name, price, quantity);
        this.weight = weight != null ? weight : 0.0;
        this.expiryDate = expiryDate;
    }

    @Override
    public Double getWeight() {
        return weight;
    }

    @Override
    public LocalDate getExpiryDate() {
        return expiryDate;
    }
}
