/*
 * 
 */
package storesystem.api.deals;

import java.io.Serializable;
import java.util.function.BiPredicate;
import storesystem.api.ShoppingCart;

/**
 *
 * @author Nathan
 */
public interface CartPredictate extends Serializable, BiPredicate<ShoppingCart, Boolean> {
    
}
