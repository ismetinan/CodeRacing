package guicoderacers;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SettingsScreen {
    
    public StackPane createSettingsPane(Stage primaryStage) {
        StackPane settingsPane = new StackPane();
        settingsPane.setStyle("-fx-background-color: seashell;");

        ImageView logoView = CodeRacersGUI.createImageView(CodeRacersGUI.logoIconImage, 375, -1);

        ImageView homeIcon = CodeRacersGUI.createImageView(CodeRacersGUI.homeIconImage, 50, 50);
        
        Button backButton = new Button();
        backButton.setGraphic(homeIcon);
        backButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        backButton.setOnAction(e -> primaryStage.setScene(CodeRacersGUI.mainGameScene));

        Label settingsTitle = new Label("Settings");
        settingsTitle.setStyle("-fx-font-size: 32px; -fx-font-family: 'Arial Black'; -fx-text-fill: #700000;");

        VBox topLeftBox = new VBox(30);
        topLeftBox.setAlignment(Pos.TOP_LEFT);
        topLeftBox.getChildren().addAll(backButton, settingsTitle);

        HBox topIcons = new HBox(30);
        topIcons.setAlignment(Pos.TOP_RIGHT);
        ImageView lightIcon = CodeRacersGUI.createImageView(CodeRacersGUI.lightIconImage, 50, 50);
        ImageView profileIcon = CodeRacersGUI.createImageView(CodeRacersGUI.profileIconImage, 72, 72);
        topIcons.getChildren().addAll(lightIcon, profileIcon);

        StackPane.setAlignment(topLeftBox, Pos.TOP_LEFT);
        StackPane.setAlignment(topIcons, Pos.TOP_RIGHT);
        StackPane.setAlignment(logoView, Pos.TOP_CENTER);

        settingsPane.getChildren().addAll(topLeftBox, topIcons, logoView);
        return settingsPane;
    }

  
}