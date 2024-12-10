package guicoderacers;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LeaderboardScreen {

    public StackPane createLeaderboardPane(Stage primaryStage) {

        StackPane leaderboardPane = new StackPane();
        leaderboardPane.setStyle("-fx-background-color: seashell;");

        ImageView homeIcon = CodeRacersGUI.createImageView(CodeRacersGUI.homeIconImage, 50, 50);

        Button backButton = new Button();
        backButton.setGraphic(homeIcon);
        backButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        backButton.setOnAction(e -> primaryStage.setScene(CodeRacersGUI.mainGameScene));

        VBox leaderboardBox = new VBox(30);
        leaderboardBox.setAlignment(Pos.CENTER);
        leaderboardBox.setPadding(new Insets(80));

        ImageView trophyIcon = CodeRacersGUI.createImageView(CodeRacersGUI.trophyIconImage, 50, 50);

        Label leaderboardTitle = new Label("Leaderboard");
        leaderboardTitle.setStyle("-fx-font-size: 32px; -fx-font-family: 'Arial Black'; -fx-text-fill: #700000;");

        HBox titleBox = new HBox(30);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.getChildren().addAll(trophyIcon, leaderboardTitle);

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

        VBox playerList = new VBox(10);
        playerList.setStyle("-fx-background-color: #700000; -fx-background-radius: 30;");
        playerList.setPadding(new Insets(10));

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
        ImageView lightIcon = CodeRacersGUI.createImageView(CodeRacersGUI.lightIconImage, 50, 50);
        ImageView profileIcon = CodeRacersGUI.createImageView(CodeRacersGUI.profileIconImage, 72, 72);
        topIcons.getChildren().addAll(lightIcon, profileIcon);

        scrollPane.getStylesheets().add("Styles/scrollpane-style.css");

        scrollPane.setContent(playerList);
        leaderboardBox.getChildren().addAll(titleBox, scrollPane);

        ImageView logoView = CodeRacersGUI.createImageView(CodeRacersGUI.logoIconImage, 375, -1);
        StackPane.setAlignment(logoView, Pos.TOP_CENTER);
        StackPane.setAlignment(backButton, Pos.TOP_LEFT);
        StackPane.setAlignment(leaderboardBox, Pos.CENTER);
        StackPane.setAlignment(topIcons, Pos.TOP_RIGHT);

        leaderboardPane.getChildren().addAll(logoView, topIcons, leaderboardBox, backButton);
        return leaderboardPane;
    }

    
}