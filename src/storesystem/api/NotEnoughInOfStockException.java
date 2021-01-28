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
public class NotEnoughInOfStockException extends OutOfStockException {

    public NotEnoughInOfStockException() {
    }
    public Integer StockQuantity=null;
    NotEnoughInOfStockException(int Quantity) {
        StockQuantity = Quantity;
    }
    
    @Override
    public String getMessage() {
        return "Not Enough in Stock"+((StockQuantity!=null)?" ("+StockQuantity+")":"");
    }
}
