/*
 * 
 */
package storesystem.api;

/**
 * Represents an arithmetic-enabled object by adding add and subtract functions. 
 * @author Nathan
 * @param <T> the object that can be added to
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
     * subtract this by {@code T}
     * @param remove the value to remove from this
     * @return the difference of the two objects
     */
    public T subtractBy(T remove);
    /**
     * subtract this by {@code T}
     * @param remove the value to remove from this
     * @return the difference of the two objects
     */
    public T subtractBy(Number remove);
}
