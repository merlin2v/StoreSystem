package storesystem.api;

@SuppressWarnings("serial")
public class ItemNotFoundException extends RuntimeException {
	private String Message = null;
	public ItemNotFoundException() {
		getMessage();
	}

    ItemNotFoundException(String message) {
        Message = message;
    }
	
	public String getMessage() {
		return "Item not found"+((Message!=null)?Message:"");
	}

}
