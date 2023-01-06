package com.example.project4_213;

/**
 * This interface has the add and remove methods used to remove or add an order
 * @author Yuvaraaj Murthy, Bharg Trivedi
 */
public interface Customizable {
    /**
     * Method to be implemented in classes that implement the Customizable interface to add an object
     * @param obj Object that is being added
     * @return true if added, false otherwise
     */
    boolean add(Object obj);

    /**
     * Method to be implemented in classes that implement the Customizable interface to remove an object
     * @param obj Object that is being removed
     * @return true if removed, false otherwise
     */
    boolean remove(Object obj);
}
