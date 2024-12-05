import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;

public class CodeRacersGUI extends Application {

    @Override
    public void start(Stage primaryStage) {
        // --- First Screen ---
        StackPane firstRoot = new StackPane();
        firstRoot.setStyle("-fx-background-color: white;");

        // Road Background
        Image roadImage = new Image("file:/C:/Users/Ege/Desktop/CodeRacing/GUI_JavaFX/src/Images/Roads.png");
        ImageView roadView = new ImageView(roadImage);
        roadView.setFitWidth(800);
        roadView.setPreserveRatio(true);

        // Game Logo
        Image logoImage = new Image("file:/C:/Users/Ege/Desktop/CodeRacing/GUI_JavaFX/src/Images/GameLogo.png");
        ImageView logoView = new ImageView(logoImage);
        logoView.setFitWidth(300);
        logoView.setPreserveRatio(true);

        // Race Button
        VBox firstCenterBox = new VBox(15);
        firstCenterBox.setAlignment(Pos.CENTER);

        Rectangle centerBackground = new Rectangle(300, 200, Color.RED);
        centerBackground.setArcWidth(20);
        centerBackground.setArcHeight(20);

        Button raceButton = new Button("Race!");
        raceButton.setStyle("-fx-font-size: 16px; -fx-background-color: black; -fx-text-fill: red;");

        firstCenterBox.getChildren().add(raceButton);
        StackPane.setAlignment(logoView, Pos.TOP_CENTER);
        StackPane.setAlignment(firstCenterBox, Pos.CENTER);
        firstRoot.getChildren().addAll(roadView, logoView, centerBackground, firstCenterBox);

        Scene firstScene = new Scene(firstRoot, 800, 600);

        // --- Second Screen ---
        StackPane secondRoot = new StackPane();
        secondRoot.setStyle("-fx-background-color: white;");

        ImageView secondRoadView = new ImageView(roadImage);
        secondRoadView.setFitWidth(800);
        secondRoadView.setPreserveRatio(true);

        ImageView secondLogoView = new ImageView(logoImage);
        secondLogoView.setFitWidth(300);
        secondLogoView.setPreserveRatio(true);

        // Top Right Icons
        HBox topIcons = new HBox(10);
        topIcons.setAlignment(Pos.TOP_RIGHT);
        ImageView lightIcon = new ImageView(new Image("file:/C:/Users/Ege/Desktop/CodeRacing/GUI_JavaFX/src/Images/LightIcon.png"));
        ImageView profileIcon = new ImageView(new Image("file:/C:/Users/Ege/Desktop/CodeRacing/GUI_JavaFX/src/Images/ProfilIcon.png"));
        lightIcon.setFitWidth(30);
        lightIcon.setFitHeight(30);
        profileIcon.setFitWidth(50);
        profileIcon.setFitHeight(50);
        topIcons.getChildren().addAll(lightIcon, profileIcon);

        // Buttons
        VBox buttonBox = new VBox(15);
        buttonBox.setAlignment(Pos.CENTER);

        Button fullThrottleButton = new Button("Full Throttle");
        Button leaderboardButton = new Button("Leaderboard");
        Button settingsButton = new Button("Settings");

        fullThrottleButton.setStyle("-fx-font-size: 16px; -fx-background-color: #8B0000; -fx-text-fill: white;");
        leaderboardButton.setStyle("-fx-font-size: 16px; -fx-background-color: #8B0000; -fx-text-fill: white;");
        settingsButton.setStyle("-fx-font-size: 16px; -fx-background-color: #8B0000; -fx-text-fill: white;");

        buttonBox.getChildren().addAll(fullThrottleButton, leaderboardButton, settingsButton);

        StackPane.setAlignment(secondLogoView, Pos.TOP_CENTER);
        StackPane.setAlignment(topIcons, Pos.TOP_RIGHT);
        StackPane.setAlignment(buttonBox, Pos.CENTER);

        secondRoot.getChildren().addAll(secondRoadView, secondLogoView, topIcons, buttonBox);

        Scene secondScene = new Scene(secondRoot, 800, 600);

        // Transition
        raceButton.setOnAction(e -> primaryStage.setScene(secondScene));

        primaryStage.setTitle("Code Racers");
        primaryStage.setScene(firstScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
