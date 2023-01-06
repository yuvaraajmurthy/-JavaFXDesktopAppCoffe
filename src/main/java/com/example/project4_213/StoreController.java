package com.example.project4_213;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import static com.example.project4_213.Order.df;

/**
 * StoreController class that implements the methods to control the storeView fxml
 *
 * @author Yuvaraaj Murthy, Bharg Trivedi
 */
public class StoreController implements Initializable {

    @FXML
    private ListView<String> storeOrderList;

    @FXML
    private TextField storeTotalRevenue;
    private ObservableList<String> allOrders;

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
        allOrders = FXCollections.observableArrayList();
        for(int i =0; i<mc.storeOrders.noOfStoreOrders(); i++)
        {
            allOrders.add(mc.storeOrders.getOrderFromIndex(i));
        }
        storeOrderList.setItems(allOrders);
        showRevenue();

    }

    /**
     * Method that calculates total revenue and displays it
     */
    private void showRevenue()
    {
        storeTotalRevenue.setText("$"+df.format(mc.storeOrders.getTotalRevenue()));
    }

    /**
     * Exports all the store orders to a file that the user decides
     * @param event
     */
    @FXML
    void exportOrdersClicked(ActionEvent event)
    {
        if (storeOrderList.getItems().isEmpty())
        {
            Alert alrt = new Alert(Alert.AlertType.WARNING);
            alrt.setTitle("No Orders to Export");
            alrt.setHeaderText("There are no Orders to export!");
            alrt.setContentText("Add orders or Exit");
            alrt.showAndWait();
        }
        else{
            FileChooser chooser = new FileChooser();
            chooser.setTitle("Open Target File for the Export");
            chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                    new FileChooser.ExtensionFilter("All Files", "*.*"));
            Stage stage = new Stage();
            File targetFile = chooser.showSaveDialog(stage); //get the reference of the target file
            //write code to write to the file.
            if (targetFile == null) {
                Alert alrt = new Alert(Alert.AlertType.WARNING);
                alrt.setTitle("File not selected");
                alrt.setHeaderText("Try again and select a file");
                alrt.setContentText("Try again.");
                alrt.showAndWait();
            }
            else if(mc.storeOrders.writeToFile(targetFile))
            {
                Alert alrt = new Alert(Alert.AlertType.CONFIRMATION);
                alrt.setTitle("File Exported!");
                alrt.setHeaderText("The store orders were successfully exported.");
                alrt.setContentText("Success");
                alrt.showAndWait();
            }
            else
            {
                Alert alrt = new Alert(Alert.AlertType.WARNING);
                alrt.setTitle("File Export Failed!");
                alrt.setHeaderText("There was an error exporting the Orders");
                alrt.setContentText("ERROR");
                alrt.showAndWait();
            }
        }

    }

    /**
     * Listener for the order remove button to check if an order can be removed
     * @param event
     */
    @FXML
    void storeRemoveButtonClicked(ActionEvent event) {
        if (storeOrderList.getItems().isEmpty()) {
            Alert alrt = new Alert(Alert.AlertType.WARNING);
            alrt.setTitle("No Orders to Delete!");
            alrt.setHeaderText("There are no Orders to delete!");
            alrt.setContentText("Add orders or Exit");
            alrt.showAndWait();
        } else if (storeOrderList.getSelectionModel().getSelectedItem() == null) {
            Alert alrt = new Alert(Alert.AlertType.WARNING);
            alrt.setTitle("Select Order to Delete");
            alrt.setHeaderText("Please Select an Order to delete!");
            alrt.setContentText("Select Order to delete");
            alrt.showAndWait();
        } else {
            int indexOfOrderToRemove = storeOrderList.getSelectionModel().getSelectedIndex();
            Order order = mc.storeOrders.storeOrdersArray.get(indexOfOrderToRemove);
            if (mc.storeOrders.remove(order)) {
                Alert alrt = new Alert(Alert.AlertType.CONFIRMATION);
                alrt.setTitle("Order removed!");
                alrt.setHeaderText("The selected Order was successfully removed.");
                alrt.setContentText("Remove more Orders or add orders");
                alrt.showAndWait();
            } else {
                Alert alrt = new Alert(Alert.AlertType.WARNING);
                alrt.setTitle("Error deleting Order!");
                alrt.setHeaderText("There was an error deleting the Order you selected.");
                alrt.setContentText("Try deleting the another Order.");
                alrt.showAndWait();
            }

            allOrders.remove(indexOfOrderToRemove);
            showRevenue();
        }
    }

}
