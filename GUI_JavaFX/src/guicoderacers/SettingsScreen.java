package guicoderacers;

import javafx.geometry.Insets;
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
        ImageView lightIcon = CodeRacersGUI.createImageView(CodeRacersGUI.lightIconImage, 50, 50);
        ImageView profileIcon = CodeRacersGUI.createImageView(CodeRacersGUI.profileIconImage, 72, 72);
        Button profileButton = new Button();
        profileButton.setGraphic(profileIcon);
        profileButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        profileButton.setOnMouseClicked(e -> ProfileScreen.showProfile(primaryStage));
        topIcons.setAlignment(Pos.TOP_RIGHT);
        topIcons.getChildren().addAll(lightIcon, profileButton);

        HBox topBar = new HBox(775);
        topBar.setAlignment(Pos.TOP_LEFT);
        topBar.getChildren().addAll(backButton, topIcons);

        VBox contentBox = new VBox(20);
        contentBox.setAlignment(Pos.CENTER);
        contentBox.setPrefSize(400, 360);

        Label gameAudioLabel = new Label("Game Audio");
        gameAudioLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: seashell; -fx-font-family: 'Arial Black';");
        Slider gameAudioSlider = new Slider(0, 100, 50);
        gameAudioSlider.setMaxSize(300, -1);
        gameAudioSlider.getStylesheets().add(getClass().getResource("/Styles/slider-style.css").toExternalForm());

        Label musicAudioLabel = new Label("Music Audio");
        musicAudioLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: seashell; -fx-font-family: 'Arial Black';");
        Slider musicAudioSlider = new Slider(0, 100, 50);
        musicAudioSlider.setMaxSize(300, -1);
        musicAudioSlider.getStylesheets().add(getClass().getResource("/Styles/slider-style.css").toExternalForm());

        musicAudioSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            CodeRacersGUI.setVolume(newValue.doubleValue() / 100);
        });

        Label ege = new Label("Ege çok sıkıldı!!! :(");
        ege.setStyle("-fx-font-size: 18px; -fx-text-fill: seashell; -fx-font-family: 'Arial Black';");
        Slider egeL = new Slider(0, 100, 50);
        egeL.setMaxSize(300, -1);
        egeL.getStylesheets().add(getClass().getResource("/Styles/slider-style.css").toExternalForm());

        Label ural = new Label("Bana selam verin");
        ural.setStyle("-fx-font-size: 18px; -fx-text-fill: seashell; -fx-font-family: 'Arial Black';");
        Slider uralL = new Slider(0, 100, 50);
        uralL.setMaxSize(300, -1);
        uralL.getStylesheets().add(getClass().getResource("/Styles/slider-style.css").toExternalForm());

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

        // Add components to the content box
        contentBox.getChildren().addAll(gameAudioLabel, gameAudioSlider, musicAudioLabel, musicAudioSlider, ege, egeL, ural, uralL, backgroundColorLabel, backgroundColorComboBox);
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
}