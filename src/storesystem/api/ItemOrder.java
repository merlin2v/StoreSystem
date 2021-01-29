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

    /**
     * Creates an {@code ItemOrder}
     * @param item the item of this {@code ItemOrder}
     * @param quantity the quantity of the {@code Item}
     */
    public ItemOrder(Item item, int quantity) {
    	this.item = item;
    	this.Quantity = quantity;
    }
    
    //getters

    /**
     * get the total cost of the {@code ItemOrder}
     * @return the total cost
     */
    public double getCost() {
    	return Quantity * item.getPrice();
    }
    
    /**
     * get the quantity of {@code Item}s in this {@code ItemOrder}
     * @return the quantity
     */
    public int getQuantity() {
    	return Quantity;
    }
    
    /**
     * get the name of the {@code Item}
     * @return the name
     */
    public String getName() {
    	return item.getName();
    }
    
    /**
     * gets the {@code Item} Object for this item
     * @return the {@code Item} Object
     */
    public Item getItem() {
    	return item;
    }
    
    //setters

    /**
     * sets the quantity 
     * @param Quantity the quantity to set to
     */
    public void setQuantity(int Quantity) {
    	this.Quantity = Quantity;
    }
    
    //toString method

    /**
     * creates a string representing this object
     * @return the object 
     */
    @Override
    public String toString() {
    	return Quantity + "of" + item;
    }

    /**
     * add this to another {@code ItemOrder}
     * @param add the value to add to this
     * @return the sum of the two ItemOrders
     */
    @Override
    public ItemOrder addTo(ItemOrder add) {
        if (!this.item.equals(add.item)) {
            throw new IllegalArgumentException("must be same item");
        }
        return new ItemOrder(item, add.Quantity+Quantity);
    }

    /**
     * increase this Quantity by {@code add}
     * @param add the value to add to this
     * @return the sum of the ItemOrder and the number
     */
    @Override
    public ItemOrder addTo(Number add) {
        return new ItemOrder(item, add.intValue() + Quantity);
    }
    
    /**
     * subtract this by another {@code ItemOrder}
     * @param remove the value to remove from this
     * @return the difference of the two {@code ItemOrder}s
     */
    @Override
    public ItemOrder subtractBy(ItemOrder remove) {
        if (!this.item.equals(remove.item)) {
            throw new IllegalArgumentException("must be same item");
        }
        return new ItemOrder(item, Quantity-remove.Quantity);
    }

    /**
     * subtract this by {@code remove}
     * @param remove the value to remove from this
     * @return the difference between {@code this} and {@code remove}
     */
    @Override
    public ItemOrder subtractBy(Number remove) {
        return new ItemOrder(item, Quantity-remove.intValue());
    }
}
