package src.model;

public class Customer {
    public String name;
    public double balance;

    public Customer(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    public void deductBalance(double amount) {
        this.balance -= amount;
    }
}
