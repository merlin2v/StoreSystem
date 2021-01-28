package storesystem.api;

/* Tracy Pham 
 * CS211 - Group #3 Project 1
 * January 20/2021
 * Group members: Donovan, Dani, Dat, Jenny, and Nathan
 */

import java.util.*;
import java.util.ArrayList;

public class ShoppingCart { 
	// Creates an array list with ItemOrder as the data type
	// and order as the parameter
	public ArrayList<ItemOrder> shoppingcart = new ArrayList<ItemOrder>();
	
	// Adds a shopping cart order according to the name
	// of the item
	public void add(ItemOrder itemOrder) {
		shoppingcart.add(itemOrder);
	}
	
	// Removes specific item from shopping cart
	public boolean remove(ItemOrder removeItemOrder) {
		Item item = removeItemOrder.item;
                if(hasItem(item)){
                    ItemOrder thisItemOrder = getItemOrder(item);
                    ItemOrder newValue = thisItemOrder.subtractFrom(removeItemOrder);
                    if(newValue.Quantity < 0 ) return false; // do nothing if the item subtraction is bigger than what the cart has
                    if(newValue.Quantity == 0) 
                        shoppingcart.remove(thisItemOrder);
                    else thisItemOrder.Quantity = newValue.Quantity; // we can set the value here because it is a refrence (I think)
                    return true;
                }else return false;// do nothing if we don't have the item
                
	}
	
	// Removes the given item from this list
	public boolean remove(Item item, int quantity) {
		return remove(new ItemOrder(item, quantity));
	}
	
	// Searches for item in shopping cart
	public boolean searchItem(ItemOrder searchOrder) {
		boolean found = false;
		for(int index = 0; index < shoppingcart.size(); index++) {
			if(searchOrder.equals(shoppingcart.get(index)))
				found = true;
			}
		return found;
	}
	
	
	// Get total cost of shopping cart
	public double getTotalCost() {
		double totalCost = 0;
		for(int index = 0; index < shoppingcart.size(); index++ ) {
			ItemOrder itemorder = shoppingcart.get(index);
			totalCost += itemorder.getCost();
			}
		return totalCost;
		}
        
	//System.out.println("Total amount of payment is $" + (int)totalCost);

    public boolean hasItem(Item item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ItemOrder getItemOrder(Item item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}	
	
