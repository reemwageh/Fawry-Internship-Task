package src.model.Customer;

public class Customer {
    private String name;
    private Double balance;

    public Customer(String name, Double balance) {
        this.name = name != null ? name : "Unnamed Customer";
        this.balance = balance != null ? balance : 0.0;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Double getBalance() { return balance; }
    public void setBalance(Double balance) { this.balance = balance; }
}
