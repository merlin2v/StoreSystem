/*
 * Dani
 */
package storesystem.api;

import java.io.Serializable;

/**
 *
 * @author Dani
 */
public class ItemOrder implements Serializable, Countable<ItemOrder>{
	//fields
    Item item;
    int Quantity;
    private double pricePerUnit;
    
    //constructors
    public ItemOrder(Item item, int quantity, double pricePerUnit) {
    	this.item = item;
    	this.Quantity = quantity;
    	this.pricePerUnit = pricePerUnit;
    }
    
    //getters
    public double getCost() {
    	return Quantity * pricePerUnit;
    }
    
    public int getQuantity() {
    	return Quantity;
    }
    
    public String getName() {
    	return Item.Name;
    }
    
    public String getItem() {
    	return item;
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
