package com.example.hellofx;


import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class HelloJavaFX extends Application {


    @Override
    public void start(Stage stage) {


        // Welcome message
        Label message = new Label("Welcome, Agatha Mulenga!");


        // Start button
        Button startButton = new Button("Start");


        // Reset button
        Button resetButton = new Button("Reset");


        // Start button action
        startButton.setOnAction(event -> {
            message.setText("Welcome to JavaFX!");
        });


        // Reset button action
        resetButton.setOnAction(event -> {
            message.setText("Welcome, Agatha Mulenga!");
        });


        // Arrange the controls vertically
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(message, startButton, resetButton);


        // Create the scene
        Scene scene = new Scene(layout, 500, 300);


        // Window title - PUT YOUR ACTUAL STUDENT NUMBER HERE
        stage.setTitle("JavaFX Lab - Student No: 202501442");


        // Set the scene and display the window
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}