/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storesystem.api;

/**
 * An exception that is throw to let the user know that there is none of that 
 * item left in the {@link Inventory}
 * @author Nathan
 */
public class OutOfStockException extends Exception {

    /**
     * An exception that lets the user know that there is none of that item left
     */
    public OutOfStockException() {
    }

    /**
     * gets a message of the exception
     * @return the message
     */
    @Override
    public String getMessage() {
        return "Out of Stock";
    }
    
    
}
