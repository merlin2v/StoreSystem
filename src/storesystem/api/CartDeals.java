/*
 *  
 */
package storesystem.api;

import java.util.*;
import storesystem.api.deals.*;

 /**
* this represents the acumulation of deals that will apply 
* @author Nathan
*/
public class CartDeals{
    private Collection<DealObject> DealObjects = new ArrayList<>();
    private double totalReduction;
    
    

    /**
     * add a DealObject
     * @param o DealObject to add
     */
    public void add(DealObject o) {
        if (o.isReduction()) {
            totalReduction += (Double)o.getReceivable();
        }
        DealObjects.add(o);
    }
    /**
     * remove a DealObject
     * @param o DealObject to remove
     * @return 
     */
    public boolean remove(DealObject o) {
        boolean r = DealObjects.remove(o);
        if (r) {
            if (o.isReduction()) {
                totalReduction -= (Double)o.getReceivable();
            }
        }
        return r;
    }
    /**
     * if there is a price reduction inside
     * @return 
     */
    public boolean hasReduction() {
        return totalReduction != 0;
    }
    /**
     * gets the total reduction 
     * @return 
     */
    public double getTotalReduction(){
        return totalReduction;
    }

    /**
     * gets an unmodifiable Collection of the deal objects
     * @return 
     */
    public Collection<DealObject> getDealObjects() {
        return Collections.unmodifiableCollection(DealObjects);
    }
    
            
}