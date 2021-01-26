/*
 * 
 */
package storesystem.api;

import java.io.*;
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
    private static String DEFAULT_REGISTRY_PATH = "data/registry.obj";
    
    public int value;
    


 protected ItemRegistry() {
	 registeredItems = new TreeMap<>();
	 deals = new HashSet<>();
 }
 
    /**
     * Loads a Registry from a given file object.
     * @param file the file where the object was serialized 
     * @return the item registry ItemRegistry
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public static ItemRegistry loadRegistry(File file) throws FileNotFoundException, IOException {
        FileInputStream fin = new FileInputStream(file);
        ObjectInputStream in = new ObjectInputStream(fin);
        ItemRegistry r;
        try {
            r = (ItemRegistry) in.readObject();
        } catch (ClassNotFoundException ex) {
            throw new Error(ex);// this should never happen
        }
        in.close();
        fin.close();
        return r;
    }

    /**
     * gets the default registry 
     * @return
     */
    public static ItemRegistry getDefaultRegistry(){
        try {
            return loadRegistry(new File(DEFAULT_REGISTRY_PATH));
        } catch (IOException ex) {
            System.err.println("No Registry was found, generating blank registry.");
            return new ItemRegistry();
        }
    }
    
    /**
     * this saves the {@link ItemRegistry} as a {@link Serializable} object
     * @param f the file to serialize to
     * @throws java.io.FileNotFoundException
     * @throws java.io.IOException
     * @see Serializable
     */
    public void save(File f) throws FileNotFoundException, IOException{
        FileOutputStream fout = new FileOutputStream(f);
        ObjectOutputStream out = new ObjectOutputStream(fout);
        out.writeObject(this);
        out.close();
    }
    
    //Jenny's code
    
 /**Returns a boolean depending on if the specified item is registered or not.
  * @param name String
  * @return boolean
  */
 public boolean isItemRegistered(String name) {
	 return registeredItems.containsKey(name);
 }
 
 /**Returns a Set containing all of the item names in the registry.
  * @return registeredItems.keySet(); Set<String>
  */
 public Set<String> getListOfItems() {
	 return registeredItems.keySet();
 }
 /**Registers item into registry with only the name (no price listed).
  * @param name String
  * @throws ItemRegisteredException
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
