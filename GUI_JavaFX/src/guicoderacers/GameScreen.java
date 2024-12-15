package guicoderacers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.List;

public class GameScreen {

    private Question selectedQuestion;
    private List<Question> questions;
    private int currentQuestionIndex = 0;
    private Timeline timeline;
    VBox questionDisplayArea = new VBox(10);
    VBox optionsArea = new VBox(10);

    public StackPane createGamePane(Stage primaryStage) {
        StackPane gamePane = new StackPane();
        gamePane.setStyle("-fx-background-color: seashell;");

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
        Button profileButton = new Button();
        profileButton.setGraphic(profileIcon);
        profileButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        profileButton.setOnMouseClicked(e -> ProfileScreen.showProfile(primaryStage));
        topIcons.getChildren().addAll(lightIcon, profileButton);

        HBox topBar = new HBox(775);
        topBar.setAlignment(Pos.TOP_LEFT);
        topBar.getChildren().addAll(backButton, topIcons);

        questions = RandomGameGenerator.generateRandomQuestions();
        selectedQuestion = questions.get(currentQuestionIndex);

        questionDisplayArea = createQuestionDisplay();
        questionDisplayArea.setStyle("-fx-background-color: #700000; -fx-border-color: black;");
        questionDisplayArea.setPadding(new Insets(10));
        questionDisplayArea.setPrefHeight(325);
        questionDisplayArea.setMouseTransparent(true);

        BorderPane questionOverlay = new BorderPane();
        questionOverlay.setTop(questionDisplayArea);
        BorderPane.setMargin(questionDisplayArea, new Insets(0, 0, 0, 632));

        optionsArea = createOptionsScene();
        optionsArea.setStyle("-fx-background-color: white; -fx-border-color: black;");
        optionsArea.setPadding(new Insets(5));

        BorderPane overlayLayout = new BorderPane();
        overlayLayout.setRight(optionsArea);
        BorderPane.setMargin(optionsArea, new Insets(334, 3, 0, -120));

        VBox mainBox = new VBox(30);
        mainBox.setAlignment(Pos.TOP_CENTER);
        mainBox.getChildren().addAll(topBar, questionOverlay, overlayLayout);

        ImageView logoView = CodeRacersGUI.createImageView(CodeRacersGUI.logoIconImage, 375, -1);
        StackPane.setAlignment(roadView, Pos.BOTTOM_CENTER);
        StackPane.setAlignment(logoView, Pos.TOP_CENTER);
        StackPane.setAlignment(mainBox, Pos.CENTER);

        gamePane.getChildren().addAll(roadView, logoView, mainBox);

        // Set up periodic question updates
        timeline = new Timeline(new KeyFrame(Duration.seconds(15), e -> updateQuestionInstance()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        return gamePane;
    }

    private VBox createQuestionDisplay() {
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.TOP_LEFT);
        layout.setPadding(new Insets(20));

        String questionText = selectedQuestion.getQuestionText();
        String associatedContent = selectedQuestion.getAssociatedContent();

        Label questionLabel = new Label(questionText);
        questionLabel.setStyle("-fx-font-size: 18px; -fx-font-family: 'Arial Black';");

        Label contentLabel = new Label(associatedContent);
        contentLabel.setStyle("-fx-font-size: 14px; -fx-font-family: 'Arial';");

        layout.getChildren().addAll(questionLabel, contentLabel);
        return layout;
    }

    private VBox createOptionsScene() {
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.TOP_LEFT);
        layout.setPadding(new Insets(20));

        List<String> options = ((MultipleChoiceQuestion)selectedQuestion).getOptions();
        for (String option : options) {
            Button optionButton = new Button(option);
            optionButton.setStyle("-fx-font-size: 14px; -fx-font-family: 'Arial';");
            optionButton.setOnAction(e -> handleAnswer(option.equals(selectedQuestion.getCorrectAnswer()), optionButton));
            layout.getChildren().add(optionButton);
        }

        return layout;
    }

    private void updateQuestionInstance() {
        currentQuestionIndex = (currentQuestionIndex + 1) % questions.size();
        selectedQuestion = questions.get(currentQuestionIndex);
        questionDisplayArea.getChildren().setAll(createQuestionDisplay().getChildren());
        optionsArea.getChildren().setAll(createOptionsScene().getChildren());
        timeline.playFromStart();
    }

    private void handleAnswer(boolean isCorrect, Button button) {
        button.setStyle(isCorrect ? "-fx-background-color: green; -fx-text-fill: white;" : "-fx-background-color: red; -fx-text-fill: white;");
        if (isCorrect) updateQuestionInstance();
    }
}