/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storesystem.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author natha
 */
public class Inventory {

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
