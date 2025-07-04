package src.model;

public abstract class Product {
    public String name;
    public double price;
    public int quantity;
    public boolean expired;

    public Product(String name, double price, int quantity, boolean expired) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.expired = expired;
    }

    public boolean isExpired(){
        return expired;
    }
    public abstract boolean requiresShipping();
}
