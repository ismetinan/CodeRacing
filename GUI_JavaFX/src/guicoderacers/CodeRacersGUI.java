

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class CodeRacersGUI extends Application {
    
    // Images and Scenes that I use in various methods
    protected static Image roadIconImage;
    protected static Image logoIconImage;
    protected static Image lightIconImage;
    protected static Image profileIconImage;
    protected static Image homeIconImage;
    protected static Image trophyIconImage;
    protected static Image settingsIconImage;
    protected static Image cyanF1CarImage;
    protected static Image redF1CarImage;
    protected static Image greenF1CarImage;
    protected static Image yellowF1CarImage;
    protected static Image blueF1CarImage;
    protected static Image orangeF1CarImage;
    protected static Image pinkF1CarImage;
    protected static Scene logInScene;
    protected static Scene mainGameScene;
    protected static Scene leaderboardScene;
    protected static Scene settingsScene;
    protected static Scene lobbiesScene;

    // Default sizes of screens
    protected static final int defaultWidth = 1000;
    protected static final int defaultHeight = 750;

    private static MediaPlayer mediaPlayer;

    @Override
    public void start(Stage primaryStage) {
        Database.initializeDatabase();
        loadImages();
        playBackgroundMusic();
        LogInScreen logInScreen = new LogInScreen();
        StackPane logInPane = logInScreen.createLogInPane(primaryStage);
        StackPane mainGamePane = new MainScreen().createMainGamePane(primaryStage);
        StackPane leaderboardPane = new LeaderboardScreen().createLeaderboardPane(primaryStage);
        StackPane settingsPane = new SettingsScreen().createSettingsPane(primaryStage);
        StackPane lobbiesPane = new LobbiesScreen().createLobbiesPane(primaryStage);

        logInScene = new Scene(logInPane, defaultWidth, defaultHeight);
        mainGameScene = new Scene(mainGamePane, defaultWidth, defaultHeight);
        leaderboardScene = new Scene(leaderboardPane, defaultWidth, defaultHeight);
        settingsScene = new Scene(settingsPane, defaultWidth, defaultHeight);
        lobbiesScene = new Scene(lobbiesPane, defaultWidth, defaultHeight); 

        Button startButton = (Button) logInPane.lookup("#startButton");
        startButton.setOnAction(event -> primaryStage.setScene(mainGameScene));

        logInPane.setOnKeyPressed(event -> {
            String username = logInScreen.getTextField().getText();
            System.out.println(username);
            Database.addUser(username); // Save to database
            primaryStage.setScene(mainGameScene);
            primaryStage.setScene(mainGameScene);

        });

        primaryStage.setTitle("Code Racers");
        primaryStage.setScene(logInScene);
        primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest(event -> mediaPlayer.stop()); // Stop music when window is closed
        primaryStage.show();
    }

    private void loadImages() {
        roadIconImage = new Image("Images/Roads.png");
        logoIconImage = new Image("Images/GameLogo.png");
        lightIconImage = new Image("Images/LightIcon.png");
        profileIconImage = new Image("Images/ProfileIcon.png");
        homeIconImage = new Image("Images/HomeIcon.png");
        trophyIconImage = new Image("Images/Trophy.png");
        settingsIconImage = new Image("Images/SettingsIcon.png");
        cyanF1CarImage = new Image("Images/Cars/CyanRaceCar.png");
        redF1CarImage = new Image("Images/Cars/RedRaceCar.png");
        greenF1CarImage = new Image("Images/Cars/GreenRaceCar.png");
        yellowF1CarImage = new Image("Images/Cars/YellowRaceCar.png");
        blueF1CarImage = new Image("Images/Cars/DarkBlueRaceCar.png");
        orangeF1CarImage = new Image("Images/Cars/OrangeRaceCar.png");
        pinkF1CarImage = new Image("Images/Cars/PinkRaceCar.png");

    }

    private void playBackgroundMusic() {
        String musicFile = "/Musics/GameOpeningTheme.mp3"; // Ensure the correct path
        Media media = new Media(getClass().getResource(musicFile).toExternalForm());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Loop the music
        mediaPlayer.play();
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