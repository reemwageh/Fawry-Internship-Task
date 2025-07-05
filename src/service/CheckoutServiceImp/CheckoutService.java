package src.service.CheckoutServiceImp;

import src.model.Cart.CartItem;
import src.model.Checkout.CheckoutSummary;
import src.model.Customer.Customer;
import src.model.Product.ProductInterface.Shippable;
import src.service.CartServiceImp.CartItemService;
import src.service.CartServiceImp.CartService;
import src.service.CustomerServiceImp.CustomerService;
import src.service.ShippingServiceImp.ShippingService;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class CheckoutService {
    private static final Logger logger = Logger.getLogger(CheckoutService.class.getName());

    private final CartService cartService;
    private final CustomerService customerService;
    private final CartItemService cartItemService = new CartItemService();

    public CheckoutService(CartService cartService, CustomerService customerService) {
        this.cartService = cartService;
        this.customerService = customerService;
    }

    public CheckoutSummary checkout(Customer customer) {
        if (Boolean.TRUE.equals(isCartInvalid())){
            return null;
        }

        Double subtotal = cartService.calculateSubtotal();
        Double shipping = cartService.calculateShipping();
        Double total = cartService.calculateTotal();

        if (!Boolean.TRUE.equals(customerService.hasSufficientBalance(customer, total))) {
            logger.warning("Checkout failed: Insufficient customer balance.");
            return null;
        }

        cartService.deductQuantities();

        Boolean deducted = customerService.deductBalance(customer, total);
        if (!Boolean.TRUE.equals(deducted)) {
            logger.warning("Checkout failed during balance deduction.");
            return null;
        }
        List<CartItem> shippableItems = cartService.getCartItems().stream()
                .filter(item -> item.getProduct() instanceof Shippable)
                .collect(Collectors.toList());

        ShippingService.ship(shippableItems, cartItemService);

        return new CheckoutSummary(subtotal, shipping, total, customer.getBalance());
    }

    private Boolean isCartInvalid() {
        return Boolean.TRUE.equals(cartService.isEmpty())
                || Boolean.TRUE.equals(cartService.hasExpiredItems())
                || Boolean.TRUE.equals(cartService.hasOutOfStockItems());
    }

    private void printReceipt(Double subtotal, Double shipping, Double total, Double remainingBalance) {
        logger.info("** Checkout receipt **");
        for (var item : cartService.getCartItems()) {
            String name = item.getProduct() != null ? item.getProduct().getName() : "Unknown";
            Double totalItemPrice = cartItemService.getTotalPrice(item);
            logger.info(String.format("%dx %s %.0f", item.getQuantity(), name, totalItemPrice));
        }

        logger.info("----------------------");
        logger.info(String.format("Subtotal %.2f", subtotal));
        logger.info(String.format("Shipping %.0f", shipping));
        logger.info(String.format("Amount %.0f", total));
        logger.info(String.format("Balance after payment: %.0f", remainingBalance));
    }
}
