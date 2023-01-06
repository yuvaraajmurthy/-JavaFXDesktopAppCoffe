package com.example.project4_213;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import static com.example.project4_213.Order.df;


/**
 * DonutController implements the methods to control the donutView
 *
 * @author Yuvaraaj Murthy, Bharg Trivedi
 */
public class DonutController implements Initializable {



    @FXML
    private ListView<String> donutCurrentSelectionList, donutFlavorList;

    @FXML
    private ComboBox<String> donutMenu;

    @FXML
    private ComboBox<Integer> donutQuantityCombo;

    @FXML
    private TextField donutSubTotal;

    private ArrayList<Donuts> donutOrders = new ArrayList<>();


    private MainController mc;

    private static final ObservableList<String> donutOptions = FXCollections.observableArrayList(
      Donuts.YST, Donuts.CKE, Donuts.DHL);

    private static final ObservableList<Integer> donutQuantity = FXCollections.observableArrayList(
            1,2,3,4,5,6,7,8,9,10);

    private static final ObservableList<String> yeastOptions = FXCollections.observableArrayList(
            "Powdered", "Chocolate-Glazed","Caramel-Glazed");

    private static final ObservableList<String> cakeOptions = FXCollections.observableArrayList(
            "Baked-Carrot", "Baked-Cinnamon","Chocolate");

    private static final ObservableList<String> holeOptions = FXCollections.observableArrayList(
            "Glazed-Blueberry", "Cinnamon","Glazed-Chocolate");


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
        donutMenu.setItems(donutOptions);
        donutMenu.getSelectionModel().selectFirst();
        donutFlavorList.setItems(yeastOptions);
        donutQuantityCombo.setItems(donutQuantity);
        donutQuantityCombo.setValue(1);
    }


    /**
     * Method that listens to any changes in the donut Menu
     * @param event
     */
    @FXML
    void donutMenuSelected(ActionEvent event)
    {
        if(donutMenu.getValue().equals(Donuts.YST))
        {
            donutFlavorList.setItems(yeastOptions);
            donutFlavorList.getSelectionModel().selectFirst();
        }
        else if(donutMenu.getValue().equals(Donuts.CKE))
        {
            donutFlavorList.setItems(cakeOptions);
            donutFlavorList.getSelectionModel().selectFirst();

        }
        else if(donutMenu.getValue().equals(Donuts.DHL))
        {
            donutFlavorList.setItems(holeOptions);
            donutFlavorList.getSelectionModel().selectFirst();
        }

    }

    /**
     * Method that runs when the add Donuts button is clicked by adding the donuts to the cart
     * @param event
     */
    @FXML
    void addDonutsClicked(ActionEvent event)
    {
        if(donutMenu.getValue()==null ||
                donutFlavorList.getSelectionModel().getSelectedItem()==null)
        {
            Alert alrt = new Alert(Alert.AlertType.WARNING);
            alrt.setTitle("Select Input!");
            alrt.setHeaderText("No type/flavor is selected.");
            alrt.setContentText("Select type and flavor of donut.");
            alrt.showAndWait();
        }
        else
        {
            Donuts donut = new Donuts(donutQuantityCombo.getValue(),
                    donutMenu.getValue(),
                    donutFlavorList.getSelectionModel().getSelectedItem());
            donutOrders.add(donut);
            donutSubTotal.setText("$"+df.format(allDonutsPrice()));
            donutCurrentSelectionList.getItems().add(donut.toString());

        }
    }

    /**
     *
     * Method that calculates the price of the donuts
     * @return price of the donuts as a double
     */
    private double allDonutsPrice()
    {
        double price=0;
        for (Donuts donutOrder : donutOrders) {
            price += donutOrder.itemPrice();
        }
        return price;
    }

    /**
     * Method that removes a previously selected configuration of a donut order
     * @param event
     */
    @FXML
    void removeDonutsClicked(ActionEvent event)
    {
        if(donutCurrentSelectionList.getItems().isEmpty())
        {
            Alert alrt = new Alert(Alert.AlertType.WARNING);
            alrt.setTitle("No Item to Delete!");
            alrt.setHeaderText("There is no item to delete!");
            alrt.setContentText("Select type and flavor of donut.");
            alrt.showAndWait();
        }
        else if(donutCurrentSelectionList.getSelectionModel().getSelectedItem()==null)
        {
            Alert alrt = new Alert(Alert.AlertType.WARNING);
            alrt.setTitle("Select Input!");
            alrt.setHeaderText("No donut order is selected.");
            alrt.setContentText("Select donut order");
            alrt.showAndWait();
        }
        else
        {
            donutOrders.remove(donutCurrentSelectionList.getSelectionModel().getSelectedIndex());
            donutCurrentSelectionList.getItems().remove(donutCurrentSelectionList.getSelectionModel().getSelectedIndex());
            donutSubTotal.setText("$"+df.format(allDonutsPrice()));
            donutCurrentSelectionList.getSelectionModel().clearSelection();
        }
    }

    /**
     * Adds the donuts selected with their flavors and quantity to the list which will be added to the orders
     * @param event
     */
    @FXML
    void addDonutsOrderClicked(ActionEvent event)
    {
        if(donutCurrentSelectionList.getItems().isEmpty())
        {
            Alert alrt = new Alert(Alert.AlertType.WARNING);
            alrt.setTitle("Add Donuts");
            alrt.setHeaderText("No donuts added to Order.");
            alrt.setContentText("Add donuts or exit");
            alrt.showAndWait();
        }
        else
        {
            for (Donuts donutOrder : donutOrders) {
                mc.checkoutItems.add(donutOrder);
            }
            donutOrders = new ArrayList<>();
            donutQuantityCombo.getSelectionModel().select(0);
            donutMenu.getSelectionModel().select(0);
            donutFlavorList.getSelectionModel().select(0);
            donutCurrentSelectionList.getItems().clear();
            donutSubTotal.setText("$"+df.format(allDonutsPrice()));

            Alert alrt = new Alert(Alert.AlertType.CONFIRMATION);
            alrt.setTitle("Donuts Added!");
            alrt.setHeaderText("Selected Donuts have been added to the cart!");
            alrt.setContentText("Add more donuts or exit");
            alrt.showAndWait();
        }
    }






}
