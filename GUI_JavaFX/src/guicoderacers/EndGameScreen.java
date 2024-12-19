package guicoderacers;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EndGameScreen {

    public StackPane createEndGamePane(Stage primaryStage) {
        StackPane endGamePane = new StackPane();
        endGamePane.setStyle("-fx-background-color: seashell;");
        ImageView homeIcon = CodeRacersGUI.createImageView(CodeRacersGUI.homeIconImage, 50, 50);

        VBox summaryBox = new VBox(20);
        summaryBox.setAlignment(Pos.CENTER);
        summaryBox.setPadding(new Insets(20));
        summaryBox.setStyle("-fx-background-color: #700000; -fx-border-color: black; -fx-background-radius: 30; -fx-border-radius: 30;");
        summaryBox.setMaxWidth(500);
        summaryBox.setMinWidth(500);
        summaryBox.setMaxHeight(300);
        summaryBox.setMinHeight(300);

        Label summaryLabel = new Label("Game Summary");
        summaryLabel.setStyle("-fx-font-size: 32px; -fx-font-family: 'Arial Black'; -fx-text-fill: seashell;");
        summaryBox.getChildren().add(summaryLabel);

        // Add more summary details here as needed

        // Create a label with two lines of text
        Label twoLineLabel = new Label(GameScreen.getCorrectAnswers() + " correct answers\n" + GameScreen.getIncorrectAnswers() + " incorrect answers");
        twoLineLabel.setStyle("-fx-font-size: 24px; -fx-font-family: 'Arial Black'; -fx-text-fill: seashell;");
        summaryBox.getChildren().add(twoLineLabel);

        Button homeButton = new Button();
        homeButton.setGraphic(homeIcon);
        homeButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        homeButton.setOnAction(e -> CodeRacersGUI.navigateToMainScreen());
        summaryBox.getChildren().add(homeButton);

        HBox gridPlaces = new HBox(47);
        gridPlaces.setAlignment(Pos.BOTTOM_LEFT);

        for (int i = 1; i <= 5; i++) {
            Label positionLabel = new Label(String.valueOf(i));
            positionLabel.setPrefSize(60, 42);
            positionLabel.setAlignment(Pos.CENTER);
            positionLabel.setStyle("-fx-background-color: darkgray; -fx-text-fill: white; -fx-font-size: 20px; -fx-font-weight: bold; -fx-background-radius: 10; -fx-border-radius: 10;");
            if (i == 1) {
                HBox.setMargin(positionLabel, new Insets(0, 0, 0, 10)); // Add 10 pixel margin to the first label
            }
            gridPlaces.getChildren().add(positionLabel);
        }

        StackPane.setAlignment(gridPlaces, Pos.BOTTOM_LEFT); // Align the HBox to the bottom left
        HBox.setMargin(gridPlaces, new Insets(0, 0, 0, 180));

        ImageView roadView = CodeRacersGUI.createImageView(CodeRacersGUI.autoBahnIconImage, CodeRacersGUI.defaultHeight, 500);
        roadView.rotateProperty().set(90);

        ImageView blueCar = CodeRacersGUI.createImageView(CodeRacersGUI.darkBlueCarIconImage, 60, 42);
        blueCar.setRotate(90); // Rotate the car to face the road
        ImageView greenCar = CodeRacersGUI.createImageView(CodeRacersGUI.greenCarIconImage, 60, 42);
        greenCar.setRotate(90); // Rotate the car to face the road
        ImageView yellowCar = CodeRacersGUI.createImageView(CodeRacersGUI.yellowCarIconImage, 60, 42);
        yellowCar.setRotate(90); // Rotate the car to face the road
        ImageView pinkCar = CodeRacersGUI.createImageView(CodeRacersGUI.pinkCarIconImage, 60, 42);
        pinkCar.setRotate(90); // Rotate the car to face the road

        StackPane.setMargin(blueCar, new Insets(540, 708, 0, 0)); // Adjust the margin as needed
        StackPane.setMargin(greenCar, new Insets(540, 493, 0, 0)); // Adjust the margin as needed
        StackPane.setMargin(yellowCar, new Insets(540, 278, 0, 0)); // Adjust the margin as needed
        StackPane.setMargin(pinkCar, new Insets(540, 63, 0, 0)); // Adjust the margin as needed

        StackPane.setMargin(roadView, new Insets(0, 0, 0, -500)); // Add some margin
        StackPane.setAlignment(summaryBox, Pos.TOP_RIGHT);

        endGamePane.getChildren().addAll(roadView, gridPlaces, blueCar, greenCar, yellowCar, pinkCar, summaryBox); // Add the road and the VBox to the StackPane

        return endGamePane;
    }
}