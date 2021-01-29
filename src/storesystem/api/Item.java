/*
 * Dani
 */
package storesystem.api;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/** here is item info that you need to know
 *
 * @author Dani
 */
public class Item implements Serializable, Comparable<Item>{
	// fields
    String name;
    double price;
    
    /*Integer HashCode = null;
    
    
    private void writeObject(ObjectOutputStream oos) throws IOException{
        if (HashCode==null) {
            HashCode=super.hashCode();
        }
        oos.defaultWriteObject();
    }
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException{
        ois.defaultReadObject();
    }
    
    @Override      
    public int hashCode () {
        if (HashCode==null) {
            return super.hashCode();
        }
        return HashCode;
    }
*/
    //constructors
    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }
    
    public Item() {
    	this.name = "";
    	this.price = 0.0;
    }
    
    //getters
    public String getName() {
    	return name;
    }
    
    public double getPrice() {
    	return price;
    }
    
    //setters
    public void setName(String newName) {
    	this.name = newName;
    }
    
    public void setPrice(double newPrice) {
    	this.price = newPrice;
    }

    @Override
    public int compareTo(Item o) {
        return this.name.compareTo(o.name);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Item) {
             return name.equals(((Item)obj).name);
        }
        return name.equals(obj);
    }
    
}
