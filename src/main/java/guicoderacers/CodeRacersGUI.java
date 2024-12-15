package guicoderacers;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class CodeRacersGUI extends Application {

    protected static Image roadIconImage;
    protected static Image logoIconImage;
    protected static Image lightIconImage;
    protected static Image profileIconImage;
    protected static Image homeIconImage;
    protected static Image trophyIconImage;
    protected static Image settingsIconImage;
    protected static Image carIconImage;
    protected static Scene logInScene;
    protected static Scene mainGameScene;
    protected static Scene leaderboardScene;
    protected static Scene settingsScene;
    protected static Scene lobbiesScene;

    protected static final int defaultWidth = 1000;
    protected static final int defaultHeight = 750;

    @Override
    public void start(Stage primaryStage) {
        loadImages();

        StackPane logInPane = new LogInScreen().createLogInPane(primaryStage);
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
        if (startButton != null) {
            startButton.setOnAction(event -> primaryStage.setScene(mainGameScene));
        }

        logInPane.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                primaryStage.setScene(mainGameScene);
            }
        });

        try {
            // Load the media from the resources folder
            Media media = new Media(getClass().getResource("/Musics/ALIZADE & BEGE - 247 (Official Music Video).mp3").toExternalForm());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.play();
        } catch (Exception e) {
            System.err.println("Error loading media: " + e.getMessage());
            e.printStackTrace();
        }

        primaryStage.setTitle("Code Racers");
        primaryStage.setScene(logInScene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private void loadImages() {
        try {
            // Load images from the resources folder
            roadIconImage = new Image(getClass().getResource("/Images/Roads.png").toExternalForm());
            logoIconImage = new Image(getClass().getResource("/Images/GameLogo.png").toExternalForm());
            lightIconImage = new Image(getClass().getResource("/Images/LightIcon.png").toExternalForm());
            profileIconImage = new Image(getClass().getResource("/Images/ProfileIcon.png").toExternalForm());
            homeIconImage = new Image(getClass().getResource("/Images/HomeIcon.png").toExternalForm());
            trophyIconImage = new Image(getClass().getResource("/Images/Trophy.png").toExternalForm());
            settingsIconImage = new Image(getClass().getResource("/Images/SettingsIcon.png").toExternalForm());
            carIconImage = new Image(getClass().getResource("/Images/CarIcon.png").toExternalForm());
        } catch (Exception e) {
            System.err.println("Error loading images: " + e.getMessage());
            e.printStackTrace();
        }
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