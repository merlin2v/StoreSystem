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
    
    private HashMap<Item, Integer> inventory; 
    
    // CONSTRUCTORS //
    
    public Inventory() {
        
        this.inventory = new HashMap<Item, Integer>();
    }
    /**
     *  loads an Inventory object from a file
     * @param file the file to load from
     * @return The inventory object
     * @throws FileNotFoundException if the file cannot be found
     * @throws IOException if an i/o exception occurs
     */
    public static Inventory loadInventory(File file) throws FileNotFoundException, IOException {
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
    
    public void addItem(Item i, int q) {
        inventory.put(i, q);
    }

    public void addItem(ItemOrder order) {
        Item item = new Item(order.getName(), order.getCost());
        inventory.put(item, order.getQuantity());
    }
        
    public void addItem(Item i) {
        inventory.put(i, 1);
    }

    public void removeItem(Item i) {
        int q = inventory.get(i);
        inventory.put(i, q - 1);
        if (inventory.get(i) <= 0) {
            inventory.remove(i);
        }
    }

    public void removeItems(Item i, int q) {
        int current_q = inventory.get(i);
        inventory.put(i, current_q - q);
        if (inventory.get(i) <= 0) {
            inventory.remove(i);
        }
    }
    
    public void removeItems(ItemOrder order) {
        Item item = new Item(order.getName(), order.getCost());
        inventory.remove(item);
    }
    
    public boolean hasItem(Item i) {
        return inventory.containsKey(i);
    }

    public int getItem(Item i) {
        return inventory.get(i);
    }
    public ItemOrder getItemOrder(Item i) {
        ItemOrder item_order = new ItemOrder(i, inventory.get(i));
        return item_order;
    }
    
}
