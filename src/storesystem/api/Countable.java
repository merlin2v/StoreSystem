/*
 * 
 */
package storesystem.api;

/**
 * Represents an arithmetic-enabled object by adding add and subtract functions. 
 * @author Nathan
 */
public interface Countable<T> {
    /**
     * add this to {@code T}
     * @param add the value to add to this
     * @return the sum of the two objects
     */
    public T addTo(T add);
    /**
     * add this to {@code T}
     * @param add the value to add to this
     * @return the sum of the two objects
     */
    public T addTo(Number add);
    /**
     * add this to {@code T}
     * @param remove the value to remove from this
     * @return the difference of the two objects
     */
    public T subtractFrom(T remove);
    /**
     * add this to {@code T}
     * @param remove the value to remove from this
     * @return the difference of the two objects
     */
    public T subtractFrom(Number remove);
}
