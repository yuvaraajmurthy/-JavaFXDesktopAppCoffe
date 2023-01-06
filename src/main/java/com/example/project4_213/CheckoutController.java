package com.example.project4_213;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import static com.example.project4_213.Order.df;

/**
 * CheckoutController
 *
 * @author Yuvaraaj Murthy, Bharg Trivedi
 */
public class CheckoutController implements Initializable {


   /* @FXML
    private Button checkoutOrderButton, checkoutRemoveItemButton;*/

    @FXML
    private ListView<String> checkoutOrderList;

    @FXML
    private TextField checkoutSubtotalBox, checkoutTotalBox, checkoutSalesTaxBox;

    private ObservableList<String> orderedItems;

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
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        orderedItems = FXCollections.observableArrayList();
        for(int i =0; i<mc.checkoutItems.ordersSize(); i++)
        {
            orderedItems.add(mc.checkoutItems.getItemFromIndex(i));
        }
        checkoutOrderList.setItems(orderedItems);
        showPrices();
    }

    /**
     * Updates the prices of all ordered items and displays it in the scene
     */
    private void showPrices()
    {
        checkoutSubtotalBox.setText("$"+df.format(mc.checkoutItems.getSubTotal()));
        checkoutSalesTaxBox.setText("$"+df.format(mc.checkoutItems.getSalesTax()));
        checkoutTotalBox.setText("$"+df.format(mc.checkoutItems.getTotal()));
    }


    /**
     * Checks when the order button is clicked and checks if the conditions are met to send an order
     * @param event
     */
    @FXML
    void checkoutOrderButtonClicked(ActionEvent event)
    {
        if(checkoutOrderList.getItems().isEmpty())
        {
            Alert alrt = new Alert(Alert.AlertType.WARNING);
            alrt.setTitle("No Items to Order!");
            alrt.setHeaderText("There are no items to Order!");
            alrt.setContentText("Add Items or Exit");
            alrt.showAndWait();
        }
        else if(mc.storeOrders.add(mc.checkoutItems))
        {
            mc.checkoutItems = new Order();
            Alert alrt = new Alert(Alert.AlertType.CONFIRMATION);
            alrt.setTitle("Items Ordered!");
            alrt.setHeaderText("All items have been ordered");
            alrt.setContentText("Add Items or Exit");
            alrt.showAndWait();
        }
        else
        {
            Alert alrt = new Alert(Alert.AlertType.WARNING);
            alrt.setTitle("Items not ordered");
            alrt.setHeaderText("Failed to order items!");
            alrt.setContentText("Add Items or Exit");
            alrt.showAndWait();
        }
        orderedItems.clear();
        showPrices();

    }

    /**
     * Method that checks if all conditions are met to delete an order and deletes it
     * @param event
     */
    @FXML
    void checkoutRemoveButtonClicked(ActionEvent event) {
        if(checkoutOrderList.getItems().isEmpty())
        {
            Alert alrt = new Alert(Alert.AlertType.WARNING);
            alrt.setTitle("No Items to Delete!");
            alrt.setHeaderText("There are no items to delete!");
            alrt.setContentText("Add Items or Exit");
            alrt.showAndWait();
        }
        else if(checkoutOrderList.getSelectionModel().getSelectedItem()==null)
        {
            Alert alrt = new Alert(Alert.AlertType.WARNING);
            alrt.setTitle("Select Item to Delete");
            alrt.setHeaderText("Please Select an item to delete!");
            alrt.setContentText("Select item to delete");
            alrt.showAndWait();
        }
        else
        {
            int indexOfItemToRemove = checkoutOrderList.getSelectionModel().getSelectedIndex();
            MenuItem mi = mc.checkoutItems.orders.get(indexOfItemToRemove);
            if(mc.checkoutItems.remove(mi))
            {
                Alert alrt = new Alert(Alert.AlertType.CONFIRMATION);
                alrt.setTitle("Item removed!");
                alrt.setHeaderText("The selected item was successfully removed.");
                alrt.setContentText("Remove more items or order items");
                alrt.showAndWait();
            }
            else
            {
                Alert alrt = new Alert(Alert.AlertType.WARNING);
                alrt.setTitle("Error deleting item!");
                alrt.setHeaderText("There was an error deleting the item you selected.");
                alrt.setContentText("Try deleting the another item.");
                alrt.showAndWait();
            }

            orderedItems.remove(indexOfItemToRemove);
            showPrices();
        }
    }



}
