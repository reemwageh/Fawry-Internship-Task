import src.model.*;
import src.service.CheckoutService;

public class Main {
    public static void main(String[] args) {
        // 1. Define products
        Product cheese = new ShippableProduct("Cheese", 100, 5, false, 200);       // 200g
        Product biscuits = new ShippableProduct("Biscuits", 150, 3, false, 700);   // 700g
        Product scratchCard = new DigitalProduct("Scratch Card", 50, 10, false);   // no weight

        // 2. Create a customer
        Customer customer = new Customer("Reem", 1000); // balance: 1000

        // 3. Create a cart
        Cart cart = new Cart();

        // 4. Add items to the cart
        cart.add(cheese, 2);        // 2 x Cheese (200g each)
        cart.add(biscuits, 1);      // 1 x Biscuits (700g)
        cart.add(scratchCard, 1);   // 1 x Scratch Card

        // 5. Perform checkout
        CheckoutService.checkout(customer, cart);
    }
}
