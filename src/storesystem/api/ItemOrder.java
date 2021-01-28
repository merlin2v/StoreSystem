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
    
    //constructors
    public ItemOrder(Item item, int quantity) {
    	this.item = item;
    	this.Quantity = quantity;
    }
    
    //getters
    public double getCost() {
    	return Quantity * item.getPrice();
    }
    
    public int getQuantity() {
    	return Quantity;
    }
    
    public String getName() {
    	return item.getName();
    }
    
    public Item getItem() {
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

    @Override
    public ItemOrder addTo(ItemOrder add) {
        if (!this.item.equals(add.item)) {
            throw new IllegalArgumentException("must be same item");
        }
        return new ItemOrder(item, add.Quantity+Quantity);
    }

    @Override
    public ItemOrder addTo(Number add) {
        return new ItemOrder(item, add.intValue() + Quantity);
    }

    @Override
    public ItemOrder subtractFrom(ItemOrder remove) {
        if (!this.item.equals(remove.item)) {
            throw new IllegalArgumentException("must be same item");
        }
        return new ItemOrder(item, Quantity-remove.Quantity);
    }

    @Override
    public ItemOrder subtractFrom(Number remove) {
        return new ItemOrder(item, Quantity-remove.intValue());
    }
}
