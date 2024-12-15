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

    private Question selectedQuestion; // Shared question instance
    private VBox questionDisplayArea;
    private VBox optionsArea;
    private List<Question> questions;
    private int currentQuestionIndex = 0; // Index to track the current question
    private Timeline timeline;

    @Override
    public void start(Stage primaryStage) {
        // Load the background image
        Image backgroundImage;
        try {
            backgroundImage = new Image(new FileInputStream("image (1).png")); // Replace with the correct path
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

        // Initialize question list from RandomQuestionGenerator
        questions = RandomGameGenerator.generateRandomQuestions();
        selectedQuestion = questions.get(currentQuestionIndex);

        // Create Question Display Scene
        questionDisplayArea = createQuestionDisplay();
        questionDisplayArea.setStyle("-fx-background-color: #8B0000; -fx-border-color: black;"); // Dark red background
        questionDisplayArea.setPadding(new Insets(5));
        questionDisplayArea.setPrefHeight(325); // Adjust the height
        questionDisplayArea.setMouseTransparent(true); // Allow rendering while clicks pass through

        BorderPane questionOverlay = new BorderPane();
        questionOverlay.setTop(questionDisplayArea);
        BorderPane.setMargin(questionDisplayArea, new Insets(0, 0, 0, 632)); // Adjust placement for top half

        root.getChildren().add(questionOverlay);

        // Create Options Scene
        optionsArea = createOptionsScene();
        optionsArea.setStyle("-fx-background-color: white; -fx-border-color: black;");
        optionsArea.setPadding(new Insets(5));

        BorderPane overlayLayout = new BorderPane();
        overlayLayout.setRight(optionsArea);
        BorderPane.setMargin(optionsArea, new Insets(334, 3, 0, -120)); // Adjust for placement

        root.getChildren().add(overlayLayout);

        // Set up periodic question updates
        timeline = new Timeline(new KeyFrame(Duration.seconds(15), event -> {
            updateQuestionInstance();
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        // Scene Setup
        Scene scene = new Scene(root, 1200, 800);
        primaryStage.setTitle("GamePlayFx - Unified Scenes");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox createQuestionDisplay() {
        // Create layout
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.TOP_LEFT);
        layout.setPadding(new Insets(20));

        // Display question text and associated content
        String questionText = selectedQuestion.getQuestionText();
        String associatedContent = "";

        if (selectedQuestion instanceof CodeDebuggingQuestion) {
            associatedContent = ((CodeDebuggingQuestion) selectedQuestion).getCodeSnippet();
        } else if (selectedQuestion instanceof AlgorithmTracingQuestion) {
            associatedContent = ((AlgorithmTracingQuestion) selectedQuestion).getAlgorithmCode();
        } else if (selectedQuestion instanceof DragAndDrop) {
            associatedContent = "Match the draggable items to their correct targets.";
        } else if (selectedQuestion instanceof MultipleChoiceQuestion) {
            associatedContent = String.join("\n", ((MultipleChoiceQuestion) selectedQuestion).getOptions());
        }

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
        if (selectedQuestion instanceof DragAndDrop dragAndDropQuestion) {
            return createDragAndDropScene(dragAndDropQuestion);
        }

        VBox optionsBox = new VBox(15);
        optionsBox.setAlignment(Pos.CENTER);
        optionsBox.setPadding(new Insets(10));

        if (selectedQuestion instanceof MultipleChoiceQuestion multipleChoiceQuestion) {
            for (int i = 0; i < multipleChoiceQuestion.getOptions().size(); i++) {
                String optionText = multipleChoiceQuestion.getOptions().get(i);
                Button optionButton = new Button(optionText);
                optionButton.setPrefWidth(400);
                optionButton.setStyle("-fx-font-size: 16px; -fx-padding: 10px; -fx-background-color: darkred; -fx-text-fill: white;");

                int currentIndex = i; // Capture the index
                optionButton.setOnAction(event -> {
                    boolean isCorrect = currentIndex == multipleChoiceQuestion.getCorrectAnswerIndex();
                    handleAnswer(isCorrect, optionButton);
                });

                optionsBox.getChildren().add(optionButton);
            }
        } else if (selectedQuestion instanceof CodeDebuggingQuestion debuggingQuestion) {
            for (String option : debuggingQuestion.getOptions()) {
                Button optionButton = new Button(option);
                optionButton.setPrefWidth(400);
                optionButton.setStyle("-fx-font-size: 16px; -fx-padding: 10px; -fx-background-color: darkred; -fx-text-fill: white;");

                optionButton.setOnAction(event -> {
                    boolean isCorrect = debuggingQuestion.checkAnswer(option);
                    handleAnswer(isCorrect, optionButton);
                });

                optionsBox.getChildren().add(optionButton);
            }
        } else if (selectedQuestion instanceof AlgorithmTracingQuestion tracingQuestion) {
            for (String option : tracingQuestion.getOptions()) {
                Button optionButton = new Button(option);
                optionButton.setPrefWidth(400);
                optionButton.setStyle("-fx-font-size: 16px; -fx-padding: 10px; -fx-background-color: darkred; -fx-text-fill: white;");

                optionButton.setOnAction(event -> {
                    boolean isCorrect = tracingQuestion.checkAnswer(option);
                    handleAnswer(isCorrect, optionButton);
                });

                optionsBox.getChildren().add(optionButton);
            }
        }

        VBox layout = new VBox(optionsBox);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #e6e6e6; -fx-border-color: black; -fx-border-width: 2px;");
        layout.setPadding(new Insets(20));
        layout.setPrefSize(565, 300);
        return layout;
    }

    private VBox createDragAndDropScene(DragAndDrop question) {
        VBox dragAndDropArea = new VBox(10);
        dragAndDropArea.setAlignment(Pos.CENTER);

        // Create pairs of targets and correct answers
        List<String> targets = question.getDroppableTargets();
        List<String> correctAnswers = question.getCorrectAnswer();

        // Shuffle the targets and maintain the correct-answer relationship
        List<Pair<String, String>> targetAnswerPairs = new ArrayList<>();
        for (int i = 0; i < targets.size(); i++) {
            targetAnswerPairs.add(new Pair<>(targets.get(i), correctAnswers.get(i)));
        }
        Collections.shuffle(targetAnswerPairs);

        // Extract shuffled targets and correct answers
        List<String> shuffledTargets = new ArrayList<>();
        List<String> shuffledCorrectAnswers = new ArrayList<>();
        for (Pair<String, String> pair : targetAnswerPairs) {
            shuffledTargets.add(pair.getKey());
            shuffledCorrectAnswers.add(pair.getValue());
        }

        // Update the question's correctAnswer to match the shuffled version
        question.setCorrectAnswer(shuffledCorrectAnswers);

        // Droppable Targets
        VBox targetArea = new VBox(10);
        targetArea.setAlignment(Pos.CENTER);

        for (String target : shuffledTargets) {
            Label targetLabel = new Label("Drop here: " + target);
            targetLabel.setPrefSize(240, 40);
            targetLabel.setAlignment(Pos.CENTER);
            targetLabel.setStyle("-fx-background-color: lightblue; -fx-border-color: black; -fx-font-size: 14;");

            targetLabel.setOnDragOver(event -> {
                if (event.getGestureSource() != targetLabel && event.getDragboard().hasString()) {
                    event.acceptTransferModes(TransferMode.MOVE);
                }
                event.consume();
            });

            targetLabel.setOnDragDropped(event -> {
                Dragboard dragboard = event.getDragboard();
                if (dragboard.hasString()) {
                    targetLabel.setText(dragboard.getString());
                    event.setDropCompleted(true);
                } else {
                    event.setDropCompleted(false);
                }
                event.consume();
            });

            targetArea.getChildren().add(targetLabel);
        }

        // Draggable Items
        VBox draggableArea = new VBox(10);
        draggableArea.setAlignment(Pos.CENTER);

        for (String item : question.getDraggableItems()) {
            Label draggableLabel = new Label(item);
            draggableLabel.setPrefSize(240, 40);
            draggableLabel.setAlignment(Pos.CENTER);
            draggableLabel.setStyle("-fx-background-color: darkred; -fx-text-fill: white; -fx-border-color: black; -fx-font-size: 14;");

            draggableLabel.setOnDragDetected(event -> {
                Dragboard dragboard = draggableLabel.startDragAndDrop(TransferMode.MOVE);
                ClipboardContent content = new ClipboardContent();
                content.putString(draggableLabel.getText());
                dragboard.setContent(content);
                event.consume();
            });

            draggableArea.getChildren().add(draggableLabel);
        }

        HBox dragDropLayout = new HBox(50, targetArea, draggableArea);
        dragDropLayout.setAlignment(Pos.CENTER);

        dragAndDropArea.getChildren().add(dragDropLayout);

        // Add a "Submit" button to check the answers
        Button submitButton = new Button("Submit");
        submitButton.setStyle("-fx-background-color: darkgreen; -fx-text-fill: white; -fx-font-size: 16px;");
        submitButton.setOnAction(event -> {
            boolean allCorrect = true;

            // Check all targets against the correct answers
            for (int i = 0; i < targetArea.getChildren().size(); i++) {
                Label targetLabel = (Label) targetArea.getChildren().get(i);
                String droppedText = targetLabel.getText().replace("Drop here: ", "").trim();
                String correctAnswer = shuffledCorrectAnswers.get(i).trim();

                if (!correctAnswer.equalsIgnoreCase(droppedText)) {
                    allCorrect = false;
                    targetLabel.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-border-color: black; -fx-font-size: 14;");
                } else {
                    targetLabel.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-border-color: black; -fx-font-size: 14;");
                }
            }

            if (allCorrect) {
                updateQuestionInstance();
            }
        });

        dragAndDropArea.getChildren().add(submitButton);

        return dragAndDropArea;
    }

    private void updateScenes() {
        // Update the question display scene
        questionDisplayArea.getChildren().clear();
        VBox newQuestionDisplay = createQuestionDisplay();
        questionDisplayArea.getChildren().addAll(newQuestionDisplay.getChildren());

        // Update the options scene
        optionsArea.getChildren().clear();
        VBox newOptionsScene = createOptionsScene();
        optionsArea.getChildren().addAll(newOptionsScene.getChildren());
    }

    private void updateQuestionInstance() {
        currentQuestionIndex = (currentQuestionIndex + 1) % questions.size();
        selectedQuestion = questions.get(currentQuestionIndex);
        updateScenes();
        resetTimer();
    }

    private void resetTimer() {
        timeline.stop();
        timeline.playFromStart();
    }

    private void handleAnswer(boolean isCorrect, Button clickedButton) {
        // Highlight the button
        clickedButton.setStyle(isCorrect
                ? "-fx-background-color: green; -fx-text-fill: white; -fx-font-size: 16px;"
                : "-fx-background-color: red; -fx-text-fill: white; -fx-font-size: 16px;");

        if (isCorrect) {
            updateQuestionInstance();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
