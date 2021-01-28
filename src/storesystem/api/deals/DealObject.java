/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storesystem.api.deals;

import storesystem.api.*;

/**
 * Represents a Receivable object in {@link Deal}
 * @author Nathan
 */
public abstract class DealObject<T> {

    
    /**
     * if the price is reduced 
     * @return if the price should be reduced
     */
    public abstract boolean isReduction();
    /**
     * if the price is reduced get the reduction value
     * @return the Receivable object for this {@code DealObject}
     */
    public abstract T getReceivable();
    
    /**
     * get string representation of this object
     * @return a string
     */
    public abstract String getMessage();
}
