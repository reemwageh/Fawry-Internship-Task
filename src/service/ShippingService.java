package src.service;

import src.model.CartItem;

import java.util.List;

public class ShippingService {
    public static void ship(List<CartItem> items) {
        System.out.println("** Shipment notice **");

        double totalWeight = 0;

        for (CartItem item : items) {
            if (item.isShippable()) {
                double weight = item.getWeight();
                totalWeight += weight;

                System.out.printf("%dx %s %.0fg\n", item.quantity, item.product.name, weight);
            }
        }

        System.out.printf("Total package weight %.1fkg\n", totalWeight / 1000.0);
    }
}