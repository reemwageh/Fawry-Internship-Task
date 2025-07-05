package src.service.ShippingServiceImp;

import src.model.Cart.CartItem;
import src.service.CartServiceImp.CartItemService;

import java.util.List;
import java.util.logging.Logger;

public class ShippingService {
    private static final Logger logger = Logger.getLogger(ShippingService.class.getName());

    public static void ship(List<CartItem> items, CartItemService cartItemService) {
        logger.info("** Shipment notice **");

        Double totalWeight = 0.0;

        for (CartItem item : items) {
            if (Boolean.TRUE.equals(cartItemService.isShippable(item))) {
                Double itemWeight = cartItemService.getWeight(item);
                totalWeight += itemWeight;

                logger.info(String.format(
                        "%dx %s %.0fg",
                        item.getQuantity(),
                        item.getProduct().getName(),
                        itemWeight
                ));
            }
        }

        logger.info(String.format("Total package weight %.1fkg", totalWeight / 1000.0));
    }
}
