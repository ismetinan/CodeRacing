
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class CodeRacersGUI extends Application {
    private Image roadIconImage;
    private Image logoIconImage;
    private Image lightIconImage;
    private Image profileIconImage;
    private Image homeIconImage;
    private Scene logInScene;
    private Scene mainGameScene;
    private Scene leaderboardScene;
    @Override
    public void start(Stage primaryStage) {
        loadImages();
        StackPane logInPane = createLogInPane(primaryStage);
        StackPane mainGamePane = createMainGamePane(primaryStage);
        StackPane leaderboardPane = createLeaderboardPane(primaryStage);
        logInScene = new Scene(logInPane, 1000, 750);
        mainGameScene = new Scene(mainGamePane, 1000, 750);
        leaderboardScene = new Scene(leaderboardPane, 1000, 750);
        Button startButton = (Button) logInPane.lookup("#startButton");
        startButton.setOnAction(e -> primaryStage.setScene(mainGameScene));
        primaryStage.setTitle("Code Racers");
        primaryStage.setScene(logInScene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    private void loadImages() {
        roadIconImage = new Image("file:/C:/Users/Ege/Desktop/CodeRacing/GUI_JavaFX/src/Images/Roads.png");
        logoIconImage = new Image("file:/C:/Users/Ege/Desktop/CodeRacing/GUI_JavaFX/src/Images/GameLogo.png");
        lightIconImage = new Image("file:/C:/Users/Ege/Desktop/CodeRacing/GUI_JavaFX/src/Images/LightIcon.png");
        profileIconImage = new Image("file:/C:/Users/Ege/Desktop/CodeRacing/GUI_JavaFX/src/Images/ProfilIcon.png");
        homeIconImage = new Image("file:/C:/Users/Ege/Desktop/CodeRacing/GUI_JavaFX/src/Images/HomeIcon.png");
    }
    private StackPane createLogInPane(Stage primaryStage) {
        StackPane logInPane = new StackPane();
        logInPane.setStyle("-fx-background-color: seashell;");
        ImageView roadView = createImageView(roadIconImage, 1000, 500);
        ImageView logoView = createImageView(logoIconImage, 375, -1);
        VBox centerBox = new VBox(30);
        centerBox.setAlignment(Pos.CENTER);
        Rectangle centerBackground = new Rectangle(375, 250, Color.rgb(112, 0, 0));
        centerBackground.setArcWidth(50);
        centerBackground.setArcHeight(50);
        Label titleLabel = new Label("Set Your Car!");
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
    private StackPane createMainGamePane(Stage primaryStage) {
        StackPane mainGamePane = new StackPane();
        mainGamePane.setStyle("-fx-background-color: seashell;");
        ImageView roadView = createImageView(roadIconImage, 1000, 500);
        ImageView logoView = createImageView(logoIconImage, 375, -1);
        HBox topIcons = new HBox(30);
        topIcons.setAlignment(Pos.TOP_RIGHT);
        ImageView lightIcon = createImageView(lightIconImage, 50, 50);
        ImageView profileIcon = createImageView(profileIconImage, 72, 72);
        topIcons.getChildren().addAll(lightIcon, profileIcon);
        VBox buttonBox = new VBox(50);
        buttonBox.setAlignment(Pos.CENTER);
        Button fullThrottleButton = createStyledButton("Full Throttle");
        Button leaderboardButton = createStyledButton("Leaderboard");
        Button settingsButton = createStyledButton("Settings");
        fullThrottleButton.setMinSize(375, 90);
        leaderboardButton.setMinSize(375, 90);
        settingsButton.setMinSize(375, 90);
        leaderboardButton.setOnAction(e -> primaryStage.setScene(leaderboardScene)); 
        buttonBox.getChildren().addAll(fullThrottleButton, leaderboardButton, settingsButton);
        StackPane.setAlignment(logoView, Pos.TOP_CENTER);
        StackPane.setAlignment(topIcons, Pos.TOP_RIGHT);
        StackPane.setAlignment(buttonBox, Pos.CENTER);
        StackPane.setAlignment(roadView, Pos.BOTTOM_CENTER);
        mainGamePane.getChildren().addAll(roadView, logoView, topIcons, buttonBox);
        return mainGamePane;
    }
    private StackPane createLeaderboardPane(Stage primaryStage) {
        StackPane leaderboardPane = new StackPane();
        leaderboardPane.setStyle("-fx-background-color: seashell;");
        Button backButton = new Button();
        ImageView homeIcon = createImageView(homeIconImage, 50, 50);
        backButton.setGraphic(homeIcon);
        backButton.setStyle("-fx-background-color: transparent;");
        backButton.setOnAction(e -> primaryStage.setScene(mainGameScene)); 
        VBox leaderboardBox = new VBox(30);
        leaderboardBox.setAlignment(Pos.TOP_CENTER);
        leaderboardBox.setPadding(new Insets(20));
        Label leaderboardTitle = new Label("Leaderboard");
        leaderboardTitle.setStyle("-fx-font-size: 32px; -fx-font-family: 'Arial Black'; -fx-text-fill: #8B0000;");
        HBox topIcons = new HBox(30);
        topIcons.setAlignment(Pos.TOP_RIGHT);
        ImageView lightIcon = createImageView(lightIconImage, 50, 50);
        ImageView profileIcon = createImageView(profileIconImage, 72, 72);
        topIcons.getChildren().addAll(lightIcon, profileIcon);
        ScrollPane scrollPane = new ScrollPane();
        VBox playerList = new VBox(10);
        playerList.setStyle("-fx-background-color: #8B0000; -fx-background-radius: 10;");
        playerList.setPadding(new Insets(10));
        String[] players = { "Ege Hamilton", "Vettel Emre", "Eren Uçak", "İsmet Skywalker", "Aybüke Leclerc","AHMET","DNED","JKDHEJKE" };
        for (String player : players) {
            Label playerLabel = new Label(player);
            playerLabel.setStyle("-fx-text-fill: seashell; -fx-font-size: 18px; -fx-font-family: 'Arial';");
            playerList.getChildren().add(playerLabel);
        }
        scrollPane.setContent(playerList);
        leaderboardBox.getChildren().addAll(leaderboardTitle, scrollPane);
        ImageView logoView = createImageView(logoIconImage, 375, -1);
        StackPane.setAlignment(logoView, Pos.TOP_CENTER);
        StackPane.setAlignment(backButton, Pos.TOP_LEFT);
        StackPane.setAlignment(topIcons, Pos.TOP_RIGHT);
        StackPane.setAlignment(leaderboardBox, Pos.CENTER);
        leaderboardPane.getChildren().addAll(logoView,leaderboardBox, backButton,topIcons);
        return leaderboardPane;
    }
    private ImageView createImageView(Image image, double width, double height) {
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(width);
        if (height > 0) {
            imageView.setFitHeight(height);
        } else {
            imageView.setPreserveRatio(true);
        }
        return imageView;
    }
    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-font-size: 32px; -fx-background-color: #700000;-fx-font-family: 'Arial Black'; -fx-text-fill: seashell;-fx-background-radius: 30");
        return button;
    }
    public static void main(String[] args) {
        launch(args);
    }
}