/*
 * 
 */
package storesystem.api;

import java.io.IOException;
import java.io.ObjectStreamException;
import java.io.Serial;
import java.io.Serializable;
/**
 *
 * @author natha
 */
public class ItemRegistry implements Serializable{
    @Serial
    
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
 public void registerItem(String name) 
	 throws ItemRegisteredException {
 }
 
 public void registerItem(String name, double price) 
	 throws ItemRegisteredException {
 }
 
 public void deleteItem(String name) {
	 
 }
 
 public Item getItem(String name) {
	 throw new UnsupportedOperationException("Not supported yet."); //TODO implement
 }
 
 public void setPrice(String name, double price) {
	 
 }
 
 public void havePrice() {
	 
 }
 
 public void addDeal() {
	 
 }
 
 public void removeDeal() {
	 
 }


    public boolean isItemRegistered(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //TODO implement
    }
}
