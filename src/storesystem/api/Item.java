/*
 * Dani
 */
package storesystem.api;

import java.io.Serializable;

/** here is item info that you need to know
 *
 * @author Dani
 */
public class Item implements Serializable{
	// fields
    String name;
    double price;
    
    //constructors
    public Item (String name, double price) {
    	this.name = name;
    	this.price = price;
    }
    
    public Item() {
    	this.name = "";
    	this.price = 0.0;
    }
    
    //getters
    public String getName() {
    	return name;
    }
    
    public double getPrice() {
    	return price;
    }
    
    //setters
    public void setName(String newName) {
    	this.name = newName;
    }
    
    public void setPrice(double newPrice) {
    	this.price = newPrice;
    }
}
