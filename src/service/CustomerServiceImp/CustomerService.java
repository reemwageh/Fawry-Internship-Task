package src.service.CustomerServiceImp;

import src.model.Customer.Customer;
import java.util.logging.Logger;

public class CustomerService {
    private static final Logger logger = Logger.getLogger(CustomerService.class.getName());

    public Boolean hasSufficientBalance(Customer customer, Double amount) {
        if (customer == null || customer.getBalance() == null) {
            logger.warning("Invalid customer or null balance.");
            return false;
        }
        return customer.getBalance() >= amount;
    }

    public Boolean deductBalance(Customer customer, Double amount) {
        if (amount == null || amount < 0) {
            logger.warning("Invalid deduction amount: " + amount);
            return false;
        }
        if (!hasSufficientBalance(customer, amount)) {
            logger.warning("Insufficient balance.");
            return false;
        }
        customer.setBalance(customer.getBalance() - amount);
        return true;
    }
}
