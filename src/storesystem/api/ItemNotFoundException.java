package storesystem.api;

@SuppressWarnings("serial")
public class ItemNotFoundException extends RuntimeException {
	
	public ItemNotFoundException() {
		getMessage();
	}
	
	public String getMessage() {
		return "Item not found";
	}

}
