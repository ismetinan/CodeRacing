package guicoderacers;


import java.util.ArrayList;
import java.util.List;

import controller.Database;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import questions.Question;
import questions.QuestionGenerator;

public class CodeRacersGUI extends Application {
    
    // Images and Scenes that I use in various methods
    protected static Image roadIconImage;
    protected static Image logoIconImage;
    protected static Image profileIconImage;
    protected static Image homeIconImage;
    protected static Image trophyIconImage;
    protected static Image settingsIconImage;
    protected static Image autoBahnIconImage;
    protected static Image redCarIconImage;
    protected static Image darkBlueCarIconImage;
    protected static Image greenCarIconImage;
    protected static Image yellowCarIconImage;
    protected static Image pinkCarIconImage;
    protected static Image orangeCarIconImage;
    protected static Image cyanCarIconImage;
    protected static Scene logInScene;
    protected static Scene mainGameScene;
    protected static Scene leaderboardScene;
    protected static Scene settingsScene;
    protected static Scene lobbiesScene;
    protected static Scene gamePlayScene;
    protected static Scene endGameScene;
    private static Stage primaryStage;

    // Default sizes of screens
    protected static final int defaultWidth = 1000;
    protected static final int defaultHeight = 750;

    private static MediaPlayer mediaPlayer;

