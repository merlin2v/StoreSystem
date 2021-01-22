package api;

@SuppressWarnings("serial")
public class ItemRegisteredException extends RuntimeException {
	

	public ItemRegisteredException() {
		getMessage();
	}
	
	public String getMessage() {
		return "Item is already registered";
	}
}
