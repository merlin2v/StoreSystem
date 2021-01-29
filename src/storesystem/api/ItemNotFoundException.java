package storesystem.api;

/**
 * An item thrown when an item cannot be found
 * @author Jenny
 */
@SuppressWarnings("serial")
public class ItemNotFoundException extends RuntimeException {
    private String Message = null;
    /**
     * creates an ItemNotFoundException
     */
    public ItemNotFoundException() {
            getMessage();
    }

    /**
     * creates an ItemNotFoundException with a message
     * @param message the message
     */
    public ItemNotFoundException(String message) {
        Message = message;
    }
	
    /**
     * returns {@code "item not found."} and appends an additional message if any
     * @return the massage of why the exception was thrown
     */
    public String getMessage() {
        return "Item not found."+((Message!=null)?Message:"");
    }
}
