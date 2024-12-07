

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
    
    //Images and Scenes that I use in various methods
    private Image roadIconImage;
    private Image logoIconImage;
    private Image lightIconImage;
    private Image profileIconImage;
    private Image homeIconImage;
    private Image trophyIconImage;
    private Scene logInScene;
    private Scene mainGameScene;
    private Scene leaderboardScene;
    private Scene settingsScene;

    //Default sizes of screens
    private final int defaultWidth=1000;
    private final int defaultHeight=750;

    
    @Override
    public void start(Stage primaryStage) {
        
        loadImages();
        
        StackPane logInPane = createLogInPane(primaryStage);
        StackPane mainGamePane = createMainGamePane(primaryStage);
        StackPane leaderboardPane = createLeaderboardPane(primaryStage);
        StackPane settingsPane= createSettingsPane(primaryStage);

        
        logInScene = new Scene(logInPane, defaultWidth, defaultHeight);
        mainGameScene = new Scene(mainGamePane, defaultWidth, defaultHeight);
        leaderboardScene = new Scene(leaderboardPane, defaultWidth, defaultHeight);
        settingsScene = new Scene(settingsPane,defaultWidth,defaultHeight);

        
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
        trophyIconImage= new Image("file:/C:/Users/Ege/Desktop/CodeRacing/GUI_JavaFX/src/Images/Trophy.png");
        

    }
    
    private StackPane createLogInPane(Stage primaryStage) {
        StackPane logInPane = new StackPane();
        logInPane.setStyle("-fx-background-color: seashell;");
        ImageView roadView = createImageView(roadIconImage, defaultWidth, 500);
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
        ImageView roadView = createImageView(roadIconImage, defaultWidth, 500);
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
        settingsButton.setOnAction(e -> primaryStage.setScene(settingsScene));
        buttonBox.getChildren().addAll(fullThrottleButton, leaderboardButton, settingsButton);
        StackPane.setAlignment(logoView, Pos.TOP_CENTER);
        StackPane.setAlignment(topIcons, Pos.TOP_RIGHT);
        StackPane.setAlignment(buttonBox, Pos.CENTER);
        StackPane.setAlignment(roadView, Pos.BOTTOM_CENTER);
        mainGamePane.getChildren().addAll(roadView, logoView, topIcons, buttonBox);
        return mainGamePane;
    }

    private StackPane createLeaderboardPane(Stage primaryStage) {
        // Create the leaderboard pane
        StackPane leaderboardPane = new StackPane();
        leaderboardPane.setStyle("-fx-background-color: seashell;");

        ImageView homeIcon = createImageView(homeIconImage, 50, 50);
    
        // Back Button
        Button backButton = new Button();
        backButton.setGraphic(homeIcon);
        backButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
    
        // Set Action Directly
        backButton.setOnAction(e -> {
            System.out.println("Back button clicked");
            primaryStage.setScene(mainGameScene); // Ensure mainGameScene is properly initialized
        });
    
        // Leaderboard Box
        VBox leaderboardBox = new VBox(30);
        leaderboardBox.setAlignment(Pos.CENTER);
        leaderboardBox.setPadding(new Insets(20));
    
        ImageView trophyIcon = createImageView(trophyIconImage, 50, 50);
    
        Label leaderboardTitle = new Label("Leaderboard");
        leaderboardTitle.setStyle("-fx-font-size: 32px; -fx-font-family: 'Arial Black'; -fx-text-fill: #700000;");
    
        HBox titleBox = new HBox(30);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.getChildren().addAll(trophyIcon, leaderboardTitle);
    
        // ScrollPane for Player List
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setMaxSize(600, 375);
        scrollPane.setStyle(
            "-fx-background: #700000;" +
            "-fx-background-color: #700000;" +
            "-fx-border-color: #700000;" +  
            "-fx-border-width: 30;" +      
            "-fx-border-radius: 30;" +     
            "-fx-background-radius: 30;"
        );
    
        VBox playerList = new VBox(10);
        playerList.setStyle("-fx-background-color: #700000; -fx-background-radius: 30;");
        playerList.setPadding(new Insets(10));
    
        // Add Players to the Player List
        String[] players = { "Ege Hamilton", "Vettel Emre", "Eren Uçak", "İsmet Skywalker", "Aybüke Leclerc","1","1","2","2","1","1" };
        int rank = 1;
        for (String player : players) {
            Label playerLabel = new Label(rank + ". " + player);
            playerLabel.setStyle("-fx-text-fill: seashell; -fx-font-size: 18px; -fx-font-family: 'Arial Black';");
            playerList.getChildren().add(playerLabel);
            rank++;
        }
        HBox topIcons = new HBox(30);
        topIcons.setAlignment(Pos.TOP_RIGHT);
        ImageView lightIcon = createImageView(lightIconImage, 50, 50);
        ImageView profileIcon = createImageView(profileIconImage, 72, 72);
        topIcons.getChildren().addAll(lightIcon, profileIcon);

        scrollPane.getStylesheets().add("file:/C:/Users/Ege/Desktop/CodeRacing/GUI_JavaFX/src/Styles/scrollpane-style.css");

    
    
        scrollPane.setContent(playerList);
        leaderboardBox.getChildren().addAll(titleBox, scrollPane);
    
        // Add Elements to Leaderboard Pane
        ImageView logoView = createImageView(logoIconImage, 375, -1);
        StackPane.setAlignment(logoView, Pos.TOP_CENTER);
        StackPane.setAlignment(backButton, Pos.TOP_LEFT);
        StackPane.setAlignment(leaderboardBox, Pos.CENTER);
        StackPane.setAlignment(topIcons, Pos.TOP_RIGHT);

    
        leaderboardPane.getChildren().addAll(logoView,topIcons, leaderboardBox, backButton);
        return leaderboardPane;
    }
    

    private StackPane createSettingsPane(Stage primaryStage) {
        StackPane settingsPane = new StackPane();
        settingsPane.setStyle("-fx-background-color: seashell;");
    
        // Logo at the top
        ImageView logoView = createImageView(logoIconImage, 375, -1);
    
        // Back Button
        Button backButton = new Button();
        ImageView homeIcon = createImageView(homeIconImage, 50, 50);
        backButton.setGraphic(homeIcon);
        backButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
    
        // Debugging: Check mainGameScene
        backButton.setOnAction(e -> {
            if (mainGameScene == null) {
                System.out.println("Error: mainGameScene is null!");
            } else {
                System.out.println("Back button clicked");
                primaryStage.setScene(mainGameScene);
            }
        });
    
        // Title
        Label settingsTitle = new Label("Settings");
        settingsTitle.setStyle("-fx-font-size: 32px; -fx-font-family: 'Arial Black'; -fx-text-fill: #700000;");
    
        // VBox for Title and Back Button
        VBox topLeftBox = new VBox(30);
        topLeftBox.setAlignment(Pos.TOP_LEFT);
        topLeftBox.getChildren().addAll(backButton, settingsTitle);
    
        // Top Icons
        HBox topIcons = new HBox(30);
        topIcons.setAlignment(Pos.TOP_RIGHT);
        ImageView lightIcon = createImageView(lightIconImage, 50, 50);
        ImageView profileIcon = createImageView(profileIconImage, 72, 72);
        topIcons.getChildren().addAll(lightIcon, profileIcon);
    
        // Add to StackPane
        StackPane.setAlignment(topLeftBox, Pos.TOP_LEFT);
        StackPane.setAlignment(topIcons, Pos.TOP_RIGHT);
        StackPane.setAlignment(logoView, Pos.TOP_CENTER);
    
        settingsPane.getChildren().addAll(topLeftBox, topIcons, logoView);
        return settingsPane;
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