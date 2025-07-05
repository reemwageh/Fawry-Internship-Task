package src.service.CartServiceImp;

import src.model.Cart.Cart;
import src.model.Cart.CartItem;
import src.model.Product.Product;

import java.util.List;
import java.util.logging.Logger;

public class CartService {
    private static final Logger logger = Logger.getLogger(CartService.class.getName());

    private final Cart cart;
    private final CartItemService cartItemService = new CartItemService();


    public CartService(Cart cart) {
        this.cart = cart;
    }

    public void add(Product product, Integer quantity) {
        if (isInvalidQuantity(quantity)) {
            logger.warning("Invalid quantity.");
            return;
        }

        if (isOutOfStock(product, quantity)) {
            logger.warning("Not enough stock for product: " + (product != null ? product.getName() : ""));
            return;
        }

        cart.getCartItemsInternal().add(new CartItem(product, quantity));
    }

    public Boolean isEmpty() {
        return cart.getCartItems().isEmpty();
    }

    public Double calculateSubtotal() {
        return cart.getCartItems().stream()
                .mapToDouble(cartItemService::getTotalPrice)
                .sum();
    }

    public Double calculateShipping() {
        return cart.getCartItems().stream()
                .mapToDouble(cartItemService::getWeight)
                .sum() / 1000.0 * 30;
    }

    public Double calculateTotal() {
        return calculateSubtotal() + calculateShipping();
    }

    public Boolean hasExpiredItems() {
        return cart.getCartItems().stream().anyMatch(cartItemService::isExpired);
    }

    public Boolean hasOutOfStockItems() {
        return cart.getCartItems().stream()
                .anyMatch(item -> isOutOfStock(item.getProduct(), item.getQuantity()));
    }

    public void deductQuantities() {
        for (CartItem item : cart.getCartItems()) {
            Product product = item.getProduct();
            if (product != null && product.getQuantity() != null && item.getQuantity() != null
                    && product.getQuantity() >= item.getQuantity()) {
                product.setQuantity(product.getQuantity() - item.getQuantity());
            }
        }
    }

    public List<CartItem> getCartItems() {
        return cart.getCartItems();
    }
    private boolean isInvalidQuantity(Integer quantity) {
        return quantity == null || quantity <= 0;
    }

    private boolean isOutOfStock(Product product, Integer quantity) {
        return product == null || product.getQuantity() == null || quantity == null
                || quantity > product.getQuantity();
    }
}
