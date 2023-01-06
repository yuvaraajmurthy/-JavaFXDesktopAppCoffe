package com.example.project4_213;

/**
 * MenuItem class is the super class of Coffee and Donuts
 *
 * @author Yuvaraaj Murthy, Bharg Trivedi
 */
public class MenuItem {

    protected double price;
    protected int quantity;

    /**
     * Constructor of MenuItem class to instantiate local variables
     *
     * @param quantity number of items being ordered
     */
    public MenuItem(int quantity) {
        this.quantity = quantity;
    }

    /**
     * returns the price of the item
     *
     * @return double value of item
     */
    public double itemPrice() {
        return 0;
    }

    /**
     * Lets the user set the number of items to purchase
     *
     * @param quantity int number of items to purchase
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
