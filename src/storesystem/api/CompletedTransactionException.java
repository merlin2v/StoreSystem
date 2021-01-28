/*
 *  
 */
package storesystem.api;

/**
 * An exception thrown when the transaction for a {@link ShoppingCart} has been 
 * completed and a change was attempted.
 * @author Nathan
 */
public class CompletedTransactionException extends RuntimeException {

    public CompletedTransactionException() {
    }
    private String Explain=null;
    CompletedTransactionException(String explain) {
        Explain = explain;
    }
    @Override
    public String getMessage() {
        return ((Explain==null)?"Cannot do action.":Explain)+" Transaction is completed.";
    }
}
