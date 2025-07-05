package src.factory.ProductInterface;

import src.model.Product.Product;

public interface NonShippableProductFactory {
    Product createNonExpiringProduct(String name, Double price, Integer quantity);
}
