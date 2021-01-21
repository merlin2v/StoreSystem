/*
 * 
 */
package storesystem.api;

import java.util.function.Predicate;

/**
 *
 * @author natha
 */
public class Deal {
    /** the check to see if the deal is valid and can be applied.
     * to use:
     *  {@code Predicate<Item[]> predictate = (items) -> {return items.length < 2;};}
     */
    public Predicate<Item[]> p;//TODO find better name
    
}
