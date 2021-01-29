/*
 * 
 */
package storesystem.api;

import java.io.*;
import java.util.*;
import storesystem.api.deals.*;
/**
 * Controls the valid {@link Item}s as well as creates and stores them.  
 * Also in charge of Deal Objects.
 * @author Jenny
 */
public class ItemRegistry implements Serializable{
    
    //Private fields
    private TreeMap<String, Item> registeredItems; //TreeMap that holds registered items
    private Set<Deal> deals; //HashSet that holds deals
    private static String DEFAULT_REGISTRY_PATH = "data/registry.obj"; 

    /**
     * creates a new blank {@code ItemRegistry} object
     */
    protected ItemRegistry() {
	 registeredItems = new TreeMap<>();
	 deals = new HashSet<>();
 }
 
    /**
     * Loads a Registry from a given file object.
     * @param file the file where the object was serialized 
     * @return the item registry ItemRegistry
     * @throws FileNotFoundException if the file cannot be found
     * @throws IOException if an i/o exception occurs
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
     * @return gets the default registry. If none can be found, one will be created.
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
     * @throws java.io.FileNotFoundException if the file cannot be found
     * @throws java.io.IOException if an i/o exception occurs
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
  * @return {@code registeredItems.keySet(); Set<String>}
  */
 public Set<String> getListOfItems() {
	 return registeredItems.keySet();
 }
 /**Registers item into registry with only the name (no price listed).
  * @param name String
  * @throws ItemRegisteredException if the item has already been registered 
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
 
  /**Returns if the key can be found (the name of the item).
  * @param name String
  * @return Item
  */
 public boolean hasItem(String name) { 
	 return registeredItems.containsKey(name);
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
 
    /**
     * Runs through all of the deals and checks for valid deals
     * @param cart  the cart object to check for deals
     * @return the {@link CartDeals} object that contains the deals
     * @see CartDeals
     */
    public CartDeals runThroughDeals(ShoppingCart cart){
        CartDeals oDeal = new CartDeals();
        Iterator<Deal> iterator = deals.iterator();
        while (iterator.hasNext()) {
            Deal next;
               next =  iterator.next();
               next.reset();
            DealObject tryReceive;
            while((tryReceive = next.tryReceive(cart))!=null){
                oDeal.add(tryReceive);
            }
        }
        return oDeal;
    }
    /**
     * Wrap Item in an extension object
     * @param name creates the name for the object
     * @param item the template item to store extra variables in
     */
    public void registerWrappedItem(String name, WrappedItem item){
        if (isItemRegistered(name) || item.isRegistered()) {
            throw new ItemRegisteredException();
        }
        item.name = name;
        item.price = 0.0;
        registeredItems.put(name, item);
        item.Registered = true;
    }
}
