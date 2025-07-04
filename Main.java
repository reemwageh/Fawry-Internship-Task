import src.model.*;
import src.service.CheckoutService;

public class Main {
    public static void main(String[] args) {
        Product cheese = new ShippableProduct("Cheese", 100, 5, false, 200);       // 200g
        Product biscuits = new ShippableProduct("Biscuits", 150, 3, false, 700);   // 700g
        Product scratchCard = new DigitalProduct("Scratch Card", 50, 10, false);   // no weight
        Customer customer = new Customer("Reem", 1000); // balance: 1000
        Cart cart = new Cart();
        cart.add(cheese, 2);
        cart.add(biscuits, 1);
        cart.add(scratchCard, 1);
        CheckoutService.checkout(customer, cart);
    }
}
