package com.example.project4_213;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main class to run the program
 * @author Yuvaraaj Murthy, Bharg Trivedi
 */
public class CafeAppMain extends Application {
    /**
     * Sets up the main view stage and scene
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CafeAppMain.class.getResource("mainView.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 600, 500);
        stage.setTitle("Rutgers Cafe");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Main method to launch the program
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }
}