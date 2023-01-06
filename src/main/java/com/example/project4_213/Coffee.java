package com.example.project4_213;

/**
 * Coffee class to store coffee type
 * @author Yuvaraaj Murthy, Bharg Trivedi
 */
public class Coffee extends MenuItem {

    private String size;
    private boolean cream, syrup, milk, caramel, whipCream;

    public static final int CREAM = 1, SYRUP = 2, MILK = 3, CARAMEL = 4, WHIPCREAM = 5;
    private static final double SHORT = 1.69, TALL = 2.09, GRANDE = 2.49, VENTI = 2.89;
    private static final double ADD_IN = 0.3;
    private double addInPriceTotal = 0;

    public static final String SHRT = "Short", TLL = "Tall", GRDE = "Grande", VNTI = "Venti"; //coffee names

    /**
     * Constructor of Coffee class to instantiate local variables
     * @param size size of coffee
     * @param quantity number of cups of coffee
     * @param cream if cream is being added
     * @param syrup if syrup is being added
     * @param milk if milk is being added
     * @param caramel if caramel is being added
     * @param whipCream if whipped cream is being added
     */
    public Coffee(String size, int quantity, boolean cream, boolean syrup, boolean milk,
                  boolean caramel, boolean whipCream) {
        super(quantity);
        this.size = size;
        this.cream = cream;
        this.syrup = syrup;
        this.milk = milk;
        this.caramel = caramel;
        this.whipCream = whipCream;
        updateCoffeePrice();
    }

    /**
     * private method to update Coffee price
     */
    private void updateCoffeePrice() {
        price = 0;
        updateAddInPrice();
        price += addInPriceTotal;
        if (SHRT.equals(size)) {
            price += SHORT;
        } else if (TLL.equals(size)) {
            price += TALL;
        } else if (GRDE.equals(size)) {
            price += GRANDE;
        } else if (VNTI.equals(size)) {
            price += VENTI;
        }
        price = price * quantity;
    }


    /**
     * Private method to update add-in price
     */
    private void updateAddInPrice() {
        addInPriceTotal = 0;
        if (cream) {
            addInPriceTotal += ADD_IN;
        }
        if (syrup) {
            addInPriceTotal += ADD_IN;
        }
        if (milk) {
            addInPriceTotal += ADD_IN;
        }
        if (caramel) {
            addInPriceTotal += ADD_IN;
        }
        if (whipCream) {
            addInPriceTotal += ADD_IN;
        }
    }


    /**
     * Method that returns the total price of a coffee object
     * @return returns a double which is the value of the coffee
     */
    @Override
    public double itemPrice() {
        updateCoffeePrice();
        return price;
    }


    /**
     * Method to handle addition of different add-ins
     * @param addIn type of add-in that is being added
     */
    public void addAddIn(int addIn)
    {
        if(addIn==CREAM){cream=true;}
        if(addIn==SYRUP){syrup=true;}
        if(addIn==MILK){milk=true;}
        if(addIn==CARAMEL){caramel=true;}
        if(addIn==WHIPCREAM){whipCream=true;}
    }


    /**
     * Method to handle removal of add-in
     * @param addIn type of add-in that is being removed
     */
    public void removeAddIn(int addIn)
    {
        if(addIn==CREAM){cream=false;}
        if(addIn==SYRUP){syrup=false;}
        if(addIn==MILK){milk=false;}
        if(addIn==CARAMEL){caramel=false;}
        if(addIn==WHIPCREAM){whipCream=false;}
    }


    /**
     * Changes the size of the coffee order and updates price
     * @param size the new size of coffee to be ordered
     */
    public void setSize(String size)
    {
        this.size = size;
        updateCoffeePrice();

    }

    /**
     * Changes the number of cups of coffee to be ordered
     * @param quantity the new number of coffee cups to be ordered
     */
    @Override
    public void setQuantity(int quantity)
    {

        this.quantity = quantity;
        updateCoffeePrice();
    }


    /**
     * Overridden toString method
     * @return returns a formatted coffee string
     */
    @Override
    public String toString()
    {
        String str = "Coffee "+size+"["+quantity+"]";
        updateAddInPrice();
        if(addInPriceTotal!=0)
        {
            str+= " [";
            if(cream){str+="Cream,";}
            if(syrup){str+="Syrup,";}
            if(milk){str+="Milk,";}
            if(caramel){str+="Caramel,";}
            if(whipCream){str+="Whipped Cream,";}
            str = str.substring(0, str.length()-1);
            str += "]";
        }
        return str;
    }


}
