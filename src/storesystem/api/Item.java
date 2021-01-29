/*
 * Dani
 */
package storesystem.api;

import java.io.Serializable;

/** 
 * A basic Item that can be registered using an {@link ItemRegistry}
 * @author Dani
 */
public class Item implements Serializable, Comparable<Item>{
	// fields
    String name;
    double price;
    
    //constructors

    Item(String name, double price) {
        this.name = name;
        this.price = price;
    }
    
    Item() {
    	this.name = "";
    	this.price = 0.0;
    }
    
    //getters

    /**
     * get's the name of an item
     * @return the name
     */
    public String getName() {
    	return name;
    }
    
    /**
     * gets the price of an item
     * @return the price
     */
    public double getPrice() {
    	return price;
    }
    
    //setters
    void setName(String newName) {
    	this.name = newName;
    }
    
    /**
     * Sets the price of the object
     * @param newPrice the new price
     */
    public void setPrice(double newPrice) {
    	this.price = newPrice;
    }

    /**
     * compares items using their names
     * @param o the item to compare to
     * @return the compare int
     */
    @Override
    public int compareTo(Item o) {
        return this.name.compareTo(o.name);
    }

    /**
     * Will find out if the object is equal in name
     * @param obj the object to test
     * @return if is equal
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Item) {
             return name.equals(((Item)obj).name);
        }
        return name.equals(obj);
    }
    
}
