package storesystem.api;

/**
 * An exception that lets the tester know that an item is already registered
 * @author Jenny
 */
@SuppressWarnings("serial")
public class ItemRegisteredException extends RuntimeException {
	
	/**
	 * An exception that lets the tester know that an item is already registered
	 * 
	 */
	public ItemRegisteredException() {
		getMessage();
	}
	
	/**
	 * Replaces super message with "Item is already registered"
	 * @return "Item is already registered" String
	 */
	public String getMessage() {
		return "Item is already registered";
	}
}
