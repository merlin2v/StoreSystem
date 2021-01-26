/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storesystem.api;

import java.io.*;

/**
 *
 * @author natha
 */
// WOOOOOOOOOOOOOOOOOOOOO
public class Inventory implements Serializable{

    /**
     *  loads an Inventory object from a file
     * @param file
     * @return
     * @throws FileNotFoundException
     * @throws IOException 
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
    
    public void addItem(Item i, int q) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void addItem(Item i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean removeItem(Item i) {
        return removeItem(i,1);
    }

    public boolean removeItem(Item i, int q) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ItemOrder getItemOrder(Item i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
