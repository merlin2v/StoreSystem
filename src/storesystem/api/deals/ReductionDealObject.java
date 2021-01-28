/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storesystem.api.deals;

/**
 * Represents a reduction in price
 * @author Nathan
 */
public class ReductionDealObject extends DealObject<Double>{

    /**
     * create a simple price reduction
     * @param Reduction the amount that gets reduced
     */
    public ReductionDealObject(double Reduction) {
        this.Reduction = Reduction;
    }

    /**
     * the price reduction to apply
     */
    public double Reduction;
    @Override
    public boolean isReduction() {
        return true;
    }

    /**
     * gets the Reduction in price
     * @return  get the reduction value
     */
    @Override
    public Double getReceivable() {
        return this.Reduction;
    }

    @Override
    public String getMessage() {
        return "-"+Reduction;
    }
    
    
}
