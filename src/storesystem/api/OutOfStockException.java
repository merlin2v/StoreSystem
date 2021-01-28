/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storesystem.api;

/**
 *
 * @author natha
 */
public class OutOfStockException extends Exception {

    public OutOfStockException() {
    }

    @Override
    public String getMessage() {
        return "Out of Stock";
    }
    
    
}
