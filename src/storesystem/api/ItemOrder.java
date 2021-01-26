/*
 * Dani
 */
package storesystem.api;

/**
 *
 * @author natha
 */
public class ItemOrder {
	//private fields
    public String item;
    public int quantity;
    private double pricePerUnit;
    
    //constructors
    public ItemOrder(String item, int quantity, double pricePerUnit) {
    	this.item = item;
    	this.quantity = quantity;
    	this.pricePerUnit = pricePerUnit;
    }
    
    //getters
    public double getCost() {
    	return quantity * pricePerUnit;
    }
    
    //setters
    public void setQuantity() {
    	this.quantity = quantity;
    }
    
    //toString method
    public String toString() {
    	return quantity + "of" + item;
    }
}
