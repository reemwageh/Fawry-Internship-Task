package src.model;

public class DigitalProduct extends Product{
    public DigitalProduct(String name, double price, int quantity, boolean expired) {
        super(name, price, quantity, expired);
    }
    @Override
    public boolean requiresShipping() {
        return false;
    }
}
