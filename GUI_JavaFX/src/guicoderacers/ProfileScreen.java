package guicoderacers;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ProfileScreen {

    public void display(Stage primaryStage) {
        Stage profileStage = new Stage();
        profileStage.initModality(Modality.APPLICATION_MODAL);
        profileStage.initOwner(primaryStage);

        StackPane profilePane = new StackPane();
        profilePane.setStyle("-fx-background-color: lightgray;");
        profilePane.setPrefSize(375, 250);

        ImageView profileIcon = CodeRacersGUI.createImageView(CodeRacersGUI.profileIconImage, 72, 72);
        Label usernameLabel = new Label("Username: YourUsername");
        usernameLabel.setStyle("-fx-font-size: 18px; -fx-font-family: 'Arial Black';");
        Label rankingLabel = new Label("Current Ranking: 1");
        rankingLabel.setStyle("-fx-font-size: 18px; -fx-font-family: 'Arial Black';");

        Button backButton = new Button("Back");
        backButton.setStyle("-fx-font-size: 16px; -fx-background-color: black; -fx-text-fill: seashell; -fx-font-family: 'Arial Black'; -fx-background-radius: 30");
        backButton.setOnAction(e -> profileStage.close());

        VBox contentBox = new VBox(20);
        contentBox.setAlignment(Pos.CENTER);
        contentBox.getChildren().addAll(profileIcon, usernameLabel, rankingLabel, backButton);

        profilePane.getChildren().add(contentBox);

        Scene profileScene = new Scene(profilePane);
        profileStage.setScene(profileScene);
        profileStage.showAndWait();
    }
}