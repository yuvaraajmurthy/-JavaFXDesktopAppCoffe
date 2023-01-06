package com.example.project4_213;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * StoreOrders keeps a list of orders placed by the user.
 * @author Yuvaraaj Murthy, Bharg Trivedi
 */
public class StoreOrders implements Customizable{

    private int orderNum =1;
    private double totalRevenue = 0;
    protected ArrayList<Order> storeOrdersArray;

    /**
     * Constructor of the StoreOrders class that instantiates local variables
     */
    public StoreOrders()
    {
        storeOrdersArray = new ArrayList<>();
    }


    /**
     * Method that returns the number of orders given in the store
     * @return int number of orders
     */
    public int noOfStoreOrders()
    {
        return storeOrdersArray.size();
    }



    /**
     * Method to add an order to Store orders
     *
     * @param obj Object that is being added
     * @return true if added, false otherwise
     */
    @Override
    public boolean add(Object obj) {
        if((obj instanceof Order order))
        {
            order.setOrderNum(orderNum);
            orderNum++;
            storeOrdersArray.add(order);
            totalRevenue+=order.getTotal();
            return true;
        }
        return false;
    }

    /**
     * Returns the order from a given index from the Store orders array
     * @param index index in the store orders array which is being returned
     */
    public String getOrderFromIndex(int index)
    {
        return storeOrdersArray.get(index).toString();
    }


    /**
     * Method to remove an object from StoreOrders
     *
     * @param obj Object that is being removed
     * @return true if removed, false otherwise
     */
    @Override
    public boolean remove(Object obj)
    {
        if((obj instanceof Order order))
        {
            if(storeOrdersArray.contains(order))
            {
                totalRevenue-=order.getTotal();
                if(storeOrdersArray.isEmpty())
                {
                    totalRevenue=0.0;
                }
                storeOrdersArray.remove(order);
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the total revenue of the store
     * @return double value of revenue
     */
    public double getTotalRevenue()
    {
        return totalRevenue;
    }

    /**
     * Overridden toString method of the StoreOrders class
     * @return Formatted String
     */
    @Override
    public String toString()
    {
        StringBuilder str= new StringBuilder();
        for (Order order : storeOrdersArray) {
            str.append(order.toString()).append("\n");
        }
        return str.toString();
    }

    /**
     * Method to write the entire StoreOrders array to a file
     * @param targetFile the file to which the array is written to
     */
    public boolean writeToFile(File targetFile)
    {
        try{
            FileWriter fw = new FileWriter(targetFile);
            String str = "Store Orders Export\n\n";
            str+=this.toString();
            fw.write(str);
            fw.close();
        }
        catch(IOException e)
        {
            return false;
        }
        return true;
    }

}
