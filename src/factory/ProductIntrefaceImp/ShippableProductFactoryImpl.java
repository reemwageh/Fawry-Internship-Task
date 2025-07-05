package src.factory.ProductIntrefaceImp;

import src.factory.ProductInterface.ShippableProductFactory;
import src.model.Product.Product;
import src.model.Product.ShippableExpiringProduct;
import src.model.Product.ShippableNonExpiringProduct;

import java.time.LocalDate;

public class ShippableProductFactoryImpl implements ShippableProductFactory {

    @Override
    public Product createExpiringProduct(String name, Double price, Integer quantity, Double weight, LocalDate expiryDate) {
        return new ShippableExpiringProduct(name, price, quantity, weight, expiryDate);
    }

    @Override
    public Product createNonExpiringProduct(String name, Double price, Integer quantity, Double weight) {
        return new ShippableNonExpiringProduct(name, price, quantity, weight);
    }
}
