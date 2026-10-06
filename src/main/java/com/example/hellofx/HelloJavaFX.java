// Student Number: 202501442
        package com.example.hellofx;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class HelloJavaFX extends Application {


    private final ObservableList<Customer> customers = FXCollections.observableArrayList();


    @Override
    public void start(Stage stage) {
        // Window Title
        stage.setTitle("JavaFX Lab - Student ID: 202501442");


        // Form Controls
        Label nameLabel = new Label("Customer Name:");
        TextField nameField = new TextField();
        nameField.setPromptText("e.g., Mary Banda");
        nameLabel.setLabelFor(nameField);


        Label provinceLabel = new Label("Province:");
        ComboBox<String> provinceBox = new ComboBox<>();
        provinceBox.getItems().addAll(
                "Central", "Copperbelt", "Eastern", "Luapula",
                "Lusaka", "Muchinga", "Northern", "North-Western",
                "Southern", "Western"
        );
        provinceBox.setPromptText("Choose a province");


        Button saveButton = new Button("Save Customer");
        saveButton.setDefaultButton(true);


        Label statusLabel = new Label();


        // Form Layout (GridPane)
        GridPane formGrid = new GridPane();
        formGrid.setHgap(10);
        formGrid.setVgap(10);
        formGrid.add(nameLabel, 0, 0);
        formGrid.add(nameField, 1, 0);
        formGrid.add(provinceLabel, 0, 1);
        formGrid.add(provinceBox, 1, 1);
        formGrid.add(saveButton, 1, 2);


        // Table Setup
        TableView<Customer> table = new TableView<>();
        table.setItems(customers);


        TableColumn<Customer, String> nameCol = new TableColumn<>("Customer Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameCol.setPrefWidth(200);


        TableColumn<Customer, String> provinceCol = new TableColumn<>("Province");
        provinceCol.setCellValueFactory(new PropertyValueFactory<>("province"));
        provinceCol.setPrefWidth(150);


        table.getColumns().add(nameCol);
        table.getColumns().add(provinceCol);


        // Delete Button Setup
        Button deleteButton = new Button("Delete Selected");

        // Save Action & Input Validation
        saveButton.setOnAction(event -> {
            String name = nameField.getText().trim();
            if (name.isEmpty()) {
                statusLabel.setText("Enter the customer name.");
                nameField.requestFocus();
                return;
            }


            String province = provinceBox.getValue();
            if (province == null) {
                statusLabel.setText("Choose a province.");
                provinceBox.requestFocus();
                return;
            }


            customers.add(new Customer(name, province));
            statusLabel.setText("Customer saved successfully.");


            // Clear inputs after successful save
            nameField.clear();
            provinceBox.setValue(null);
            nameField.requestFocus();
        });


        // Delete Action with Confirmation Dialog
        deleteButton.setOnAction(event -> {
            Customer selected = table.getSelectionModel().getSelectedItem();
            if (selected == null) {
                statusLabel.setText("Select a customer to delete.");
                return;
            }


            ButtonType deleteType = new ButtonType("Delete", ButtonBar.ButtonData.OK_DONE);
            Alert ask = new Alert(Alert.AlertType.CONFIRMATION, "Delete the selected customer?", deleteType, ButtonType.CANCEL);
            ask.setHeaderText("Confirm Deletion");


            if (ask.showAndWait().orElse(ButtonType.CANCEL) == deleteType) {
                customers.remove(selected);
                statusLabel.setText("Customer deleted.");
            }
        });


        // Action Buttons Row
        HBox actionBox = new HBox(10, deleteButton);
        actionBox.setAlignment(Pos.CENTER_LEFT);


        // Main Layout
        VBox root = new VBox(15);
        root.setPadding(new Insets(15));
        root.getChildren().addAll(formGrid, statusLabel, table, actionBox);


        // Create Scene and Show
        Scene scene = new Scene(root, 420, 500);
        stage.setScene(scene);
        stage.show();


        nameField.requestFocus();
    }


    public static void main(String[] args) {
        launch(args);
    }
}