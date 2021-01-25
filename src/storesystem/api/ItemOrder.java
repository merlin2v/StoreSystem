/*
 * Dani
 */
package storesystem.api;

/**
 *
 * @author Dani
 */
public class ItemOrder {
	//private fields
    private String item;
    public int Quantity;
    private double pricePerUnit;
    
    //constructors
    public ItemOrder(String item, int quantity, double pricePerUnit) {
    	this.item = item;
    	this.Quantity = quantity;
    	this.pricePerUnit = pricePerUnit;
    }
    
    //getters
    public double getCost() {
    	return Quantity * pricePerUnit;
    }
    
    //setters
    public void setQuantity() {
    	this.Quantity = Quantity;
    }
    
    //toString method
    public String toString() {
    	return Quantity + "of" + item;
    }
}
