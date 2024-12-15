package guicoderacers;

import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class LobbiesScreen {

    public StackPane createLobbiesPane(Stage primaryStage) {
        StackPane lobbiesPane = new StackPane();
        lobbiesPane.setStyle("-fx-background-color: seashell;");

        ImageView homeIcon = CodeRacersGUI.createImageView(CodeRacersGUI.homeIconImage, 50, 50);
        ImageView roadView = CodeRacersGUI.createImageView(CodeRacersGUI.roadIconImage, CodeRacersGUI.defaultWidth, 500);

        Button backButton = new Button();
        backButton.setGraphic(homeIcon);
        backButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        backButton.setOnAction(e -> primaryStage.setScene(CodeRacersGUI.mainGameScene));

        HBox topIcons = new HBox(30);
        topIcons.setAlignment(Pos.TOP_RIGHT);
        ImageView lightIcon = CodeRacersGUI.createImageView(CodeRacersGUI.lightIconImage, 50, 50);
        ImageView profileIcon = CodeRacersGUI.createImageView(CodeRacersGUI.profileIconImage, 72, 72);
        ImageView roadIcon = CodeRacersGUI.createImageView(CodeRacersGUI.roadIconImage, 50, 50); // Add road icon
        ImageView carIcon = CodeRacersGUI.createImageView(CodeRacersGUI.redCarIconImage, 50, 50); // Add car icon
        Button profileButton = new Button();
        profileButton.setGraphic(profileIcon);
        profileButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        profileButton.setOnMouseClicked(e -> ProfileScreen.showProfile(primaryStage));
        topIcons.getChildren().addAll(lightIcon, roadIcon, carIcon, profileButton); // Add icons to topIcons

        HBox topBar = new HBox(775);
        topBar.setAlignment(Pos.TOP_LEFT);
        topBar.getChildren().addAll(backButton, topIcons);

        VBox playerList = new VBox(15);
        playerList.setStyle("-fx-background-color: #700000; -fx-background-radius: 30;");
        playerList.setPadding(new Insets(15));

        // Add multiple lobby buttons
        String[] lobbies = {"Lobby 1", "Lobby 2", "Lobby 3"};
        for (String lobby : lobbies) {
            Button lobbyButton = new Button(lobby);
            lobbyButton.setStyle("-fx-text-fill: seashell; -fx-font-size: 18px; -fx-font-family: 'Arial Black'; -fx-background-color: #100000; -fx-background-radius: 30;");
            lobbyButton.setMaxWidth(Double.MAX_VALUE);
            
            if (lobby.equals("Lobby 1")) {
                lobbyButton.setOnAction(event ->primaryStage.setScene(CodeRacersGUI.gamePlayScene));
            }
            
            playerList.getChildren().add(lobbyButton);
        }

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
        scrollPane.getStylesheets().add("Styles/scrollpane-style.css");
        scrollPane.setContent(playerList);

        VBox lobbiesBox = new VBox(30);
        lobbiesBox.setAlignment(Pos.CENTER);
        lobbiesBox.setPadding(new Insets(80));
        lobbiesBox.getChildren().add(scrollPane);

        VBox mainBox = new VBox(30);
        mainBox.setAlignment(Pos.TOP_CENTER);
        mainBox.getChildren().addAll(topBar, lobbiesBox);

        ImageView logoView = CodeRacersGUI.createImageView(CodeRacersGUI.logoIconImage, 375, -1);
        StackPane.setAlignment(logoView, Pos.TOP_CENTER);
        StackPane.setAlignment(mainBox, Pos.CENTER);

        lobbiesPane.getChildren().addAll(logoView, mainBox);
        return lobbiesPane;
    }
}