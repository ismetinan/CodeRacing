package guicoderacers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.Pair;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GamePlayFx extends Application {

    private List<Question> questions;
    private Question selectedQuestion;
    private VBox questionDisplayArea;
    private VBox optionsArea;
    private int currentQuestionIndex = 0;
    private Timeline timeline;

    @Override
    public void start(Stage primaryStage) {
        // Load the background image
        Image backgroundImage;
        try {
            backgroundImage = new Image(new FileInputStream("image.png")); // Replace with your image path
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }

        // Background ImageView
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setFitWidth(1200);
        backgroundImageView.setFitHeight(800);
        backgroundImageView.setPreserveRatio(false);

        // Root Layout
        StackPane root = new StackPane();
        root.getChildren().add(backgroundImageView);

        // Initialize question list
        questions = RandomGameGenerator.generateRandomQuestions();
        selectedQuestion = questions.get(currentQuestionIndex);

        // Create question and options display areas
        questionDisplayArea = createQuestionDisplay();
        optionsArea = createOptionsScene();

        BorderPane mainLayout = new BorderPane();
        mainLayout.setTop(questionDisplayArea);
        mainLayout.setRight(optionsArea);

        root.getChildren().add(mainLayout);

        // Set up periodic question updates
        timeline = new Timeline(new KeyFrame(Duration.seconds(15), e -> updateQuestionInstance()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        // Scene setup
        Scene scene = new Scene(root, 1200, 800);
        primaryStage.setTitle("GamePlayFx - Unified");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox createQuestionDisplay() {
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.TOP_LEFT);
        layout.setPadding(new Insets(20));

        String questionText = selectedQuestion.getQuestionText();
        String associatedContent = selectedQuestion.getAssociatedContent(); // Generalized accessor

        Label questionLabel = new Label(questionText);
        questionLabel.setWrapText(true);
        questionLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: white;");
        questionLabel.setPrefWidth(1000);

        Label contentLabel = new Label(associatedContent);
        contentLabel.setWrapText(true);
        contentLabel.setStyle("-fx-font-family: monospace; -fx-font-size: 14px; -fx-padding: 10px; -fx-background-color: #f4f4f4; -fx-border-color: black;");
        contentLabel.setPrefWidth(1000);

        layout.getChildren().addAll(questionLabel, contentLabel);
        return layout;
    }

    private VBox createOptionsScene() {
        VBox optionsBox = new VBox(15);
        optionsBox.setAlignment(Pos.CENTER);
        optionsBox.setPadding(new Insets(10));

        if (selectedQuestion instanceof DragAndDrop dragAndDropQuestion) {
            return createDragAndDropScene(dragAndDropQuestion);
        } else if (selectedQuestion instanceof MultipleChoiceQuestion mcQuestion) {
            for (String option : mcQuestion.getOptions()) {
                Button optionButton = new Button(option);
                optionButton.setPrefWidth(400);
                optionButton.setStyle("-fx-font-size: 16px; -fx-padding: 10px; -fx-background-color: darkred; -fx-text-fill: white;");
                optionButton.setOnAction(e -> handleAnswer(mcQuestion.checkAnswer(option), optionButton));
                optionsBox.getChildren().add(optionButton);
            }
        }

        VBox layout = new VBox(optionsBox);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #e6e6e6; -fx-border-color: black; -fx-border-width: 2px;");
        layout.setPadding(new Insets(20));
        return layout;
    }

    private VBox createDragAndDropScene(DragAndDrop question) {
        VBox dragAndDropArea = new VBox(10);
        dragAndDropArea.setAlignment(Pos.CENTER);

        List<Pair<String, String>> pairs = question.getShuffledPairs();
        VBox targetArea = new VBox(10);
        VBox draggableArea = new VBox(10);

        targetArea.setAlignment(Pos.CENTER);
        draggableArea.setAlignment(Pos.CENTER);

        for (Pair<String, String> pair : pairs) {
            Label targetLabel = createTargetLabel(pair.getKey());
            Label draggableLabel = createDraggableLabel(pair.getValue());

            targetArea.getChildren().add(targetLabel);
            draggableArea.getChildren().add(draggableLabel);
        }

        Button submitButton = new Button("Submit");
        submitButton.setStyle("-fx-background-color: darkgreen; -fx-text-fill: white; -fx-font-size: 16px;");
        submitButton.setOnAction(e -> checkDragAndDropAnswers(targetArea, pairs));

        dragAndDropArea.getChildren().addAll(new HBox(50, targetArea, draggableArea), submitButton);
        return dragAndDropArea;
    }

    private Label createTargetLabel(String text) {
        Label label = new Label("Drop here: " + text);
        label.setPrefSize(240, 40);
        label.setAlignment(Pos.CENTER);
        label.setStyle("-fx-background-color: lightblue; -fx-border-color: black; -fx-font-size: 14;");
        label.setOnDragOver(e -> {
            if (e.getDragboard().hasString()) e.acceptTransferModes(TransferMode.MOVE);
            e.consume();
        });
        label.setOnDragDropped(e -> {
            Dragboard db = e.getDragboard();
            if (db.hasString()) label.setText(db.getString());
            e.setDropCompleted(true);
            e.consume();
        });
        return label;
    }

    private Label createDraggableLabel(String text) {
        Label label = new Label(text);
        label.setPrefSize(240, 40);
        label.setAlignment(Pos.CENTER);
        label.setStyle("-fx-background-color: darkred; -fx-text-fill: white; -fx-border-color: black; -fx-font-size: 14;");
        label.setOnDragDetected(e -> {
            Dragboard db = label.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();
            content.putString(label.getText());
            db.setContent(content);
            e.consume();
        });
        return label;
    }

    private void checkDragAndDropAnswers(VBox targetArea, List<Pair<String, String>> pairs) {
        boolean allCorrect = true;
        for (int i = 0; i < targetArea.getChildren().size(); i++) {
            Label targetLabel = (Label) targetArea.getChildren().get(i);
            String droppedText = targetLabel.getText().replace("Drop here: ", "").trim();
            String correctAnswer = pairs.get(i).getValue().trim();
            if (!droppedText.equalsIgnoreCase(correctAnswer)) {
                targetLabel.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-border-color: black;");
                allCorrect = false;
            } else {
                targetLabel.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-border-color: black;");
            }
        }

        if (allCorrect) updateQuestionInstance();
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

    public static void main(String[] args) {
        launch(args);
    }
}
