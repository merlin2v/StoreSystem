/*
 *  
 */
package storesystem.api;

/**
 * An exception that is throw to let the user know that there is not enough of that 
 * item left in the {@link Inventory} to purchase the specified quantity
 * @author Nathan
 */
public class NotEnoughInOfStockException extends OutOfStockException {

    /**
     * An exception that lets the user know that there is not enough of that 
     * item left to purchase a set quantity
     */
    public NotEnoughInOfStockException() {
    }

    /**
     * The quantity in the stock. May be {@code null}
     */
    public Integer StockQuantity=null;
    /**
     * An exception that lets the user know that there is not enough of that 
     * item left to purchase a set quantity
     * @param Quantity the quantity left available in stock
     */
    public NotEnoughInOfStockException(int Quantity) {
        StockQuantity = Quantity;
    }
    
    /**
     * gets a message of the exception and appends the current stock if 
     * {@link StockQuantity} is not {@code null}
     * @return the message
     */
    @Override
    public String getMessage() {
        return "Not Enough in Stock"+((StockQuantity!=null)?" ("+StockQuantity+")":"");
    }
}
