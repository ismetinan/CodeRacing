package guicoderacers;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CarColorSelectionScreen {

    public void showCarColorSelection(Stage primaryStage, Runnable onColorSelected) {
        Stage colorSelectionStage = new Stage();
        colorSelectionStage.initModality(Modality.APPLICATION_MODAL);
        colorSelectionStage.initOwner(primaryStage);

        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));

        Label instructionLabel = new Label("Select your car color:");
        instructionLabel.setStyle("-fx-font-size: 18px; -fx-font-family: 'Arial Black'; -fx-text-fill: #700000;");

        ComboBox<String> colorComboBox = new ComboBox<>();
        colorComboBox.getItems().addAll("Red", "DarkBlue", "Green", "Yellow", "Pink", "Orange", "Cyan");
        colorComboBox.getStylesheets().add(getClass().getResource("/Styles/combobox-style.css").toExternalForm());

        // Add a listener to change the background color based on the selected color
        colorComboBox.setOnAction(event -> {
            String selectedColor = colorComboBox.getValue();
            if (selectedColor != null) {
                layout.setStyle("-fx-background-color: " + getColorHex(selectedColor) + ";");
            }
        });

        Button confirmButton = new Button("Confirm");
        confirmButton.setStyle("-fx-background-color: darkgreen; -fx-text-fill: white; -fx-font-size: 16px; -fx-background-radius: 30; -fx-border-radius: 30;");
        confirmButton.setOnAction(event -> {
            String selectedColor = colorComboBox.getValue();
            if (selectedColor != null) {
                SettingsScreen.setUserCarColor(selectedColor); // Set the car color
                Car.setCarColor(selectedColor); // Set the car color for the game
                colorSelectionStage.close(); // Close the color selection stage
                onColorSelected.run(); // Run the provided callback to navigate to the game screen
            }
        });

        layout.getChildren().addAll(instructionLabel, colorComboBox, confirmButton);

        Scene scene = new Scene(layout, 400, 300);
        colorSelectionStage.setScene(scene);
        colorSelectionStage.showAndWait();
    }

    private String getColorHex(String colorName) {
        switch (colorName) {
            case "Red":
                return "#FF0000";
            case "DarkBlue":
                return "#00008B";
            case "Green":
                return "#008000";
            case "Yellow":
                return "#FFFF00";
            case "Pink":
                return "#FFC0CB";
            case "Orange":
                return "#FFA500";
            case "Cyan":
                return "#00FFFF";
            default:
                return "#FFF5EE"; // Default to white if color is not recognized
        }
    }
}