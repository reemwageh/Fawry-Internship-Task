package src.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> cartItems = new ArrayList<>();

    public void add(Product product , int quantity){
        if(quantity<=0){
            System.out.println("Product Out Of Stock");
            return;
        }
        if (quantity > product.quantity){
            System.out.println("Not enough stock for product: " + product.name);
            return;
        }
        cartItems.add(new CartItem(product, quantity));
    }
    public boolean isEmpty(){
        return cartItems.isEmpty();
    }

    public List<CartItem> getCartItems(){
        return cartItems;
    }
    public double calculateSubtotal(){
        return cartItems.stream().mapToDouble(CartItem::getTotalPrice).sum();
    }
    public double calculateShipping(){
        double totalWeight = cartItems.stream().mapToDouble(CartItem::getWeight).sum();
        return totalWeight/1000.0*10;
    }
    public double calculateTotal(){
        return  calculateSubtotal()+calculateShipping();
    }
    public boolean hasExpiredItems(){
        return cartItems.stream().anyMatch(i->i.product.isExpired());
    }
    public boolean hasOutOfStockItems() {
        return cartItems.stream().anyMatch(i -> i.quantity > i.product.quantity);
    }

}
