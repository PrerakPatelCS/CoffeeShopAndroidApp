package com.example.coffeeshop;
/**
 * Interface that has add and remove methods signatures
 * @author Craig Li, Prerak Patel
 */
public interface Customizable {
    /**
     * Adds an object
     * @param obj object to be added
     * @return true if the object is added, false otherwise
     */
    boolean add(Object obj);
    /**
     * Removes an object
     * @param obj object to be removed
     * @return true if the object is removed, false otherwise
     */
    boolean remove(Object obj);
}
