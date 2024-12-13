package guicoderacers;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ProfileScreen {

    protected static void showProfile(Stage primaryStage) {
        Stage profileStage = new Stage();
        profileStage.initModality(Modality.APPLICATION_MODAL);
        profileStage.initOwner(primaryStage);

        // Set position of profileStage to top right of primaryStage
        profileStage.setX(primaryStage.getX() + primaryStage.getWidth() - 375);
        profileStage.setY(primaryStage.getY());

        StackPane profilePane = new StackPane();
        profilePane.setStyle("-fx-background-color: lightgray;");
        profilePane.setPrefSize(375, 250);

        ImageView roadView = CodeRacersGUI.createImageView(CodeRacersGUI.roadIconImage, 375, 185);

        ImageView profileIcon = CodeRacersGUI.createImageView(CodeRacersGUI.profileIconImage, 72, 72);
        Label usernameLabel = new Label("\s\sUsername: Ege\s\s");
        usernameLabel.setStyle("-fx-font-size: 18px; -fx-font-family: 'Arial Black';-fx-text-fill: seashell;-fx-background-color:  #700000;-fx-background-radius: 30");
        usernameLabel.setMinSize(-1, 40);
        Label rankingLabel = new Label("\s\sCurrent Ranking: 1\s\s");
        rankingLabel.setStyle("-fx-font-size: 18px; -fx-font-family: 'Arial Black';-fx-text-fill: seashell;-fx-background-color:  #700000;-fx-background-radius: 30");
        rankingLabel.setMinSize(-1, 40);


        Button backButton = new Button("\s\sBack\s\s");
        backButton.setStyle("-fx-font-size: 18px; -fx-text-fill: seashell;-fx-background-color:  #700000; -fx-font-family: 'Arial Black'; -fx-background-radius: 30");
        backButton.setOnAction(e -> profileStage.close());

        profilePane.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                profileStage.close();
            }
        });

        VBox contentBox = new VBox(20);
        contentBox.setAlignment(Pos.CENTER_LEFT);
        contentBox.setPadding(new Insets(20));
        contentBox.getChildren().addAll(profileIcon, usernameLabel, rankingLabel, backButton);

        profilePane.getChildren().add(roadView);
        profilePane.getChildren().add(contentBox);

        Scene profileScene = new Scene(profilePane);
        profileStage.setScene(profileScene);
        profileStage.showAndWait();
    }
}