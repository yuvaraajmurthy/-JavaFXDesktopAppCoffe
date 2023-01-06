package com.example.project4_213;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

import static com.example.project4_213.Order.df;

/**
 * CoffeeController class that implements the methods needed to run the coffee view
 *
 * @author Yuvaraaj Murthy, Bharg Trivedi
 */
public class CoffeeController implements Initializable
{


    @FXML
    private ComboBox<Integer> coffeeQuantity;

    @FXML
    private ComboBox<String> coffeeSizeBox;

    @FXML
    private TextField coffeeSubTotalBox;

    @FXML
    private CheckBox creamCheck, syrupCheck, milkCheck, caramelCheck, whipCreamCheck;

    private Coffee coffee;

    private static final ObservableList<String> coffeeOptions = FXCollections.observableArrayList(
            Coffee.SHRT, Coffee.TLL, Coffee.GRDE, Coffee.VNTI);

    private static final ObservableList<Integer> coffeeQuantityList = FXCollections.observableArrayList(
            1,2,3,4,5,6,7,8,9,10);

    private MainController mc;

    /**
     * Method that lets this controller access the variables in the MainController
     * @param mc
     */
    public void setMainController(MainController mc)
    {
        this.mc = mc;
    }

    /**
     * Initialize method to initialize fxml elements
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        coffeeSizeBox.setItems(coffeeOptions);
        coffeeSizeBox.getSelectionModel().selectFirst();
        coffeeQuantity.setItems(coffeeQuantityList);
        coffeeQuantity.getSelectionModel().selectFirst();
        coffee = new Coffee(Coffee.SHRT, 1, false, false, false, false, false);
        updateCoffeeSubtotalPrice();


    }


    /**
     * Method that updates the Coffee price and displays it in the scene
     */
    private void updateCoffeeSubtotalPrice()
    {
        coffeeSubTotalBox.setText("$"+df.format(coffee.itemPrice()));
    }

    /**
     * Method that takes the options selected by user and adds the order to checkout
     * @param event
     */
    @FXML
    void addCoffeeOrderClicked(ActionEvent event)
    {
        mc.checkoutItems.add(coffee);
        resetCoffeeOptions();
        Alert alrt = new Alert(Alert.AlertType.CONFIRMATION);
        alrt.setTitle("Coffee Added!");
        alrt.setHeaderText("Coffee with the selected options has been added to the cart!");
        alrt.setContentText("Add more Coffee or exit");
        alrt.showAndWait();

    }

    /**
     * After an order has been placed this method resets all the coffee options
     */
    private void resetCoffeeOptions()
    {
        coffee = new Coffee(Coffee.SHRT, 1, false, false, false, false, false);

        System.out.println(mc.checkoutItems.toString());

        coffeeSizeBox.getSelectionModel().selectFirst();
        coffeeQuantity.getSelectionModel().selectFirst();
        creamCheck.setSelected(false);
        syrupCheck.setSelected(false);
        milkCheck.setSelected(false);
        caramelCheck.setSelected(false);
        whipCreamCheck.setSelected(false);
        updateCoffeeSubtotalPrice();

    }

    /**
     * Listener to check when user changes the quantity of coffee to update the price
     * @param event
     */
    @FXML
    void coffeeQuantityChanged(ActionEvent event) {
        coffee.setQuantity(coffeeQuantity.getValue());
        updateCoffeeSubtotalPrice();
    }

    /**
     * Listener method to listen to changes in coffee size to update the price
     * @param event
     */
    @FXML
    void coffeeSizeChanged(ActionEvent event)
    {
        coffee.setSize(coffeeSizeBox.getValue());
        updateCoffeeSubtotalPrice();
    }

    /**
     * Listener method to check if cream option has been checked or unchecked and to update the price accordingly
     * @param event
     */
    @FXML
    void creamChecked(ActionEvent event) {
        if(creamCheck.isSelected())
        {
            coffee.addAddIn(Coffee.CREAM);
        }
        else if(!creamCheck.isSelected())
        {
            coffee.removeAddIn(Coffee.CREAM);
        }
        updateCoffeeSubtotalPrice();
    }

    /**
     * Listener method to check if syrup option has been checked or unchecked and to update the price accordingly
     * @param event
     */
    @FXML
    void syrupChecked(ActionEvent event) {
        if(syrupCheck.isSelected())
        {
            coffee.addAddIn(Coffee.SYRUP);
        }
        else if(!syrupCheck.isSelected())
        {
            coffee.removeAddIn(Coffee.SYRUP);
        }
        updateCoffeeSubtotalPrice();
    }

    /**
     * Listener method to check if milk option has been checked or unchecked and to update the price accordingly
     * @param event
     */
    @FXML
    void milkChecked(ActionEvent event) {
        if(milkCheck.isSelected())
        {
            coffee.addAddIn(Coffee.MILK);
        }
        else if(!milkCheck.isSelected())
        {
            coffee.removeAddIn(Coffee.MILK);
        }
        updateCoffeeSubtotalPrice();
    }

    /**
     * Listener method to check if caramel option has been checked or unchecked and to update the price accordingly
     * @param event
     */
    @FXML
    void caramelChecked(ActionEvent event)
    {
        if(caramelCheck.isSelected())
        {
            coffee.addAddIn(Coffee.CARAMEL);
        }
        else if(!caramelCheck.isSelected())
        {
            coffee.removeAddIn(Coffee.CARAMEL);
        }
        updateCoffeeSubtotalPrice();
    }

    /**
     * Listener method to check if whipped Cream option has been checked or unchecked and to update the price accordingly
     * @param event
     */
    @FXML
    void whipCreamChecked(ActionEvent event) {
        if(whipCreamCheck.isSelected())
        {
            coffee.addAddIn(Coffee.WHIPCREAM);
        }
        else if(!whipCreamCheck.isSelected())
        {
            coffee.removeAddIn(Coffee.WHIPCREAM);
        }
        updateCoffeeSubtotalPrice();
    }


}
