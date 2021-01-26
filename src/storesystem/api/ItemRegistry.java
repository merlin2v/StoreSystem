/*
 * 
 */
package storesystem.api;

import java.io.File;
import java.io.IOException;
import java.io.ObjectStreamException;
import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

import storesystem.api.deals.Deal;
/**
 *
 * @author natha
 */
public class ItemRegistry implements Serializable{
    @Serial
    
    //Private fields
    private TreeMap<String, Item> registeredItems; //TreeMap that holds registered items
    private Set<Deal> deals; //HashSet that holds deals
    private static String DEFAULT_REGISTRY_PATH;
    
    public int value;
    
    private void writeObject(java.io.ObjectOutputStream out)
     throws IOException{
        
    }
 private void readObject(java.io.ObjectInputStream in)
     throws IOException, ClassNotFoundException{
    
}
 private void readObjectNoData()
     throws ObjectStreamException{
    
}
 
 //Jenny's code
 protected ItemRegistry() {
	 registeredItems = new TreeMap<>();
	 deals = new HashSet<>();
 }
 
 public static ItemRegistry getDefaultItemRegistry() {
	 loadRegistry(new File(DEFAULT_REGISTRY_PATH));
 }
 
 public static ItemRegistry loadRegistry(File registry) {
	 
 }
 
 /**Returns a boolean depending on if the specified item is registered or not.
  * @param name String
  * @return boolean
  */
 public boolean isItemRegistered(String name) {
	 return registeredItems.containsKey(name);
 }
 
 /**Registers item into registry with only the name (no price listed).
  * @param name String
  */
 public void registerItem(String name)  { 
	 if (registeredItems.containsKey(name)) {
		 throw new ItemRegisteredException(); //Throws ItemRegisteredException if the registry already contains the item
	 } else {
	 registeredItems.put(name, new Item(name, 0.0));
	 }
 }
 
 /**Registers new item into registry with specified name and price.
  * @param name String
  * @param price double
  */
 public void registerItem(String name, double price) { 
	 if (registeredItems.containsKey(name)) {
		 throw new ItemRegisteredException(); //Throws ItemRegisteredException if the registry already contains the item
	 } else {
	 registeredItems.put(name, new Item(name, price));
	 }
 }
 
 /**Deletes the specified item from the registry.
  * @param name String
  */
 public void deleteItem(String name) { 
	 if (!registeredItems.containsKey(name)) {
		 throw new ItemNotFoundException(); //Throws ItemNotFoundException if specified item does not exist in registry
	 } else {
		 registeredItems.remove(name);
	 }
 }
 
 /**Returns an Item under specified Key (the name of the item).
  * @param name String
  * @return Item
  */
 public Item getItem(String name) { 
	 if (!registeredItems.containsKey(name)) {
		 throw new ItemNotFoundException(); //Throws ItemNotFoundException if specified item does not exist in registry
	 } else {
		 return registeredItems.get(name);
	 }
 }
 
 /**Changes specified item's price.
  * @param name String
  * @param price double
  */
 public void setPrice(String name, double price) { 
	 if (!registeredItems.containsKey(name)) {
		 throw new ItemNotFoundException(); //Throws ItemNotFoundException if specified item does not exist in registry
	 } else {
		 registeredItems.replace(name, new Item(name, price));
	 }
 }
 
 /**Returns price of specified item.
  * @param name String
  * @return double (the price of the item)
  */
 public double havePrice(String name) { 
	 if (!registeredItems.containsKey(name)) {
		 throw new ItemNotFoundException(); //Throws ItemNotFoundException if specified item does not exist in registry
	 } else {
		 return registeredItems.get(name).getPrice();
	 }
 }
 
 /**Registers specified deal.
  * @param d Deal
  */
 public void addDeal(Deal d) { 
	 if (deals.contains(d)) {
		 throw new ItemRegisteredException(); //Throws ItemRegisteredException if the deal is already registered
	 } else {
		 deals.add(d);
	 }
 }
 
 /**Removes a deal from the deals HashSet.
  * @param d Deal
  */
 public void removeDeal(Deal d) { 
	 if (!deals.contains(d)) {
		 throw new ItemNotFoundException(); //Throws ItemNotFoundException if the deal does not exist in the deals HashSet
	 } else {
		 deals.remove(d);
	 }
 }
}
