package guicoderacers;


import controller.Server;
import controller.Database;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainScreen {
    private String username;


    public StackPane createMainGamePane(Stage primaryStage) {

        StackPane mainGamePane = new StackPane();
        mainGamePane.setStyle("-fx-background-color: seashell;");
        ImageView roadView = CodeRacersGUI.createImageView(CodeRacersGUI.roadIconImage, CodeRacersGUI.defaultWidth, 500);
        ImageView logoView = CodeRacersGUI.createImageView(CodeRacersGUI.logoIconImage, 375, -1);
        HBox topIcons = new HBox(30);
        topIcons.setAlignment(Pos.TOP_RIGHT);
        ImageView profileIcon = CodeRacersGUI.createImageView(CodeRacersGUI.profileIconImage, 72, 72);
        Button profileButton = new Button();
        profileButton.setGraphic(profileIcon);
        profileButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        profileButton.setOnMouseClicked(e -> ProfileScreen.showProfile(primaryStage));        ;
        topIcons.getChildren().addAll(profileButton);


        VBox buttonBox = new VBox(50);
        buttonBox.setAlignment(Pos.CENTER);
        Button fullThrottleButton = createStyledButton("Full Throttle !");
        Button leaderboardButton = createStyledButton("Leaderboard");
        Button settingsButton = createStyledButton("Settings");
        fullThrottleButton.setMinSize(375, 90);
        leaderboardButton.setMinSize(375, 90);
        settingsButton.setMinSize(375, 90);
        leaderboardButton.setOnAction(e -> primaryStage.setScene(CodeRacersGUI.leaderboardScene));
        settingsButton.setOnAction(e -> primaryStage.setScene(CodeRacersGUI.settingsScene));
        fullThrottleButton.setOnAction(e -> {
            // Create a new server instance dynamically for a new lobby
            
            String lobbyName = username + "'s Lobby";
            int lobbyPort = Server.getNextPort();
            Server.startServerInBackground(lobbyPort, lobbyName);
            System.out.println("New lobby created: " + lobbyName + " on port " + lobbyPort);
            LobbiesScreen lobbiesScreen = new LobbiesScreen();
            lobbiesScreen.addLobby(lobbyName, lobbyPort);
            StackPane lobbiesPane = lobbiesScreen.createLobbiesPane(primaryStage);
            Scene lobbiesScene = new Scene(lobbiesPane, CodeRacersGUI.defaultWidth, CodeRacersGUI.defaultHeight);

            
            CodeRacersGUI.lobbiesScene = lobbiesScene;
            CodeRacersGUI.updateBackgroundColor(SettingsScreen.selectedColor);

            primaryStage.setScene(CodeRacersGUI.lobbiesScene);

        });
        buttonBox.getChildren().addAll(fullThrottleButton, leaderboardButton, settingsButton);

        VBox mainContent = new VBox(20);
        mainContent.setAlignment(Pos.CENTER);
        mainContent.getChildren().addAll( buttonBox);

        StackPane.setAlignment(mainContent, Pos.CENTER);
        StackPane.setAlignment(logoView, Pos.TOP_CENTER);
        StackPane.setAlignment(topIcons, Pos.TOP_RIGHT);
        StackPane.setAlignment(roadView, Pos.BOTTOM_CENTER);
        mainGamePane.getChildren().addAll(roadView, logoView,topIcons);
        mainGamePane.getChildren().add(mainContent);
        VBox combinedBox = new VBox(130);
        combinedBox.setAlignment(Pos.TOP_CENTER);
        combinedBox.getChildren().addAll(topIcons, mainContent);
        mainGamePane.getChildren().add(combinedBox);
        return mainGamePane;
    }

    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-font-size: 24px; -fx-background-color: #700000 ; -fx-text-fill: seashell; -fx-font-family: 'Arial Black'; -fx-background-radius: 30");
        return button;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}