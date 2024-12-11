package guicoderacers;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class LogInScreen {
    public StackPane createLogInPane(Stage primaryStage) {
        StackPane logInPane = new StackPane();
        logInPane.setStyle("-fx-background-color: seashell;");
        ImageView roadView = CodeRacersGUI.createImageView(CodeRacersGUI.roadIconImage, CodeRacersGUI.defaultWidth, 500);
        ImageView logoView = CodeRacersGUI.createImageView(CodeRacersGUI.logoIconImage, 375, -1);
        VBox centerBox = new VBox(30);
        centerBox.setAlignment(Pos.CENTER);
        Rectangle centerBackground = new Rectangle(375, 250, Color.rgb(112, 0, 0));
        centerBackground.setArcWidth(50);
        centerBackground.setArcHeight(50);
        Label titleLabel = new Label("Set Your Car !");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-text-fill: seashell; -fx-font-family: 'Arial Black';");
        TextField usernameField = new TextField();
        usernameField.setPromptText("Enter your username");
        usernameField.setMaxSize(350, 30);
        usernameField.setStyle(
            "-fx-font-size: 16px;" +
            "-fx-font-family: 'Arial Black';" +
            "-fx-prompt-text-fill: gray;" +
            "-fx-border-color: lightgray;" +
            "-fx-border-width: 1;" +
            "-fx-background-radius: 15;" +
            "-fx-border-radius: 15;"
        );
        Button startButton = new Button("Race!");
        startButton.setStyle("-fx-font-size: 16px; -fx-background-color: black; -fx-text-fill: seashell;-fx-font-family: 'Arial Black';;-fx-background-radius: 30");
        startButton.setId("startButton");
        centerBox.getChildren().addAll(titleLabel, usernameField, startButton);
        StackPane.setAlignment(logoView, Pos.TOP_CENTER);
        StackPane.setAlignment(centerBox, Pos.CENTER);
        StackPane.setAlignment(roadView, Pos.BOTTOM_CENTER);
        logInPane.getChildren().addAll(roadView, logoView, centerBackground, centerBox);
        return logInPane;
    }

    
}