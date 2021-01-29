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

    /**
     * creates a CompletedTransactionException with no explanation 
     */
    public CompletedTransactionException() {
    }
    private String Explain=null;
    /**
     * creates a CompletedTransactionException with an explanation 
     * @param explain the explanation
     */
    public CompletedTransactionException(String explain) {
        Explain = explain;
    }

    /**
     * get a message that indicates why an exception has been thrown
     * @return a message explaining the reason for the exception 
     */
    @Override
    public String getMessage() {
        return ((Explain==null)?"Cannot do action.":Explain)+" Transaction is completed.";
    }
}
