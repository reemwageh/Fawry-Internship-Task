package src.model.Cart;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cart {
    private final List<CartItem> cartItems;

    public Cart() {
        this.cartItems = new ArrayList<>();
    }

    public Cart(List<CartItem> cartItems) {
        this.cartItems = cartItems != null ? cartItems : new ArrayList<>();
    }

    public List<CartItem> getCartItems() {
        return Collections.unmodifiableList(cartItems);
    }

    public List<CartItem> getCartItemsInternal() {
        return cartItems;
    }
}
