package guicoderacers;

import javafx.animation.*;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class LogInScreen {
    private TextField textField;
    private Button startButton = new Button("Race!");

    public StackPane createLogInPane(Stage primaryStage) {
        StackPane logInPane = new StackPane();
        logInPane.setStyle("-fx-background-color: linear-gradient(to bottom, #700000,rgb(247, 247, 247));");
        
        ImageView roadView = CodeRacersGUI.createImageView(CodeRacersGUI.roadIconImage, CodeRacersGUI.defaultWidth, 500);
        ImageView logoView = CodeRacersGUI.createImageView(CodeRacersGUI.logoIconImage, 375, -1);

        

        VBox centerBox = new VBox(20);
        centerBox.setAlignment(Pos.CENTER);

        Rectangle centerBackground = new Rectangle(400, 280, Color.rgb(128, 0, 0, 0.8));
        centerBackground.setArcWidth(50);
        centerBackground.setArcHeight(50);
        centerBackground.setEffect(new DropShadow(30, Color.BLACK));

        Label titleLabel = new Label("Welcome Racer!");
        titleLabel.setStyle("-fx-font-size: 30px; -fx-text-fill: seashell; -fx-font-family: 'Arial Black';");
        titleLabel.setEffect(new Glow(0.5)); // Add a glow effect to the title

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

        startButton.setStyle("-fx-font-size: 16px; -fx-background-color: black; -fx-text-fill: seashell;-fx-font-family: 'Arial Black';-fx-background-radius: 30");
        startButton.setId("startButton"); // Set the ID for the button

        // Add hover effect to the start button
        startButton.setOnMouseEntered(e -> startButton.setStyle("-fx-font-size: 16px; -fx-background-color: darkred; -fx-text-fill: seashell;-fx-font-family: 'Arial Black';-fx-background-radius: 30"));
        startButton.setOnMouseExited(e -> startButton.setStyle("-fx-font-size: 16px; -fx-background-color: black; -fx-text-fill: seashell;-fx-font-family: 'Arial Black';-fx-background-radius: 30"));

        // Add scale transition to the start button
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), startButton);
        scaleTransition.setByX(0.1);
        scaleTransition.setByY(0.1);
        startButton.setOnMousePressed(e -> scaleTransition.playFromStart());

        // Add fade transition to the logo
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(2000), logoView);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();

        // Add car images and animations
        ImageView redCar = CodeRacersGUI.createImageView(CodeRacersGUI.redCarIconImage, 100, 50);
        redCar.rotateProperty().set(180);
        ImageView blueCar = CodeRacersGUI.createImageView(CodeRacersGUI.darkBlueCarIconImage, 100, 50);
        ImageView yellowCar = CodeRacersGUI.createImageView(CodeRacersGUI.yellowCarIconImage, 100, 50);
        yellowCar.rotateProperty().set(135);

        TranslateTransition redCarTransition = new TranslateTransition(Duration.seconds(5), redCar);

        redCarTransition.setFromX(-CodeRacersGUI.defaultWidth / 2);
        redCarTransition.setToX(CodeRacersGUI.defaultWidth / 2);
        redCarTransition.setCycleCount(Animation.INDEFINITE);
        redCarTransition.setAutoReverse(true);
        redCarTransition.play();

        TranslateTransition blueCarTransition = new TranslateTransition(Duration.seconds(7), blueCar);
        blueCarTransition.setFromX(CodeRacersGUI.defaultWidth / 2);
        blueCarTransition.setToX(-CodeRacersGUI.defaultWidth / 2);
        blueCarTransition.setCycleCount(Animation.INDEFINITE);
        blueCarTransition.setAutoReverse(true);
        blueCarTransition.play();
        

        TranslateTransition yellowCarTransition = new TranslateTransition(Duration.seconds(4), yellowCar);
        yellowCarTransition.setFromY(CodeRacersGUI.defaultWidth / 2);
        yellowCarTransition.setToY(-CodeRacersGUI.defaultWidth / 2);
        yellowCarTransition.setFromX(-(CodeRacersGUI.defaultWidth / 2-100));
        yellowCarTransition.setToX(CodeRacersGUI.defaultWidth / 2-100);
        yellowCarTransition.setCycleCount(Animation.INDEFINITE);
        yellowCarTransition.setAutoReverse(true);
        yellowCarTransition.play();

        centerBox.getChildren().addAll(titleLabel, usernameField, startButton);
        StackPane.setAlignment(logoView, Pos.TOP_CENTER);
        StackPane.setAlignment(centerBox, Pos.CENTER);
        StackPane.setAlignment(roadView, Pos.BOTTOM_CENTER);
        StackPane.setAlignment(redCar, Pos.BOTTOM_LEFT);
        StackPane.setAlignment(blueCar, Pos.BOTTOM_RIGHT);
        logInPane.getChildren().addAll(roadView, logoView, centerBackground, centerBox, redCar, blueCar,yellowCar);

        this.textField = usernameField;
        return logInPane;
    }

    public TextField getTextField() {
        return textField;
    }
    public Button getStartButton() {
        return startButton;
    }
}