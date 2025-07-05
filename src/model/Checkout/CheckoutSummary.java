package src.model.Checkout;

public class CheckoutSummary {
    private final double subtotal;
    private final double shipping;
    private final double totalAmount;
    private final double remainingBalance;

    public CheckoutSummary(double subtotal, double shipping, double totalAmount, double remainingBalance) {
        this.subtotal = subtotal;
        this.shipping = shipping;
        this.totalAmount = totalAmount;
        this.remainingBalance = remainingBalance;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getShipping() {
        return shipping;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public double getRemainingBalance() {
        return remainingBalance;
    }
}
