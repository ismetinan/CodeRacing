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

public class LobbiesScreen {

    public StackPane createLobbiesPane(Stage primaryStage) {
        StackPane lobbiesPane = new StackPane();
        lobbiesPane.setStyle("-fx-background-color: seashell;");

        // Road view
        ImageView roadView = CodeRacersGUI.createImageView(CodeRacersGUI.roadIconImage, CodeRacersGUI.defaultWidth, 500);

        // Logo view
        ImageView logoView = CodeRacersGUI.createImageView(CodeRacersGUI.logoIconImage, 375, -1);

        // Back button
        Button backButton = new Button();
        ImageView homeIcon = CodeRacersGUI.createImageView(CodeRacersGUI.homeIconImage, 50, 50);
        backButton.setGraphic(homeIcon);
        backButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        backButton.setOnAction(e -> primaryStage.setScene(CodeRacersGUI.mainGameScene));

        // Top right icons
        HBox topRightIcons = new HBox(30);
        topRightIcons.setAlignment(Pos.TOP_RIGHT);
        ImageView lightIcon = CodeRacersGUI.createImageView(CodeRacersGUI.lightIconImage, 50, 50);
        ImageView profileIcon = CodeRacersGUI.createImageView(CodeRacersGUI.profileIconImage, 72, 72);
        topRightIcons.getChildren().addAll(lightIcon, profileIcon);

        // Lobbies box
        VBox lobbiesBox = new VBox(20);
        lobbiesBox.setAlignment(Pos.CENTER);
        lobbiesBox.setPadding(new Insets(20));

        Label lobbiesTitle = new Label("Lobbies");
        lobbiesTitle.setStyle("-fx-font-size: 32px; -fx-font-family: 'Arial Black'; -fx-text-fill: seashell;");

        // ScrollPane for lobby list
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

        VBox lobbyList = new VBox(10);
        lobbyList.setAlignment(Pos.CENTER);
        lobbyList.setPadding(new Insets(10));
        lobbyList.setStyle("-fx-background-color: #700000; -fx-background-radius: 15;");

        // Dummy lobby data
        String[] lobbies = {  "Ege Hamilton", "Vettel Emre", "Eren Uçak", "İsmet Skywalker", "Aybüke Leclerc","1","1","2","2","1","1" };
        for (String lobby : lobbies) {
            Label lobbyLabel = new Label(lobby);
            lobbyLabel.setStyle("-fx-text-fill: seashell; -fx-font-size: 18px; -fx-font-family: 'Arial Black';");
            lobbyList.getChildren().add(lobbyLabel);
        }

        scrollPane.setContent(lobbyList);

        // Add title and scroll pane to lobbies box
        lobbiesBox.getChildren().addAll(lobbiesTitle, scrollPane);

        // Add all elements to the main StackPane
        lobbiesPane.getChildren().addAll(roadView, logoView, backButton, topRightIcons, lobbiesBox);
        StackPane.setAlignment(roadView, Pos.BOTTOM_CENTER);
        StackPane.setAlignment(logoView, Pos.TOP_CENTER);
        StackPane.setAlignment(backButton, Pos.TOP_LEFT);
        StackPane.setAlignment(lobbiesBox, Pos.CENTER);

        return lobbiesPane;
    }
}