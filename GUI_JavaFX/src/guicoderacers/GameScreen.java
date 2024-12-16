package guicoderacers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonObject;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.Pair;
import questions.*;


public class GameScreen {

    private Question selectedQuestion;
    private List<Question> questions;
    private int currentQuestionIndex = 0;
    private Timeline timeline;
    VBox questionDisplayArea = new VBox(10);
    VBox optionsArea = new VBox(10);
    private Map<String, Car> cars = new HashMap<>();
    private MediaPlayer gameSoundPlayer;
    private Map<String, Node> carNodes = new HashMap<>();
    public StackPane createGamePane(Stage primaryStage) {

        StackPane gamePane = new StackPane();
        gamePane.setStyle("-fx-background-color: seashell;");


        questions = RandomGameGenerator.generateRandomQuestions();
        selectedQuestion = questions.get(currentQuestionIndex);

        ImageView roadView = CodeRacersGUI.createImageView(CodeRacersGUI.autoBahnIconImage,CodeRacersGUI.defaultHeight , 500);
        roadView.rotateProperty().set(90);

        questionDisplayArea = createQuestionDisplay();
        questionDisplayArea.setStyle("-fx-background-color: #700000; -fx-border-color: black;-fx-background-radius: 30;-fx-border-radius: 30;"); // Dark red background
        questionDisplayArea.setMaxWidth(500); 
        questionDisplayArea.setMinWidth(500); 
        questionDisplayArea.setMaxHeight(300);
        questionDisplayArea.setMinHeight(300);       
        questionDisplayArea.setMouseTransparent(true); 

        optionsArea = createOptionsScene();
        optionsArea.setStyle("-fx-background-color: white; -fx-border-color: black;-fx-background-radius: 30;-fx-border-radius: 30;");
        optionsArea.setMaxWidth(500); 
        optionsArea.setMinWidth(500); 
        optionsArea.setMaxHeight(400);
        optionsArea.setMinHeight(400);

        timeline = new Timeline(new KeyFrame(Duration.seconds(15), event -> {
            updateQuestionInstance();
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        VBox rightSideLayout = new VBox(20); // 20 is the spacing between question and options
        rightSideLayout.setAlignment(Pos.CENTER_RIGHT); // Align to the right
        rightSideLayout.getChildren().addAll(questionDisplayArea, optionsArea); // Add question and options vertically

        
        StackPane.setAlignment(rightSideLayout, Pos.CENTER_RIGHT); // Align the VBox to the right
        

        // Add different colored cars under each road"

        HBox carBox = new HBox(48); // 10 is the spacing between cars
        carBox.setAlignment(Pos.BOTTOM_LEFT); // Align to the bottom left

        ImageView redCar = CodeRacersGUI.createImageView(CodeRacersGUI.redCarIconImage, 60, 42);
        redCar.setRotate(90); // Rotate the car to face the road
        ImageView blueCar = CodeRacersGUI.createImageView(CodeRacersGUI.darkBlueCarIconImage, 60, 42);
        blueCar.setRotate(90); // Rotate the car to face the road
        ImageView greenCar = CodeRacersGUI.createImageView(CodeRacersGUI.greenCarIconImage, 60, 42);
        greenCar.setRotate(90); // Rotate the car to face the road
        ImageView yellowCar = CodeRacersGUI.createImageView(CodeRacersGUI.yellowCarIconImage, 60, 42);
        yellowCar.setRotate(90); // Rotate the car to face the road
        ImageView pinkCar = CodeRacersGUI.createImageView(CodeRacersGUI.pinkCarIconImage, 60, 42);
        pinkCar.setRotate(90); // Rotate the car to face the road


        // Set margin to the left side of the window
        HBox.setMargin(redCar, new Insets(0, 0, 0, 132)); 
        carBox.getChildren().addAll(redCar, blueCar, greenCar, yellowCar, pinkCar);

        VBox carAndroadBox = new VBox(124);
        carAndroadBox.getChildren().addAll(roadView, carBox);

        HBox gridPlaces = new HBox(50);
        gridPlaces.setAlignment(Pos.BOTTOM_LEFT);

        for (int i = 1; i <= 5; i++) {
            Label positionLabel = new Label(String.valueOf(i));
            positionLabel.setPrefSize(60, 42);
            positionLabel.setAlignment(Pos.CENTER);
            positionLabel.setStyle("-fx-background-color: darkgray; -fx-text-fill: white; -fx-font-size: 20px; -fx-font-weight: bold; -fx-background-radius: 10; -fx-border-radius: 10;");
            gridPlaces.getChildren().add(positionLabel);
        }

        HBox.setMargin(gridPlaces, new Insets(0, 0, 0, 132));

        

        StackPane.setAlignment(carAndroadBox, Pos.TOP_LEFT); // Align the HBox to the bottom left
        StackPane.setMargin(carAndroadBox, new Insets(0, 0, 0, -125)); // Add some margin
        StackPane.setAlignment(gridPlaces, Pos.BOTTOM_LEFT); // Align the HBox to the bottom left

        gamePane.getChildren().addAll(carAndroadBox, gridPlaces,rightSideLayout); // Add the road and the VBox to the StackPane

        return gamePane;
    }



    private VBox createQuestionDisplay() {

        VBox layout = new VBox(20);
        layout.setAlignment(Pos.TOP_LEFT);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-radius: 30; -fx-border-radius: 30;");

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
        contentLabel.setStyle("-fx-font-family: monospace; -fx-font-size: 14px; -fx-padding: 10px; -fx-background-color: #f4f4f4; -fx-border-color: black; -fx-background-radius: 30; -fx-border-radius: 30;");
        contentLabel.setPrefWidth(1000);

        layout.getChildren().addAll(questionLabel, contentLabel);
        return layout;
    }

    public void handleServerUpdate(JsonObject update) {
        String status = update.get("status").getAsString();
        String message = update.get("message").getAsString();

        if ("success".equals(status)) {
            System.out.println("Update received: " + message);
            updateQuestionInstance(); // Refresh the question and options
        } else {
            System.out.println("Error or irrelevant update: " + message);
        }
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
                optionButton.setStyle("-fx-font-size: 16px; -fx-padding: 10px; -fx-background-color: darkred; -fx-text-fill: white; -fx-background-radius: 30; -fx-border-radius: 30;");

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
                optionButton.setStyle("-fx-font-size: 16px; -fx-padding: 10px; -fx-background-color: darkred; -fx-text-fill: white; -fx-background-radius: 30; -fx-border-radius: 30;");

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
                optionButton.setStyle("-fx-font-size: 16px; -fx-padding: 10px; -fx-background-color: darkred; -fx-text-fill: white; -fx-background-radius: 30; -fx-border-radius: 30;");

                optionButton.setOnAction(event -> {
                    boolean isCorrect = tracingQuestion.checkAnswer(option);
                    handleAnswer(isCorrect, optionButton);
                });

                optionsBox.getChildren().add(optionButton);
            }
        }

        VBox layout = new VBox(optionsBox);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #e6e6e6; -fx-border-color: black; -fx-border-width: 2px; -fx-background-radius: 30; -fx-border-radius: 30;");
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
            targetLabel.setStyle("-fx-background-color: lightblue; -fx-border-color: black; -fx-font-size: 14; -fx-background-radius: 30; -fx-border-radius: 30;");

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
            draggableLabel.setStyle("-fx-background-color: darkred; -fx-text-fill: white; -fx-border-color: black; -fx-font-size: 14; -fx-background-radius: 30; -fx-border-radius: 30;");

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
        submitButton.setStyle("-fx-background-color: darkgreen; -fx-text-fill: white; -fx-font-size: 16px; -fx-background-radius: 30; -fx-border-radius: 30;");
        submitButton.setOnAction(event -> {
            boolean allCorrect = true;

            // Check all targets against the correct answers
            for (int i = 0; i < targetArea.getChildren().size(); i++) {
                Label targetLabel = (Label) targetArea.getChildren().get(i);
                String droppedText = targetLabel.getText().replace("Drop here: ", "").trim();
                String correctAnswer = shuffledCorrectAnswers.get(i).trim();

                if (!correctAnswer.equalsIgnoreCase(droppedText)) {
                    allCorrect = false;
                    targetLabel.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-border-color: black; -fx-font-size: 14; -fx-background-radius: 30; -fx-border-radius: 30;");
                } else {
                    targetLabel.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-border-color: black; -fx-font-size: 14; -fx-background-radius: 30; -fx-border-radius: 30;");
                }
            }

            if (allCorrect) {
                updateQuestionInstance();
            }
        });

        dragAndDropArea.getChildren().add(submitButton);

        return dragAndDropArea;
    }
    protected  void playGameSound() {
        String soundFile = "/Musics/GameEngineSound.mp3"; // Ensure the correct path
        Media sound = new Media(getClass().getResource(soundFile).toExternalForm());
        gameSoundPlayer = new MediaPlayer(sound);
        gameSoundPlayer.play();
    }

    private void handleAnswer(boolean isCorrect, Button clickedButton) {
        // Highlight the button
        clickedButton.setStyle(isCorrect
                ? "-fx-background-color: green; -fx-text-fill: white; -fx-font-size: 16px; -fx-background-radius: 30; -fx-border-radius: 30;"
                : "-fx-background-color: red; -fx-text-fill: white; -fx-font-size: 16px; -fx-background-radius: 30; -fx-border-radius: 30;");

        if (isCorrect) {
            updateQuestionInstance();
        }
    }
    public void setGameSoundVolume(double volume) {
        if (gameSoundPlayer != null) {
            gameSoundPlayer.setVolume(volume);
        }
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
    public void updateCarPosition(String playerId, double x, double y) {
        if (!cars.containsKey(playerId)) {
            // Add a new car if it doesn't exist
            cars.put(playerId, new Car(playerId, x, y, 0));
        }

        // Update the car's position
        Car car = cars.get(playerId);
        car.setX(x);
        car.setY(y);

        // Refresh the car's graphical position
        Platform.runLater(() -> {
            // Assuming `carNodes` is a map of playerId to JavaFX Node representing the car
            if (carNodes.containsKey(playerId)) {
                Node carNode = carNodes.get(playerId);
                carNode.setLayoutX(x);
                carNode.setLayoutY(y);
            }
        });
    }
    public void addCar(String playerId, double startX, double startY, String carPath) {
        ImageView carImage = new ImageView(carPath);
        carImage.setFitWidth(50);
        carImage.setFitHeight(30);
        carImage.setLayoutX(startX);
        carImage.setLayoutY(startY);
    
        carNodes.put(playerId, carImage);
    }
    
}
