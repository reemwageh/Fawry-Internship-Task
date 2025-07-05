package src.service.CartServiceImp;

import src.model.Cart.CartItem;
import src.model.Product.ProductInterface.Expirable;
import src.model.Product.ProductInterface.Shippable;

import java.time.LocalDate;

public class CartItemService {

    public Double getTotalPrice(CartItem item) {
        return item.getProduct().getPrice() * item.getQuantity();
    }

    public Double getWeight(CartItem item) {
        if (item.getProduct() instanceof Shippable shippable) {
            return shippable.getWeight() * item.getQuantity();
        }
        return 0.0;
    }

    public Boolean isShippable(CartItem item) {
        return item.getProduct() instanceof Shippable;
    }

    public Boolean isExpired(CartItem item) {
        if (item.getProduct() instanceof Expirable expirable) {
            LocalDate expiryDate = expirable.getExpiryDate();
            return expiryDate != null && expiryDate.isBefore(LocalDate.now());
        }
        return false;
    }
}
