package guicoderacers;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SettingsScreen {
    private static String selectedColor = "Red";

    protected static double gameSoundVolume = 50;

    public StackPane createSettingsPane(Stage primaryStage) {
        StackPane settingsPane = new StackPane();
        settingsPane.setStyle("-fx-background-color: seashell;");

        ImageView logoView = CodeRacersGUI.createImageView(CodeRacersGUI.logoIconImage, 375, -1);

        ImageView homeIcon = CodeRacersGUI.createImageView(CodeRacersGUI.homeIconImage, 50, 50);

        ImageView roadView = CodeRacersGUI.createImageView(CodeRacersGUI.roadIconImage, CodeRacersGUI.defaultWidth, 500);

        Button backButton = new Button();
        backButton.setGraphic(homeIcon);
        backButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        backButton.setOnAction(e -> primaryStage.setScene(CodeRacersGUI.mainGameScene));

        Label settingsTitle = new Label("Settings");
        settingsTitle.setStyle("-fx-font-size: 32px; -fx-font-family: 'Arial Black'; -fx-text-fill: #700000;");

        HBox topIcons = new HBox(30);
        topIcons.setAlignment(Pos.TOP_RIGHT);
        ImageView profileIcon = CodeRacersGUI.createImageView(CodeRacersGUI.profileIconImage, 72, 72);
        Button profileButton = new Button();
        profileButton.setGraphic(profileIcon);
        profileButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        profileButton.setOnMouseClicked(e -> ProfileScreen.showProfile(primaryStage));
        topIcons.setAlignment(Pos.TOP_RIGHT);
        topIcons.getChildren().addAll(profileButton);

        HBox topBar = new HBox(850);
        topBar.setAlignment(Pos.TOP_LEFT);
        topBar.getChildren().addAll(backButton, topIcons);

        VBox contentBox = new VBox(20);
        contentBox.setAlignment(Pos.CENTER);
        contentBox.setPrefSize(400, 360);

        Label gameSoundLabel = new Label("Game Sound Volume");
        gameSoundLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: seashell; -fx-font-family: 'Arial Black';");
        Slider gameSoundSlider = new Slider(0, 100, 50);
        gameSoundSlider.setMaxSize(300, -1);
        gameSoundSlider.getStylesheets().add(getClass().getResource("/Styles/slider-style.css").toExternalForm());

        gameSoundSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            GameScreen gameScreen = new GameScreen();
            gameScreen.setGameSoundVolume(newValue.doubleValue() / 100);
        });

        Label musicAudioLabel = new Label("Music Audio");
        musicAudioLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: seashell; -fx-font-family: 'Arial Black';");
        Slider musicAudioSlider = new Slider(0, 100, 50);
        musicAudioSlider.setMaxSize(300, -1);
        musicAudioSlider.getStylesheets().add(getClass().getResource("/Styles/slider-style.css").toExternalForm());

        musicAudioSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            CodeRacersGUI.setVolume(newValue.doubleValue() / 100);
        });

        // ComboBox for selecting background color
        Label backgroundColorLabel = new Label("Background Color");
        backgroundColorLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: seashell; -fx-font-family: 'Arial Black';");
        ComboBox<String> backgroundColorComboBox = new ComboBox<>();
        backgroundColorComboBox.getItems().addAll("seashell", "lightgray", "lightblue", "lightgreen", "lightcoral", "lightpink", "lightyellow", "lightseagreen");
        backgroundColorComboBox.setValue("seashell");
        backgroundColorComboBox.getStylesheets().add(getClass().getResource("/Styles/combobox-style.css").toExternalForm());
        backgroundColorComboBox.setOnAction(e -> {
            String selectedColor = backgroundColorComboBox.getValue();
            CodeRacersGUI.updateBackgroundColor(selectedColor);
        });

        Label carColorLabel = new Label("Car Color");
        carColorLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: seashell; -fx-font-family: 'Arial Black';");
        ComboBox<String> carColorComboBox = new ComboBox<>();
        carColorComboBox.getItems().addAll("Red", "DarkBlue", "Green", "Yellow", "Pink", "Orange", "Cyan");
        carColorComboBox.setValue(selectedColor); // Set the initial value to the selected color
        carColorComboBox.getStylesheets().add(getClass().getResource("/Styles/combobox-style.css").toExternalForm());
        carColorComboBox.setOnAction(e -> {
            selectedColor = carColorComboBox.getValue();
        });

        // Add components to the content box
        contentBox.getChildren().addAll(gameSoundLabel, gameSoundSlider, musicAudioLabel, musicAudioSlider, backgroundColorLabel, backgroundColorComboBox, carColorLabel, carColorComboBox);
        contentBox.setAlignment(Pos.TOP_LEFT);

        // ScrollPane for content box
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setMaxSize(650, 350);
        scrollPane.setStyle(
            "-fx-background: #700000;" +
            "-fx-background-color: #700000;" +
            "-fx-border-color: #700000;" +  
            "-fx-border-width: 30;" +      
            "-fx-border-radius: 30;" +     
            "-fx-background-radius: 30;"
        );        
        scrollPane.getStylesheets().add("Styles/scrollpane-style.css");
        scrollPane.setContent(contentBox);

        // Title box with settings icon and title
        ImageView settingsIcon = CodeRacersGUI.createImageView(CodeRacersGUI.settingsIconImage, 50, 50);
        HBox titleBox = new HBox(30);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.getChildren().addAll(settingsIcon, settingsTitle);

        VBox mainContent = new VBox(40);
        mainContent.setAlignment(Pos.CENTER);
        mainContent.getChildren().addAll(titleBox, scrollPane);

        VBox mainBox = new VBox(110);
        mainBox.setAlignment(Pos.TOP_CENTER);
        mainBox.getChildren().addAll(topBar, mainContent);

        StackPane.setAlignment(logoView, Pos.TOP_CENTER);
        StackPane.setAlignment(mainBox, Pos.TOP_CENTER);
        StackPane.setAlignment(roadView, Pos.BOTTOM_CENTER);

        settingsPane.getChildren().addAll(roadView, logoView, mainBox);
        return settingsPane;
    }

    public static String getUserCarColor() {
        return selectedColor;
    }

    public static void setUserCarColor(String color) {
        selectedColor = color;
    }
}