package guicoderacers;


import controller.Database;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

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
        StackPane mainGamePane = mainScreen.createMainGamePane(primaryStage);
        StackPane leaderboardPane = new LeaderboardScreen().createLeaderboardPane(primaryStage);
        StackPane settingsPane = new SettingsScreen().createSettingsPane(primaryStage);
        StackPane lobbiesPane = new LobbiesScreen().createLobbiesPane(primaryStage);
        StackPane gamePlayPane = new GameScreen().createGamePane(primaryStage);
        StackPane endGamePane= new EndGameScreen().createEndGamePane(primaryStage);

        Button startButton = (Button) logInPane.lookup("#startButton");
        startButton.setOnAction(event -> primaryStage.setScene(mainGameScene));
        logInScene = new Scene(logInPane, defaultWidth, defaultHeight);
        logInPane.setOnKeyPressed(event -> {
            String username = logInScreen.getTextField().getText();
            System.out.println(username);
            Database.addUser(username);
            mainScreen.setUsername(username);
            
            mainGameScene = new Scene(mainScreen.createMainGamePane(primaryStage), defaultWidth, defaultHeight);
            primaryStage.setScene(mainGameScene);
            
        });




        
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
    public static void navigateToEndGameScreen() {
        primaryStage.setScene(endGameScene); // Set the scene to the end game screen
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