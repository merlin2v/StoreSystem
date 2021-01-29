/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storesystem.api;

import java.io.*;
import java.util.*;
/**
 *
 * @author natha
 */

public class Inventory implements Serializable{
    
    // FIELDS //

    
    
    /**
     * the registry of this object
     */
    
    public transient ItemRegistry Registry;
    private TreeMap<Item, ItemOrder> inventory; 
    
    // CONSTRUCTORS //
    
    public Inventory(ItemRegistry reg) {
        this.Registry = reg;
        this.inventory = new TreeMap<Item, ItemOrder>();
    }
    
    /**
     *  loads an Inventory object from a file
     * @param file the file to load from
     * @param reg the registry to assign to this
     * @return The inventory object
     * @throws FileNotFoundException if the file cannot be found
     * @throws IOException if an i/o exception occurs
     */
    public static Inventory loadInventory(File file, ItemRegistry reg) throws FileNotFoundException, IOException {
        FileInputStream fin = new FileInputStream(file);
        ObjectInputStream in = new ObjectInputStream(fin);
        Inventory r;
        try {
            r = (Inventory) in.readObject();
        } catch (ClassNotFoundException ex) {
            throw new Error(ex); // this should never happen
        }
        in.close();
        fin.close();
        r.Registry = reg;
        return r;
    }
    
    
    
    /**
     * this saves the {@link Inventory} as a {@link Serializable} object
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
    
    /**
     * Adds one item
     * @param i the item to add
     * @param q the quantity to add
     */
    public void addItems(Item i, int q) {
        addItems(new ItemOrder(i, q));
    }

    /**
     * adds ItemOrders to the inventory
     * @param order the {@code ItemOrder} with the items to add
     */
    public void addItems(ItemOrder order) {
        if(!Registry.hasItem(order.getName())) throw new ItemNotFoundException("Item not found in registry");
        if (order.getName().equals(Registry.getItem(order.getName())) && 
                order.getItem() != Registry.getItem(order.getName())) 
            throw new ItemNotFoundException("Item in registry does not match");
        
        if(inventory.containsKey(order.getItem())){
            inventory.put(order.getItem(), inventory.get(order.item).addTo(order));
        }else inventory.put(order.getItem(), order);
    }
        
    /**
     * Adds a single item to the inventory
     * @param i the item to add
     */
    public void addItem(Item i) {
        addItems(i, 1);
    }
    /**
     * removes a single item
     * @param i the item to remove
     * @return if the item has been removed or not
     */
    public boolean removeItem(Item i) {
        return removeItems(i, 1);
    }

    /**
     * Remove items from the inventory
     * @param i the item to remove
     * @param q the quality 
     * @return if this has been successful
     */
    public boolean removeItems(Item i, int q) {
        return removeItems(new ItemOrder(i,q));
    }
    
    public boolean removeItems(ItemOrder order) {
        if(!Registry.hasItem(order.getName())) throw new ItemNotFoundException("Item not found in registry");
        if (order.getName().equals(Registry.getItem(order.getName())) && 
                order.getItem() != Registry.getItem(order.getName())) 
            throw new ItemNotFoundException("Item in registry does not match");
        if(inventory.containsKey(order.getItem())){
            ItemOrder inv = inventory.get(order.item);
            ItemOrder ninv = inv.subtractFrom(order);
            if (ninv.Quantity <= 0) {
                inventory.remove(order.item);
            }else inventory.put(order.getItem(), ninv);
            return true;
        }else return false;
    }
    
    public boolean hasItem(Item i) {
        return inventory.containsKey(i);
    }

    public int getItem(Item i) {
        return inventory.get(i).Quantity;
    }
    
    /**
     *  Gets an ItemOrder object
     * @param i the item to find
     * @return an item order representing this object
     * @throws ItemNotFoundException thrown when Item is not found in registry
     */
    public ItemOrder getItemOrder(Item i) throws ItemNotFoundException{
        if(!Registry.hasItem(i.name)) throw new ItemNotFoundException("Item not found in registry");
        if (i.getName().equals(Registry.getItem(i.name)) && 
                i != Registry.getItem(i.name)) 
            throw new ItemNotFoundException("Item in registry does not match");
        if(inventory.containsKey(i)){
            ItemOrder item_order = inventory.get(i);
            return item_order;
        }else return  new ItemOrder(i, 0);
    }
    
}
