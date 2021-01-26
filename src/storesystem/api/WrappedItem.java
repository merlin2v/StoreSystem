/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storesystem.api;

/**
 * generic Item wrapper class
 * @author Nathan
 */
public class WrappedItem extends Item{
    /**
     * a value to indicate whether an object is registered or not
     */
    boolean Registered = false;
    /**
     * gets whether the value is a valid registered item
     * @return 
     */
    public boolean isRegistered(){
        return Registered;
    }
}
