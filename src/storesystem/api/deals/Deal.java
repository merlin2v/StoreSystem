/*
 * 
 */
package storesystem.api.deals;

import java.util.function.BiPredicate;
import storesystem.api.*;

/**
 * The Deal class is the class that is capable of testing objects and returning 
 * if that deal can be applied or not. 
 * This is a base implementation but it still can be created.
 * @author Nathan
 */
public class Deal<T> {
    /** the check to see if the deal is valid and can be applied.
     * to use:  
     *  {@code Predicate<Item[]> predictate = (items, isPeek) -> {return items.length < 2;};}  
     * {@code isPeek} is whether it is a peek method or a real method.   
     */
    public BiPredicate<ItemOrder[], Boolean> Test;
    /**
     * The object that is received.
     */
    public T Receivable;
    /**
     * the number of times this Deal has been applied.
     */
    public int TimesApplied = 0;
    /**
     * The max times Deal can be applied. 
     * If -1 then there is no limit.
     */
    int Max=-1; 
    /**
     * Peek to see what the value would evaluate to. Some peeking may be disabled on some implementations.  
     * @param items The Items that are to be evaluated
     * @return whether or not do Receive the {@link Receivable}  
     */
    public boolean peekPredictate(ItemOrder[] items){
        return Test.test(items, true);
    }
    /**
     * run the test 
     * @param items The Items that are to be evaluated
     * @return whether or not do Receive the {@link receivable}  
     */
    /*public boolean test(ItemOrder[] items){
        return Test.test(items, false);
    }*/
    /**
     * try to get the {@link Receivable} if evaluated. 
     * @param items The Items that are to be evaluated
     * @return get the {@link Receivable} or null if false
     */
    public T tryReceive(ItemOrder[] items){
        T ret = null;
        if ( Max==-1 || TimesApplied < Max) {
            ret = Test.test(items, false)?Receivable:null;
            TimesApplied++;
        }
        return ret;
    }
}
