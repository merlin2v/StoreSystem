package storesystem.api;

/* Tracy Pham 
 * CS211 - Group #3 Project 1
 * January 20/2021
 * Group members: Donovan, Dani, Dat, Jenny, and Nathan
 */

import java.util.*;
import java.util.ArrayList;

/**
 * The shopping cart object. Can only be used once after completion of the 
 * transaction
 * @author Tracy Pham 
 */
public class ShoppingCart { 
    // Creates an array list with ItemOrder as the data type
    // and order as the parameter

    /**
     * the {@link ArrayList} of {@link ItemOrder} objects
     */
    public ArrayList<ItemOrder> shoppingcart = new ArrayList<ItemOrder>();

    /**
     * the Inventory object this will get data from
     */
    public final Inventory ShopInventory;
    /**
     * The tax to apply. default is 10%
     */
    public double TaxPercent = 0.1;

    private double total = Double.NaN;
    private double tax = Double.NaN;
    private CartDeals deals = null;
    private double finalTotal = Double.NaN;
    
    private boolean completed = false;

    /**
     * create the shopping cart object
     * @param ShopInventory the inventory object 
     */
    public ShoppingCart(Inventory ShopInventory) {
        this.ShopInventory = ShopInventory;
    }

    /**
     * Adds a shopping cart order according to the name
     * of the item
     * @param item the item to add
     * @throws storesystem.api.NotEnoughInOfStockException if not enough is in 
     * stock to purchase the selected quantity.
     */
    public void addItem(Item item) throws NotEnoughInOfStockException {
            addItems(item, 1);
    }
    /**
     * Adds an itemOrder to the shopping cart
     * of the item
     * @param addItemOrder the ItemOrder to add
     * @throws storesystem.api.NotEnoughInOfStockException if not enough is in 
     * stock to purchase the selected quantity.
     */
    public void addItems(ItemOrder addItemOrder) throws NotEnoughInOfStockException {
        if (completed) throw new CompletedTransactionException("Cannot complete add items.");
        
        Item item = addItemOrder.item;
        ItemOrder shopInv = ShopInventory.getItemOrder(item);
        
        
        if(hasItem(item)){
            ItemOrder thisItemOrder = getItemOrder(item);
            if (shopInv.Quantity<thisItemOrder.Quantity+addItemOrder.Quantity) {
                throw new NotEnoughInOfStockException(shopInv.Quantity);
            }
            resetValues();//reset values to recalculate
            thisItemOrder.Quantity += addItemOrder.Quantity;
        }else{
            if (shopInv.Quantity<addItemOrder.Quantity) {
                throw new NotEnoughInOfStockException(shopInv.Quantity);
            }
            resetValues();//reset values to recalculate
            shoppingcart.add(addItemOrder);
        }
    }
    
    /**
     * adds an item to the cart a {@code quantity} amount of times
     * @param item the item to add
     * @param quantity the quantity to add to cart
     * @throws storesystem.api.NotEnoughInOfStockException if not enough is in 
     * stock to purchase the selected quantity.
     */
    private void addItems(Item item, int quantity) throws NotEnoughInOfStockException {
        addItems(new ItemOrder(item,quantity));
    }
    
    /**
     * detects if an item is in the shopping cart
     * @param item the item to test for
     * @return whether the item was found or not
     */
    public boolean hasItem(Item item) {
        for(int index = 0; index < shoppingcart.size(); index++) {
            if(item.equals(shoppingcart.get(index).item))
                return true;
        }
        return false;
    }
    
    /**
     * Removes one specific item from shopping cart
     * @param item the item to remove
     * @return true if the item was removed
     */
    public boolean removeItem(Item item) {
            return removeItems(item, 1);
    }
    
    /**
     * Removes specific item from shopping cart
     * @param removeItemOrder itemOrder to remove will not remove if is 
     * bigger than what is in cart
     * @return whether it has been removed or not
     */
    public boolean removeItems(ItemOrder removeItemOrder) {
        if (completed) throw new CompletedTransactionException("Cannot complete remove items.");
        Item item = removeItemOrder.item;
        if(hasItem(item)){
            ItemOrder thisItemOrder = getItemOrder(item);
            ItemOrder newValue = thisItemOrder.subtractBy(removeItemOrder);
            if(newValue.Quantity < 0 ) return false; // do nothing if the item subtraction is bigger than what the cart has
            if(newValue.Quantity == 0) 
                shoppingcart.remove(thisItemOrder);
            else thisItemOrder.Quantity = newValue.Quantity; // we can set the value here because it is a refrence (I think)
            resetValues();//reset values to recalculate before returning true
            return true;
        }else return false;// do nothing if we don't have the item

    }

