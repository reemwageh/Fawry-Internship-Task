import src.factory.ProductInterface.NonShippableProductFactory;
import src.factory.ProductInterface.ShippableProductFactory;
import src.factory.ProductIntrefaceImp.NonShippableProductFactoryImpl;
import src.factory.ProductIntrefaceImp.ShippableProductFactoryImpl;
import src.model.Cart.Cart;
import src.model.Cart.CartItem;
import src.model.Checkout.CheckoutSummary;
import src.model.Customer.Customer;
import src.model.Product.Product;
import src.service.CartServiceImp.CartService;
import src.service.CheckoutServiceImp.CheckoutService;
import src.service.CustomerServiceImp.CustomerService;

import java.time.LocalDate;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {
        Logger logger = Logger.getLogger(Main.class.getName());

        ShippableProductFactory shippableFactory = new ShippableProductFactoryImpl();
        NonShippableProductFactory nonShippableFactory = new NonShippableProductFactoryImpl();

        // Create products
        Product cheese = shippableFactory.createExpiringProduct("Cheese", 100.0, 10, 400.0, LocalDate.of(2025, 7, 15));
        Product tv = shippableFactory.createNonExpiringProduct("TV", 150.0, 5, 700.0);
        Product biscuits = nonShippableFactory.createNonExpiringProduct("Biscuits", 150.0, 5);
        Product scratchCard = nonShippableFactory.createNonExpiringProduct("Scratch Card", 50.0, 20);

        // Create cart and service
        Cart cart = new Cart();
        CartService cartService = new CartService(cart);

        // Add products to cart
        cartService.add(cheese, 2);
        cartService.add(biscuits, 1);
        cartService.add(tv, 1);
        cartService.add(scratchCard, 3);

        // Create customer
        Customer customer = new Customer("Reem", 1000.0);

        CustomerService customerService = new CustomerService();
        CheckoutService checkoutService = new CheckoutService(cartService, customerService);

        // Checkout
        CheckoutSummary summary = checkoutService.checkout(customer);

        if (summary != null) {
            printReceipt(cartService, summary, logger);
        } else {
            logger.warning("Checkout failed, no receipt printed.");
        }
    }

    private static void printReceipt(CartService cartService, CheckoutSummary summary, Logger logger) {
        logger.info("** Checkout receipt **");
        for (CartItem item : cartService.getCartItems()) {
            String name = item.getProduct() != null ? item.getProduct().getName() : "Unknown";
            double totalPrice = item.getProduct().getPrice() * item.getQuantity();
            logger.info(String.format("%dx %s %.0f", item.getQuantity(), name, totalPrice));
        }
        logger.info("----------------------");
        logger.info(String.format("Subtotal %.2f", summary.getSubtotal()));
        logger.info(String.format("Shipping %.0f", summary.getShipping()));
        logger.info(String.format("Amount %.0f", summary.getTotalAmount()));
        logger.info(String.format("Balance after payment: %.0f", summary.getRemainingBalance()));
    }
}
