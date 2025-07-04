package src.service;

import src.model.Customer;
import src.model.Cart;
import src.model.CartItem;

public class CheckoutService {

    public static void checkout(Customer customer, Cart cart) {
        if (cart.isEmpty()) {
            System.out.println("Error: Cart is empty.");
            return;
        }

        if (cart.hasExpiredItems()) {
            System.out.println("Error: One or more items are expired.");
            return;
        }

        if (cart.hasOutOfStockItems()) {
            System.out.println("Error: One or more items are out of stock.");
            return;
        }

        double subtotal = cart.calculateSubtotal();
        double shipping = cart.calculateShipping();
        double total = subtotal + shipping;

        if (customer.balance < total) {
            System.out.println("Error: Insufficient balance.");
            return;
        }

        // Deduct quantities from products
        for (CartItem cartItem : cart.getCartItems()) {
            cartItem.product.quantity -= cartItem.quantity;
        }

        // Deduct money from customer
        customer.deductBalance(total);

        // Shipping
        ShippingService.ship(cart.getCartItems());

        // Print Receipt
        System.out.println("** Checkout receipt **");
        for (CartItem item : cart.getCartItems()) {
            System.out.printf("%dx %s %.0f\n", item.quantity, item.product.name, item.getTotalPrice());
        }
        System.out.println("----------------------");
        System.out.printf("Subtotal %.0f\n", subtotal);
        System.out.printf("Shipping %.0f\n", shipping);
        System.out.printf("Amount %.0f\n", total);
        System.out.printf("Balance after payment: %.0f\n", customer.balance);
    }
}
