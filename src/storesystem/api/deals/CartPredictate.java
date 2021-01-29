/*
 * 
 */
package storesystem.api.deals;

import java.io.Serializable;
import java.util.function.BiPredicate;
import storesystem.api.ShoppingCart;

/**
 * Represents a serializable predicate.
 * the first parameter is the {@link ShoppingCart} that is getting the {@link Deal}
 * applied to them and the second is if the method should skip on any recording
 * anything to the {@link Deal} or not. {@code true} means don't take records of
 * the method running as it is currently peeking to see what the value will be.
 * It is true when {@link Deal#peekPredictate(ShoppingCart) peekPredictate()}
 * is used.
 * @author Nathan
 */
public interface CartPredictate extends Serializable, BiPredicate<ShoppingCart, Boolean> {
    
}
