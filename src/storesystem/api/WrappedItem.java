/*
 *  
 */
package storesystem.api;

/**
 * Generic Item wrapper class.<br>
 * This is used to add extra data to Item's and can be done through the the 
 * {@link ItemRegistry} with the use of 
 * {@link ItemRegistry#registerWrappedItem(java.lang.String, storesystem.api.WrappedItem) registerWrappedItem()} 
 * which will save the item the the name in the first {@code String} parameter
 * @author Nathan
 */
public class WrappedItem extends Item{
    /**
     * a value to indicate whether an object is registered or not
     */
    boolean Registered = false;
    /**
     * gets whether the value is a valid registered item
     * @return if this object has been registered or not
     */
    public boolean isRegistered(){
        return Registered;
    }
}
