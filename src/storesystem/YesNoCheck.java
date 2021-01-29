/*
 *  
 */
package storesystem;

import java.util.*;

/**
 * A simple class to detect a yes or no answer.
 * @author Nathan
 */
public class YesNoCheck {

    private String input;
    private boolean answered=false;
    private static List<String> YES = Arrays.asList(new String[]
            {"YES","Y","YA","YE"});
    private static List<String> NO = Arrays.asList(new String[]
            {"NO","N","NA","NOPE"});

    /**
     * creates a simple YesNoCheck Object
     * @param input - the input provided by the user
     */
    public YesNoCheck(String input) {
        this.input = input.toUpperCase();
    }

    /**
     * if is a yes reply
     * @return is {@code true} if yes is the input
     */
    public boolean isYes() {
        boolean b = YES.contains(this.input);
        if (b) answered=true;
        return b;
    }
    /**
     *  if is a no reply
     * @return is {@code true} if no is the input
     */
    public boolean isNo() {
        boolean b = NO.contains(this.input);
        if (b) answered=true;
        return b;
    }
    /**
     * if yes or no has returned true
     * @return {@code true} if {@link isYes()} or {@link isNo()} has been called 
     * and returned true.
     */
    public boolean answered(){
        return answered;
    }
    /**
     * resets the {@link answered()} to false until 
     * {@link isNo()} or {@link isYes()} is called.
     */
    public void reset(){
        answered = false;
    }
}
