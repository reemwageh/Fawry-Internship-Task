package src.factory.ProductInterface;

import src.model.Product.Product;
import java.time.LocalDate;

public interface ShippableProductFactory {
    Product createExpiringProduct(String name, Double price, Integer quantity, Double weight, LocalDate expiryDate);
    Product createNonExpiringProduct(String name, Double price, Integer quantity, Double weight);
}
