/*
 *  
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

    /**
     * tests to see if this is a price reduction. 
     * will return true.
     * @return true if it is a reduction in price
     */
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

    /**
     * gets a short message representation of this deal
     * @return a string object
     */
    @Override
    public String getMessage() {
        return "-"+Reduction;
    }
}
