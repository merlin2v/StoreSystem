/*
 *  
 */
package storesystem.api.deals;

import java.io.Serializable;
import storesystem.api.*;

/**
 * Represents a Receivable object in {@link Deal}
 * @author Nathan
 * @param <T> the Receivable object for this Deal
 */
public abstract class DealObject<T> implements Serializable{

    
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
