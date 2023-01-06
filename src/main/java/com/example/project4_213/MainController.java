package com.example.project4_213;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * MainController class which controls the main view when the application is opened and leads to the other views
 *
 * @author Yuvaraaj Murthy, Bharg Trivedi
 */
public class MainController implements Initializable {

    protected static Order checkoutItems;
    protected static StoreOrders storeOrders;

    private FXMLLoader loader;



//    @FXML
//    private Button mainCheckoutButton, mainOrderCoffeeButton, mainOrderDonutsButton, mainStoreOrderButton;


    /**
     *
     * Initialize method in MainController to initialize local variables
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        checkoutItems = new Order();
        storeOrders = new StoreOrders();
    }

    /**
     * Method to listen to Order Donut button event to switch views
     * @param event
     * @throws IOException
     */
    @FXML
    void mainOrderDonutClicked(ActionEvent event) throws IOException {
        Stage orderDonutStage = new Stage();
        loader = new FXMLLoader(getClass().getResource("donutView.fxml"));
        Scene donutScene = new Scene(loader.load(), 600, 500);
        DonutController dv = loader.getController();
        dv.setMainController(this);
        orderDonutStage.setTitle("Order Donuts");
        orderDonutStage.setResizable(false);
        orderDonutStage.setScene(donutScene);
        orderDonutStage.initModality(Modality.APPLICATION_MODAL);
        orderDonutStage.show();
    }

    /**
     * Method to listen to Order Coffee button event to switch views
     * @param event
     * @throws IOException
     */
    @FXML
    void mainOrderCoffeeClicked(ActionEvent event) throws IOException {
        Stage orderCoffeeStage = new Stage();
        loader = new FXMLLoader(getClass().getResource("coffeeView.fxml"));
        Scene coffeeScene = new Scene(loader.load(), 600, 500);
        CoffeeController dv = loader.getController();
        dv.setMainController(this);
        orderCoffeeStage.setTitle("Order Coffee");
        orderCoffeeStage.setResizable(false);
        orderCoffeeStage.setScene(coffeeScene);
        orderCoffeeStage.initModality(Modality.APPLICATION_MODAL);
        orderCoffeeStage.show();
    }

    /**
     * Method to listen to Checkout button event to switch views
     * @param event
     * @throws IOException
     */
    @FXML
    void mainCheckoutButtonClicked(ActionEvent event) throws IOException {
        Stage checkoutStage = new Stage();
        loader = new FXMLLoader(getClass().getResource("checkoutView.fxml"));
        Scene checkoutScene = new Scene(loader.load(), 600, 500);
        CheckoutController dv = loader.getController();
        dv.setMainController(this);
        checkoutStage.setTitle("Checkout");
        checkoutStage.setResizable(false);
        checkoutStage.setScene(checkoutScene);
        checkoutStage.initModality(Modality.APPLICATION_MODAL);
        checkoutStage.show();
    }

    /**
     * Method to listen to Store button event to switch views
     * @param event
     * @throws IOException
     */
    @FXML
    void mainStoreOrderClicked(ActionEvent event) throws IOException {
        Stage storeStage = new Stage();
        loader = new FXMLLoader(getClass().getResource("storeView.fxml"));
        Scene storeScene = new Scene(loader.load(), 600, 500);
        StoreController dv = loader.getController();
        dv.setMainController(this);
        storeStage.setTitle("Store Orders");
        storeStage.setResizable(false);
        storeStage.setScene(storeScene);
        storeStage.initModality(Modality.APPLICATION_MODAL);
        storeStage.show();
    }


}
