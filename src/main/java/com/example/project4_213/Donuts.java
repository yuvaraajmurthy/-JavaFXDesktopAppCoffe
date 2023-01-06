package com.example.project4_213;

/**
 * Donuts class to store donut type with their specifications
 * @author Yuvaraaj Murthy, Bharg Trivedi
 */
public class Donuts extends MenuItem{

    private String typeOfDonut, flavorOfDonut;

    public static final String YST = "Yeast Donut"; //Yeast donut name in list
    public static final String CKE = "Cake Donut"; //Cake donut name in list
    public static final String DHL = "Donut Hole"; //Donut hole name in list

    private static final double YEAST_DONUT_PRICE = 1.59;
    private static final double CAKE_DONUT_PRICE = 1.79;
    private static final double DONUT_HOLE_PRICE = 0.39;


    /**
     * Donut class constructor to instantiate local variables
     * @param quantity the number of donuts being added
     * @param typeOfDonut the type of donut being added
     * @param flavorOfDonut the flavor of donut being added
     */
    public Donuts(int quantity, String typeOfDonut, String flavorOfDonut) {
        super(quantity);
        this.flavorOfDonut = flavorOfDonut;
        this.typeOfDonut = typeOfDonut;
        switch (typeOfDonut) {
            case YST -> price = YEAST_DONUT_PRICE * quantity;
            case CKE -> price = CAKE_DONUT_PRICE * quantity;
            case DHL -> price = DONUT_HOLE_PRICE * quantity;
        }
    }

    /**
     * Method that returns the price of this current donut order
     * @return returns double
     */
    @Override
    public double itemPrice()
    {
        return price;
    }

    /**
     * Overriden toString method
     * @return formatted string of donut
     */
    @Override
    public String toString()
    {
        return typeOfDonut+" - "+flavorOfDonut+"["+quantity+"]";
    }

}
