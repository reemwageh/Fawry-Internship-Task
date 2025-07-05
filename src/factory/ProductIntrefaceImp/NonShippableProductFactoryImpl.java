package src.factory.ProductIntrefaceImp;

import src.factory.ProductInterface.NonShippableProductFactory;
import src.model.Product.NonShippableNonExpiringProduct;
import src.model.Product.Product;

public class NonShippableProductFactoryImpl implements NonShippableProductFactory {
    @Override
    public Product createNonExpiringProduct(String name, Double price, Integer quantity) {
        return new NonShippableNonExpiringProduct(name, price, quantity);
    }
}