    @Override
    public void start(Stage primaryStage) {
        CodeRacersGUI.primaryStage = primaryStage;
        Database.initializeDatabase();
        loadImages();
        playBackgroundMusic();
        LogInScreen logInScreen = new LogInScreen();
        MainScreen mainScreen = new MainScreen();
        StackPane logInPane = logInScreen.createLogInPane(primaryStage);
        StackPane leaderboardPane = new LeaderboardScreen().createLeaderboardPane(primaryStage);
        StackPane settingsPane = new SettingsScreen().createSettingsPane(primaryStage);
        StackPane lobbiesPane = new LobbiesScreen().createLobbiesPane(primaryStage);
        StackPane gamePlayPane = new GameScreen().createGamePane(primaryStage);
        StackPane endGamePane= new EndGameScreen().createEndGamePane(primaryStage);

        Button startButton = logInScreen.getStartButton();
        startButton.setOnAction(e -> {
            String username = logInScreen.getTextField().getText();
            if (username.isEmpty()) {
                System.out.println("Username cannot be empty!");
                return; // Do not proceed if username is empty
            }

            System.out.println("Username: " + username);
            Database.addUser(username); // Add the user to the database
            mainScreen.setUsername(username); // Pass the username to the main screen

            // Create the main game scene and switch to it
            mainGameScene = new Scene(mainScreen.createMainGamePane(primaryStage), defaultWidth, defaultHeight);
            primaryStage.setScene(mainGameScene);
        });

        // Optionally, if you still need a key press:
        logInPane.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) { // Handle Enter key only
                startButton.fire(); // Simulate button click on Enter key press
            }
        });





        logInScene = new Scene(logInPane, defaultWidth, defaultHeight);        
        leaderboardScene = new Scene(leaderboardPane, defaultWidth, defaultHeight);
        settingsScene = new Scene(settingsPane, defaultWidth, defaultHeight);
        lobbiesScene = new Scene(lobbiesPane, defaultWidth, defaultHeight);
        gamePlayScene = new Scene(gamePlayPane, defaultWidth, defaultHeight);
        endGameScene = new Scene(endGamePane, defaultWidth, defaultHeight);


        primaryStage.setTitle("Code Racers");
        primaryStage.setScene(logInScene);
        primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest(event -> mediaPlayer.stop());
        primaryStage.show();
    }

    private void loadImages() {
        roadIconImage = new Image("Images/Roads.png");
        logoIconImage = new Image("Images/GameLogo.png");
        profileIconImage = new Image("Images/ProfileIcon.png");
        homeIconImage = new Image("Images/HomeIcon.png");
        trophyIconImage = new Image("Images/Trophy.png");
        settingsIconImage = new Image("Images/SettingsIcon.png");
        autoBahnIconImage = new Image("Images/AutoBahn.png");
        redCarIconImage = new Image("Images/Cars/RedRaceCar.png");
        darkBlueCarIconImage = new Image("Images/Cars/DarkBlueRaceCar.png");
        greenCarIconImage = new Image("Images/Cars/GreenRaceCar.png");
        yellowCarIconImage = new Image("Images/Cars/YellowRaceCar.png");
        pinkCarIconImage = new Image("Images/Cars/PinkRaceCar.png");
        orangeCarIconImage = new Image("Images/Cars/OrangeRaceCar.png");
        cyanCarIconImage = new Image("Images/Cars/CyanRaceCar.png");

    }

    public static void navigateToMainScreen() {
        primaryStage.setScene(mainGameScene); // Set the scene to the main game screen
        }

        public static void navigateToReviewScreen() {
            VBox reviewLayout = new VBox(20);
            reviewLayout.setAlignment(Pos.CENTER);
            reviewLayout.setStyle("-fx-background-color: black; -fx-padding: 40px;");
        
            // Title Label with Drop Shadow Effect
            Label reviewLabel = new Label("Review Questions");
            reviewLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #ecf0f1;");
            reviewLabel.setWrapText(true);
            reviewLabel.setEffect(new DropShadow(10, Color.BLACK)); // Add shadow for better visual
        
            // Get the list of questions from the database
            ArrayList<Integer> questionIds = GameScreen.getIncorrectId();
        
            // Create a VBox to hold the questions and answers
            VBox questionsLayout = new VBox(10);
            questionsLayout.setAlignment(Pos.CENTER);
        
            // Iterate over the questions and create a label for each question and its answer
            for (int id : questionIds) {
                String questionText = QuestionGenerator.incorrectQuestionReturner(id);
                Label questionLabel = new Label(questionText);
                questionLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: #ecf0f1;");
                questionLabel.setWrapText(true);
                questionLabel.setMaxWidth(800); // Set max width to ensure wrapping
        
                questionsLayout.getChildren().addAll(questionLabel);
            }
        
            // Add the questions layout to a ScrollPane
            ScrollPane scrollPane = new ScrollPane(questionsLayout);
            scrollPane.setFitToWidth(true);
            scrollPane.setStyle(
                "-fx-background: #700000;" +
                "-fx-background-color: #700000;" +
                "-fx-border-color: #700000;" +
                "-fx-border-width: 30;" +
                "-fx-border-radius: 30;" +
                "-fx-background-radius: 30;"
            );
            scrollPane.getStylesheets().add("Styles/scrollpane-style.css");
        
            // Back Button with Hover Effect
            Button backButton = new Button("Back to Home");
            backButton.setStyle(
                "-fx-font-size: 20px; -fx-background-radius: 15; -fx-background-color: #700000; -fx-text-fill: #ecf0f1;"
            );
            backButton.setOnMouseEntered(e -> backButton.setStyle(
                "-fx-font-size: 20px; -fx-background-radius: 15; -fx-background-color: #900000; -fx-text-fill: #ecf0f1;"
            ));
            backButton.setOnMouseExited(e -> backButton.setStyle(
                "-fx-font-size: 20px; -fx-background-radius: 15; -fx-background-color: #700000; -fx-text-fill: #ecf0f1;"
            ));
            backButton.setOnAction(e -> primaryStage.setScene(mainGameScene));
        
            // Road Icon Image
            ImageView roadView = createImageView(roadIconImage, 800, 200);
            roadView.setStyle("-fx-padding: 20px;");
            roadView.setEffect(new DropShadow(20, Color.BLACK)); // Add a shadow for depth
        
            // Add a motivational message with transition effect
            Label motivationalLabel = new Label("Thank you for playing! Keep improving!");
            motivationalLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #ecf0f1;");
            DropShadow textShadow = new DropShadow(20, Color.GOLD);
            motivationalLabel.setEffect(textShadow);
        
            // Add a subtle fade-in effect for the motivational label
            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2), motivationalLabel);
            fadeTransition.setFromValue(0);
            fadeTransition.setToValue(1);
            fadeTransition.setCycleCount(1);
            fadeTransition.play();
        
            reviewLayout.getChildren().addAll(reviewLabel, scrollPane, roadView, motivationalLabel, backButton);
            Scene reviewScene = new Scene(reviewLayout, defaultWidth, defaultHeight);
            primaryStage.setScene(reviewScene);
            primaryStage.show();
        }
        

        public static void navigateToEndGameScreen() {
    VBox endGameLayout = new VBox(20);
    endGameLayout.setAlignment(Pos.CENTER);
    endGameLayout.setStyle("-fx-background-color: black; -fx-padding: 40px;");

    // Title Label with Drop Shadow Effect
    Label resultLabel = new Label("Game Over!");
    resultLabel.setStyle("-fx-font-size: 40px; -fx-font-weight: bold; -fx-text-fill: #ecf0f1;");
    resultLabel.setEffect(new DropShadow(10, Color.BLACK)); // Add shadow for better visual

    // Correct Answers Label
    Label correctAnswersLabel = new Label("Correct Answers: " + GameScreen.getCorrectAnswers());
    correctAnswersLabel.setStyle("-fx-font-size: 28px; -fx-text-fill: #2ecc71;");
    correctAnswersLabel.setAlignment(Pos.CENTER);

    Button reviewButton = new Button("Review");

        // Set button style (optional)
        reviewButton.setStyle("-fx-font-size: 16px; -fx-background-color: black; -fx-text-fill: seashell; -fx-font-family: 'Arial Black'; -fx-background-radius: 30");

        // Add event handler to the button
        reviewButton.setOnAction(e -> navigateToReviewScreen());

    // Incorrect Answers Label
    Label incorrectAnswersLabel = new Label("Incorrect Answers: " + GameScreen.getIncorrectAnswers());
    incorrectAnswersLabel.setStyle("-fx-font-size: 28px; -fx-text-fill: #e74c3c;");
    incorrectAnswersLabel.setAlignment(Pos.CENTER);

    // Back Button with Hover Effect
    Button backButton = new Button("Back to Home");
    backButton.setStyle(
        "-fx-font-size: 20px; -fx-background-radius: 15; -fx-background-color: #700000; -fx-text-fill: #ecf0f1;"
    );
    backButton.setOnMouseEntered(e -> backButton.setStyle(
        "-fx-font-size: 20px; -fx-background-radius: 15; -fx-background-color: #900000; -fx-text-fill: #ecf0f1;"
    ));
    backButton.setOnMouseExited(e -> backButton.setStyle(
        "-fx-font-size: 20px; -fx-background-radius: 15; -fx-background-color: #700000; -fx-text-fill: #ecf0f1;"
    ));
    backButton.setOnAction(e -> primaryStage.setScene(mainGameScene));

    // Road Icon Image
    ImageView roadView = createImageView(roadIconImage, 800, 200);
    roadView.setStyle("-fx-padding: 20px;");
    roadView.setEffect(new DropShadow(20, Color.BLACK)); // Add a shadow for depth

    ImageView redCarIcon = createImageView(redCarIconImage, 80, 50);
    ImageView darkBlueCarIcon = createImageView(darkBlueCarIconImage, 80, 50);
    darkBlueCarIcon.setRotate(180); // Rotate the image

    // Add a motivational message
    Label motivationalLabel = new Label("Thank you for playing! Keep improving!");
    motivationalLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: #ecf0f1;");

    // Add fade-in animation for the components
    FadeTransition fadeTransition = new FadeTransition(Duration.seconds(4), endGameLayout);
    fadeTransition.setFromValue(0);
    fadeTransition.setToValue(1);
    fadeTransition.play();

    endGameLayout.getChildren().addAll(resultLabel, correctAnswersLabel, incorrectAnswersLabel, roadView, motivationalLabel, backButton, reviewButton);
    // Add cars on the road
    HBox carsOnRoad = new HBox(10);
    carsOnRoad.setAlignment(Pos.CENTER);
    carsOnRoad.getChildren().addAll(redCarIcon, darkBlueCarIcon);

    endGameLayout.getChildren().add(carsOnRoad);
    // Create and apply the scene
    Scene endGameScene = new Scene(endGameLayout, defaultWidth, defaultHeight);
    primaryStage.setScene(endGameScene);
    primaryStage.show();
}


        private void playBackgroundMusic() {
        String musicFile = "/Musics/GameOpeningTheme.mp3"; // Ensure the correct path
        Media media = new Media(getClass().getResource(musicFile).toExternalForm());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Loop the music
        mediaPlayer.play();
        mediaPlayer.setVolume(0.18);
    }

    public static void setVolume(double volume) {
        if (mediaPlayer != null) {
            mediaPlayer.setVolume(volume);
        }
    }

    public static void updateBackgroundColor(String color) {
        logInScene.getRoot().setStyle("-fx-background-color: " + color + ";");
        mainGameScene.getRoot().setStyle("-fx-background-color: " + color + ";");
        leaderboardScene.getRoot().setStyle("-fx-background-color: " + color + ";");
        settingsScene.getRoot().setStyle("-fx-background-color: " + color + ";");
        lobbiesScene.getRoot().setStyle("-fx-background-color: " + color + ";");
        gamePlayScene.getRoot().setStyle("-fx-background-color: " + color + ";");
    }

    protected static ImageView createImageView(Image image, int width, int height) {
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        return imageView;
    }

    public static void main(String[] args) {
        launch(args);
    }
}