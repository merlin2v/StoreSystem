/*
 * 
 */
package storesystem.api;

import java.io.*;
/**
 *
 * @author natha
 */
public class ItemRegistry implements Serializable{

    private static String DEFAULT_REGISTRY_PATH;

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
