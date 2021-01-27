/* Tracy Pham 
 * CS211 - Group #3 Project 1
 * January 20/2021
 * Group members: Donovan, Dani, Dat, Jenny, and Nathan
 */

import java.util.*;

public class ShoppingCart { 
	// Creates an array list with ItemOrder as the data type
	// and order as the parameter
	public ArrayList<ItemOrder> shoppingcart = new ArrayList<ItemOrder>();
	
	// Adds an item to shopping cart
	public void add(ItemOrder itemOrder) {
		shoppingcart.add(itemOrder);
	}
	
	// Removes specific item from shopping cart
	public void remove(ItemOrder removeItemOrder) {
		shoppingcart.remove(removeItemOrder);
	}
	
	// s
	public boolean searchItem(ItemOrder searchOrder) {
		boolean found = false;
		for(int index = 0; index < shoppingcart.size(); index++) {
			if(searchOrder.equals(shoppingcart.get(index)))
				found = true;
			}
		return found;
	}

	public double getTotalCost() {
		double totalCost = 0;
		for(int index = 0; index < shoppingcart.size(); index++ ) {
			ItemOrder itemorder = shoppingcart.get(index);
			totalCost += itemorder.getItemOrderCost();
			}
		return totalCost;
		}

	}	
	
