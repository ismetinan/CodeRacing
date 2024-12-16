package guicoderacers;

import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.HashMap;

import controller.Client;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class LobbiesScreen {

    protected ArrayList<String> lobbies = new ArrayList<>();
    protected ArrayList<Integer> lobbyPorts = new ArrayList<>(); 
    private GameScreen gameScreen;
    public StackPane createLobbiesPane(Stage primaryStage) {
        StackPane lobbiesPane = new StackPane();
        lobbiesPane.setStyle("-fx-background-color: seashell;");

        ImageView homeIcon = CodeRacersGUI.createImageView(CodeRacersGUI.homeIconImage, 50, 50);
        ImageView roadView = CodeRacersGUI.createImageView(CodeRacersGUI.roadIconImage, CodeRacersGUI.defaultWidth, 500);
        Button backButton = new Button();
        backButton.setGraphic(homeIcon);
        backButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        backButton.setOnAction(e -> primaryStage.setScene(CodeRacersGUI.mainGameScene));

        VBox lobbiesBox = new VBox(30);
        lobbiesBox.setAlignment(Pos.CENTER);
        lobbiesBox.setPadding(new Insets(80));

        ImageView redCarIcon = CodeRacersGUI.createImageView(CodeRacersGUI.redCarIconImage, 75, 50);
        Label lobbiesTitle = new Label("Lobbies");
        lobbiesTitle.setStyle("-fx-font-size: 32px; -fx-font-family: 'Arial Black'; -fx-text-fill: #700000;");

        HBox titleBox = new HBox(30);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.getChildren().addAll(redCarIcon, lobbiesTitle);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setMaxSize(500, 300);
        scrollPane.setStyle(
            "-fx-background: #700000;" +
            "-fx-background-color: #700000;" +
            "-fx-border-color: #700000;" +  
            "-fx-border-width: 30;" +      
            "-fx-border-radius: 30;" +     
            "-fx-background-radius: 30;"
        );

        VBox playerList = new VBox(15);
        playerList.setStyle("-fx-background-color: #700000; -fx-background-radius: 30;");
        playerList.setPadding(new Insets(15));
        addLobby("ismet31'lobby", 12345);
        // Add multiple lobby buttons
        for (String lobby : lobbies) {
            Button lobbyButton = new Button(lobby);
            lobbyButton.setStyle(
                    "-fx-text-fill: seashell; -fx-font-size: 18px; -fx-font-family: 'Arial Black'; -fx-background-color: #100000; -fx-background-radius: 30;");
            lobbyButton.setMaxWidth(Double.MAX_VALUE);

            lobbyButton.setOnAction(event -> {
                Client client = new Client("localhost", (lobbyPorts.get(lobbies.indexOf(lobby))));
                client.connect();
                gameScreen = new GameScreen();
                client.listenForUpdates(gameScreen);
                if (client != null) {
                    System.out.println("Connected to lobby: " + lobby + " on port: " + lobbyPorts.get(lobbies.indexOf(lobby)));
                    primaryStage.setScene(CodeRacersGUI.gamePlayScene); // Navigate to the game screen
                }
                GameScreen gameScreen = new GameScreen();
                gameScreen.playGameSound();
                primaryStage.setScene(CodeRacersGUI.gamePlayScene);
                
            });

            playerList.getChildren().add(lobbyButton);
        }

        scrollPane.getStylesheets().add("Styles/scrollpane-style.css");
        scrollPane.setContent(playerList);

        lobbiesBox.getChildren().addAll(titleBox, scrollPane);

        HBox topIcons = new HBox(30);
        topIcons.setAlignment(Pos.TOP_RIGHT);
        ImageView profileIcon = CodeRacersGUI.createImageView(CodeRacersGUI.profileIconImage, 72, 72);
        Button profileButton = new Button();
        profileButton.setGraphic(profileIcon);
        profileButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        profileButton.setOnMouseClicked(e -> ProfileScreen.showProfile(primaryStage));
        topIcons.getChildren().addAll(profileButton); // Add icons to topIcons

        HBox topBar = new HBox(850);
        topBar.setAlignment(Pos.TOP_LEFT);
        topBar.getChildren().addAll(backButton, topIcons);

        VBox mainBox = new VBox(30);
        mainBox.setAlignment(Pos.TOP_CENTER);
        mainBox.getChildren().addAll(topBar, lobbiesBox);

        ImageView logoView = CodeRacersGUI.createImageView(CodeRacersGUI.logoIconImage, 375, -1);
        StackPane.setAlignment(roadView, Pos.BOTTOM_CENTER);
        StackPane.setAlignment(logoView, Pos.TOP_CENTER);
        StackPane.setAlignment(mainBox, Pos.CENTER);

        lobbiesPane.getChildren().addAll(roadView, logoView, mainBox);
        return lobbiesPane;
    }
    public void addLobby(String lobbyName, int port) {
        lobbies.add(lobbyName);
        lobbyPorts.add(port);
    }
}