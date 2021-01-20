/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
