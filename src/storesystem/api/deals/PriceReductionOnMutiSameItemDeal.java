/*
 * 
 */
package storesystem.api.deals;

import storesystem.api.*;

/**
 * This represents a price reduction on a specific quantity of a item
 * @author Nathan
 */
public class PriceReductionOnMutiSameItemDeal extends PriceReductionDeal{
    /**
     * This is the item to search for
     */
    public Item SearchItem;
    /**
     * This is the amount required to apply this deal
     */
    public int QuantityToRecieve;
    /**
     * This is the price to reduce per every {@link QuantityToRecieve}
     */
    public double ReducedPrice;    
    private boolean hasRun;
    
    /**
     * Creates a Deal that can be applied to every {@code quantity} number of 
     * {@code search} items found in a {@link ShoppingCart}.
     * @param search the Item to search which is assigned to {@link SearchItem}
     * @param quantity the quantity to reduce the price 
     * @param reducedPrice the reduced price per item
     */
    public PriceReductionOnMutiSameItemDeal(Item search, int quantity, double reducedPrice) {
        this.SearchItem = search;
        this.QuantityToRecieve = quantity;
        this.ReducedPrice = reducedPrice;
        this.Max=-1;
        this.Receivable = new ReductionDealObject(reducedPrice);
        hasRun=false;//to prevent loops
        this.Test = (cart, isPeek) -> {
            
            if (cart.hasItem(SearchItem)&&!this.hasRun) {
                ItemOrder order = cart.getItemOrder(SearchItem);
                if (this.QuantityToRecieve <= order.getQuantity()) {
                    //calculations are done here to increase efficiency unless peeked
                    if (!isPeek) {
                        // we set TimesApplied here because we use the silent tryReceive
                        this.TimesApplied = order.getQuantity() / this.QuantityToRecieve;
                        if (this.Max!=-1 && this.TimesApplied > this.Max) this.TimesApplied = this.Max;
                        this.Receivable.Reduction = this.ReducedPrice * this.TimesApplied; 
                        this.hasRun=true;
                    }
                    return true;
                }else return false;
                
            }
            return false;
        };
    }

        /**
     * Creates a Deal that can be applied to every {@code quantity} number of 
     * {@code search} items found in a {@link ShoppingCart}.
     * @param search the Item to search which is assigned to {@link SearchItem}
     * @param quantity the quantity to reduce the price 
     * @param reducedPrice the reduced price per item
     * @param max the max amount of times this deal can be applied 
     */
    public PriceReductionOnMutiSameItemDeal(Item search, int quantity, double reducedPrice, int max) {
        this.SearchItem = search;
        this.QuantityToRecieve = quantity;
        this.ReducedPrice = reducedPrice;
        this.Max = max;
        
        this.Test = (cart, isPeek) -> {
            if (cart.hasItem(SearchItem)) {
                ItemOrder order = cart.getItemOrder(SearchItem);
                if (this.QuantityToRecieve >= order.getQuantity()) {
                    //calculations are done here to increase efficiency unless peeked
                    if (!isPeek) {
                        // we set TimesApplied here because we use the silent tryReceive
                        this.TimesApplied = order.getQuantity() % this.QuantityToRecieve;
                        if (this.Max!=-1 && this.TimesApplied > this.Max) this.TimesApplied = this.Max;
                        this.Receivable.Reduction = this.ReducedPrice * this.TimesApplied; 
                    }
                    return true;
                }else return false;
                
            }
            return false;
        };
    }

    @Override
    public ReductionDealObject tryReceive(ShoppingCart cart) {
        return super.silentReceive(cart); 
    }

    
    

    
    
    
}
