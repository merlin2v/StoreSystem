/*
 * 
 */
package storesystem.api;

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
    private TreeMap<String, Item> registry = new TreeMap<>(); //TreeMap that holds registered items
    private Set<Deal> deals = new HashSet<>(); //HashSet that holds deals
    
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
 public void registerItem(String name)  { //Registers item into registry with only the name (no price listed)
	 if (registry.containsKey(name)) {
		 throw new ItemRegisteredException(); //Throws ItemRegisteredException if the registry already contains the item
	 } else {
	 registry.put(name, new Item(name, 0.0));
	 }
 }
 
 public void registerItem(String name, double price) { //Registers new item into registry with specified name and price
	 if (registry.containsKey(name)) {
		 throw new ItemRegisteredException(); //Throws ItemRegisteredException if the registry already contains the item
	 } else {
	 registry.put(name, new Item(name, price));
	 }
 }
 
 public void deleteItem(String name) { //Deletes the specified item from the registry
	 if (!registry.containsKey(name)) {
		 throw new ItemNotFoundException(); //Throws ItemNotFoundException if specified item does not exist in registry
	 } else {
		 registry.remove(name);
	 }
 }
 
 public Item getItem(String name) { //Returns Item under specified Key (the name of the item)
	 if (!registry.containsKey(name)) {
		 throw new ItemNotFoundException();
	 } else {
		 return registry.get(name);
	 }
 }
 
 public void setPrice(String name, double price) { //Changes specified item's price
	 if (!registry.containsKey(name)) {
		 throw new ItemNotFoundException(); //Throws ItemNotFoundException if specified item does not exist in registry
	 } else {
		 registry.replace(name, new Item(name, price));
	 }
 }
 
 public double havePrice(String name) { //Returns price of specified item
	 if (!registry.containsKey(name)) {
		 throw new ItemNotFoundException(); //Throws ItemNotFoundException if specified item does not exist in registry
	 } else {
		 return registry.get(name).getPrice();
	 }
 }
 
 public void addDeal(Deal d) { //Records Specified deal
	 if (deals.contains(d)) {
		 throw new ItemRegisteredException(); //Throws ItemRegisteredException if the deal is already registered
	 } else {
		 deals.add(d);
	 }
 }
 
 public void removeDeal(Deal d) { //Removes a deal from the deals HashSet
	 if (!deals.contains(d)) {
		 throw new ItemNotFoundException(); //Throws ItemNotFoundException if the deal does not exist in the deals HashSet
	 } else {
		 deals.remove(d);
	 }
 }
}