    /**
     * reset values to recalculate
     */
    protected void resetValues(){
        total = Double.NaN;//reset values to recalculate
        tax = Double.NaN;//reset values to recalculate
        deals = null;//reset values to recalculate
        finalTotal = Double.NaN;
    }
    
    
    /**
     * Removes specific amount of items from shopping cart. Will not remove if 
     * quantity exceeds the cart.
     * @param item the item to remove
     * @param quantity the number to remove
     * @return true if was removed
     */
    public boolean removeItems(Item item, int quantity) {
            return removeItems(new ItemOrder(item, quantity));
    }

    /**
     * calculates the tax if not already done so. this will call {@link calculateTotal()}.
     * WARNING: adding or removing objects will invalidate this.
     * @return the amount that tax will add
     */
    public double calculateTax(){
        if (Double.isNaN(tax)) {
            tax = calculateTotal() * TaxPercent;
            return tax;
        }else return tax;
    }
    /**
     * calculates the total with deals applied if not already done so.
     * WARNING: adding or removing objects will invalidate this.
     * @return the amount that tax will add
     */
    public double calculateTotal(){
        if (Double.isNaN(total)) {
            double totalCost = getTotalCost();
            deals = findDeals();
            total = totalCost - deals.getTotalReduction();
            return total;
        }else return total;
    }
    /**
     * finds out what deals are available at moment of call.
     * WARNING: adding or removing objects will invalidate this.
     * @return the Cart deals
     */
    public CartDeals findDeals(){
        if (deals==null) {
            deals = ShopInventory.Registry.runThroughDeals(this);
        }
        return deals;
    }
    /**
     * calculates the final total. This runs {@link calculateTotal()} and
     * {@link calculateTax()}.
     * WARNING: adding or removing objects will invalidate this.
     * @return the final total that will be payed for
     */
    public double calculateFinalTotal(){
        if (Double.isNaN(finalTotal)) {
            finalTotal = calculateTotal() + calculateTax();
        }
        return finalTotal;
    }
    
    /**
     * gets a unmodifiable list of the ItemOrders in this object
     * @return the unmodifiable list
     */
    public List<ItemOrder> getItemOrders(){
        return Collections.unmodifiableList(shoppingcart);
    } 
    /**
     * Complete the transaction
     * @return the deals that where applied. if any
     */
    public CartDeals completeTransaction(){
        if (completed) throw new CompletedTransactionException("Cannot complete transaction a second time.");
        for (ItemOrder itemOrder : shoppingcart) {
            ShopInventory.removeItems(itemOrder);
        }
        calculateFinalTotal();
        completed=true;
        return deals;
    }
    
    /**
     * returns whether or not this {@link ShoppingCart} has completed its transaction 
     * by running
     * @return true if completed 
     */
    public boolean isCompleted(){
        return completed;
    }
    
    /**
     * Searches for item in shopping cart
     * @param searchOrder order to search for
     * @return if the ItemOrder was found
     */
    public boolean searchItem(ItemOrder searchOrder) {
            boolean found = false;
            for(int index = 0; index < shoppingcart.size(); index++) {
                    if(searchOrder.equals(shoppingcart.get(index)))
                            found = true;
                    }
            return found;
    }


    /**
     * Get total cost of shopping cart
     * @return the sum of all the ItemOrder objects
     */
    public double getTotalCost() {
            double totalCost = 0;
            for(int index = 0; index < shoppingcart.size(); index++ ) {
                    ItemOrder itemorder = shoppingcart.get(index);
                    totalCost += itemorder.getCost();
                    }
            return totalCost;
            }

    /**
     * gets the ItemOrder object for the Item 
     * @param item the item to use to find the ItemOrder
     * @return the ItemOrder that was found
     */
    public ItemOrder getItemOrder(Item item) {
        for(int index = 0; index < shoppingcart.size(); index++) {
            if(item.equals(shoppingcart.get(index).item))
                return shoppingcart.get(index);
            }
        return null;
    }

    /**
     * to string
     */
    @Override
    public String toString() 
    {
    return "Items in cart: " + shoppingcart.toString() + " Total Cost: " + this.getTotalCost();
    }

}	
	
