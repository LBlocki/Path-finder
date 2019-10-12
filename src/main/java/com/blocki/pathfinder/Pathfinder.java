package com.blocki.pathfinder;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *PathFinder
 * <p>
 * This program visualizes in an interactive way work of most popular graph searching algorithms.
 *
 *
 * @author  Łukasz Błocki
 * @version 1.0
 * @since   2019.12.10
 */
public class Pathfinder extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Board.fxml"));

        Scene scene = new Scene(root, 1200, 800, Color.WHITE);

        primaryStage.setTitle("Pathfinder");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
