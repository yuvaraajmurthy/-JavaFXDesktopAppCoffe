package com.example.project4_213;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * The Order class implements Customizable and tracks the orders added by the customer
 * @author Yuvaraaj Murthy, Bharg Trivedi
 */
public class Order implements Customizable{

    private int orderNum;
    private double subTotal, salesTax, total;
    protected ArrayList<MenuItem> orders;

    public static DecimalFormat df = new DecimalFormat("0.00");
    private static final double NJ_SALES_TAX = 0.06625;

    /**
     * Constructor of the Order class to instantiate local variables
     */
    public Order()
    {
        orders = new ArrayList<>();
        subTotal=0;
        orderNum=1;
    }

    /**
     * Changes the number of the order
     * @param num the new order number
     */
    public void setOrderNum(int num)
    {
        orderNum = num;
    }


    /**
     * Returns the subtotal of an order
     */
    public double getSubTotal()
    {
        return subTotal;
    }

    /**
     * Method to find out if an order has items
     * @return true if order has items, false otherwise
     */
    public boolean hasItems()
    {
        return !orders.isEmpty();
    }

    /**
     * Method that returns the number of items in an order
     * @return int value of number of orders
     */
    public int ordersSize()
    {
        return orders.size();
    }

    /**
     * Method to add an item to an order
     * @param obj Object that is being added
     * @return true if added, false otherwise
     */
    @Override
    public boolean add(Object obj) {
        if((obj instanceof MenuItem mi))
        {
            orders.add(mi);
            subTotal+=mi.itemPrice();
            return true;
        }
        return false;
    }

    /**
     * Method to remove an item from the order
     * @param obj Object that is being removed
     * @return true if removed, false otherwise
     */
    @Override
    public boolean remove(Object obj) {
        int index = orders.indexOf(obj);
        if((obj instanceof MenuItem) && hasItems())
        {
            subTotal-=orders.get(index).itemPrice();
            orders.remove(index);
            if(!hasItems())
            {
                subTotal=0;
            }
            return true;
        }
        return false;
    }

    /**
     * Method to get toString of an item in an order
     * @param index of the item in the order
     * @return returns String of the item
     */
    public String getItemFromIndex(int index)
    {
        return orders.get(index).toString();
    }

    /**
     * Method to return the sales tax computed on the subtotal of the order
     * @return double value of the sales tax
     */
    public double getSalesTax()
    {
        salesTax = subTotal * NJ_SALES_TAX;
        return salesTax;
    }

    /**
     * Method that returns the total value of the order after adding in the sales tax
     * @return double value of the order
     */
    public double getTotal()
    {
        total = subTotal + getSalesTax();
        return total;
    }

    /**
     * Overridden toString method of the Order class
     * @return formatted String of the Order class
     */
    @Override
    public String toString()
    {
        StringBuilder str= new StringBuilder("Order #" + orderNum + " Total Price: $" + df.format(getTotal()) + "\n");
        for (MenuItem order : orders) {
            str.append(order.toString()).append("\n");
        }
        return str.toString();
    }
}
