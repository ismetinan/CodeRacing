package guicoderacers;

import java.util.ArrayList;
import java.util.List;

import controller.Database;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ProfileScreen {
    static List<String> rankingL;
    protected static void showProfile(Stage primaryStage) {
        Stage profileStage = new Stage();
        String username = Database.getLatestUsername();
        rankingL = Database.getLeaderboard();
        List<String> userRanking = new ArrayList<String>();
        for (int i = 0; i < rankingL.size();i++) {
            String tmp = rankingL.get(i);
            
            userRanking.add(tmp.substring(0,tmp.indexOf("-") - 1));
        }

        int ranking = userRanking.indexOf(username);

        profileStage.initModality(Modality.APPLICATION_MODAL);
        profileStage.initOwner(primaryStage);

        // Set position of profileStage to top right of primaryStage
        profileStage.setX(primaryStage.getX() + primaryStage.getWidth() - 375);
        profileStage.setY(primaryStage.getY());

        StackPane profilePane = new StackPane();
        profilePane.setStyle("-fx-background-color: seashell;");
        profilePane.setPrefSize(375, 250);

        ImageView roadView = CodeRacersGUI.createImageView(CodeRacersGUI.roadIconImage, 375, 185);

        ImageView profileIcon = CodeRacersGUI.createImageView(CodeRacersGUI.profileIconImage, 72, 72);
        Label usernameLabel = new Label("\s\sUsername:\s\s"+ username+"\s\s");
        usernameLabel.setStyle("-fx-font-size: 18px; -fx-font-family: 'Arial Black';-fx-text-fill: seashell;-fx-background-color:  #700000;-fx-background-radius: 30");
        usernameLabel.setMinSize(-1, 40);
        Label rankingLabel = new Label("\s\sCurrent Ranking: "+ (ranking + 1) + "\s\s");
        rankingLabel.setStyle("-fx-font-size: 18px; -fx-font-family: 'Arial Black';-fx-text-fill: seashell;-fx-background-color:  #700000;-fx-background-radius: 30");
        rankingLabel.setMinSize(-1, 40);


        Button backButton = new Button("\s\sBack\s\s");
        backButton.setStyle("-fx-font-size: 18px; -fx-text-fill: seashell;-fx-background-color:  #100000; -fx-font-family: 'Arial Black'; -fx-background-radius: 30");
        backButton.setOnAction(e -> profileStage.close());

        profilePane.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                profileStage.close();
            }
        });

        VBox contentBox = new VBox(20);
        contentBox.setAlignment(Pos.CENTER_RIGHT);
        contentBox.setPadding(new Insets(20));
        contentBox.getChildren().addAll(profileIcon, usernameLabel, rankingLabel);

        VBox buttonBox = new VBox(20);
        buttonBox.setAlignment(Pos.BOTTOM_LEFT);
        buttonBox.setPadding(new Insets(20));
        buttonBox.getChildren().add(backButton);

        profilePane.getChildren().add(roadView);
        profilePane.getChildren().add(contentBox);
        profilePane.getChildren().add(buttonBox);

        Scene profileScene = new Scene(profilePane);
        profileStage.setScene(profileScene);
        profileStage.showAndWait();
    }
}