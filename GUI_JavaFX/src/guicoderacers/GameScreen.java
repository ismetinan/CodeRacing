package guicoderacers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import controller.Database;
import controller.Server;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart.Data;
import javafx.util.Duration;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import questions.*;
import java.lang.reflect.Type;

public class GameScreen {

    private Question selectedQuestion;
    private List<Question> questions;
    private int currentQuestionIndex = 0;
    private Timeline timeline;
    VBox questionDisplayArea = new VBox(10);
    HBox trueFalseArea = new HBox(30);
    VBox optionsArea = new VBox(10);
    private static final Gson gson = new Gson(); // Gson instance
    private static Map<Integer, String> userPasswords;
    private MediaPlayer gameSoundPlayer;
    private Label timerLabel;
    private int timeRemaining=60;
    protected static final int defaultLocy = 923; 
    protected static int carCount = 0;
    protected static Car player1Car=new Car(null, carCount,"Red");
    protected static Car player2Car=new Car(null, carCount, "Cyan");
    protected static Car player3Car=new Car(null, carCount, "Green");
    protected static Car player4Car=new Car(null, carCount, "Yellow");
    protected static Car player5Car=new Car(null, carCount, "Pink");
    private Map<String, Node> carNodes = new HashMap<>();
    protected static int questionNumber=0;
    private static int correctAnswersNumber=0;
    private static int incorrectAnswersNumber=0;
    private StackPane overlayPane;
    private static ArrayList<Integer> incorrectId = new ArrayList<>();
    private static int curPosX = 540;
    private static int counter2 = 1;
    private static int counter3 = 1;
    private static int counter4 = 1;
    private static int counter5 = 1;



    public StackPane createGamePane(Stage primaryStage) {

        StackPane gamePane = new StackPane();
        gamePane.setStyle("-fx-background-color: seashell;");

        questions = RandomGameGenerator.generateRandomQuestions();
        selectedQuestion = questions.get(currentQuestionIndex);

        questionDisplayArea = createQuestionDisplay();
        questionDisplayArea.setStyle("-fx-background-color: #700000; -fx-border-color: black;-fx-background-radius: 30;-fx-border-radius: 30;"); // Dark red background
        questionDisplayArea.setMaxWidth(500); 
        questionDisplayArea.setMinWidth(500); 
        questionDisplayArea.setMaxHeight(300);
        questionDisplayArea.setMinHeight(300);       
        questionDisplayArea.setMouseTransparent(true); 
        optionsArea = createOptionsScene();
        optionsArea.setStyle("-fx-background-color: seashell; -fx-border-color: black;-fx-background-radius: 30;-fx-border-radius: 30;");
        optionsArea.setMaxWidth(500); 
        optionsArea.setMinWidth(500); 
        optionsArea.setMaxHeight(300);
        optionsArea.setMinHeight(300);
        trueFalseArea = createTrueandFalseDisplay(correctAnswersNumber, incorrectAnswersNumber);
        trueFalseArea.setStyle("-fx-background-color:seashell; -fx-border-color: black;-fx-background-radius: 30;-fx-border-radius: 30;");
        trueFalseArea.setMaxWidth(500);
        trueFalseArea.setMinWidth(500);
        trueFalseArea.setMaxHeight(40);
        trueFalseArea.setMinHeight(40);


        timerLabel = new Label("Time remaining: " + timeRemaining + "\t\t\t\t");
        timerLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: black; -fx-font-family: 'Arial Black';");

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            timeRemaining--;
            timerLabel.setText("Time remaining: " + timeRemaining + "\t\t\t\t");
            if (timeRemaining <= 0) {
            updateQuestionInstance();
            timeRemaining = 60; // Reset the timer for the next question
            resetTimer(); // Restart the timer for the next question
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        VBox rightSideLayout = new VBox(10); 
        rightSideLayout.setAlignment(Pos.CENTER_RIGHT); 
        rightSideLayout.getChildren().addAll(timerLabel,questionDisplayArea, optionsArea, trueFalseArea); 

        StackPane.setAlignment(rightSideLayout, Pos.CENTER_RIGHT); 
        overlayPane = new StackPane();
        overlayPane.setStyle("-fx-background-color: rgba(0, 0, 0, 0);");
        overlayPane.setVisible(false);

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




        ImageView roadView = CodeRacersGUI.createImageView(CodeRacersGUI.autoBahnIconImage,CodeRacersGUI.defaultHeight , 500);
        roadView.rotateProperty().set(90);    

        
        
        StackPane.setMargin(player1Car.getCar(), player1Car.getMargin(carCount)); 



        
        StackPane.setMargin(player2Car.getCar(), new Insets(540,708, 0, 0)); // Adjust the margin as needed
        StackPane.setMargin(player3Car.getCar(), new Insets(curPosX,493 , 0, 0)); // Adjust the margin as needed
        StackPane.setMargin(player4Car.getCar(), new Insets(curPosX, 278, 0, 0)); // Adjust the margin as needed
        StackPane.setMargin(player5Car.getCar(), new Insets(curPosX, 63, 0, 0)); // Adjust the margin as needed      

        

        StackPane.setMargin(roadView, new Insets(0, 0, 0, -500)); // Add some margin

        gamePane.getChildren().addAll(roadView, gridPlaces,player1Car.getCar(),player2Car.getCar(),player3Car.getCar(),player4Car.getCar(),player5Car.getCar(),rightSideLayout,overlayPane); // Add the road and the VBox to the StackPane
        return gamePane;
        }
       
        private HBox createTrueandFalseDisplay(int correctAnswers, int incorrectAnswers) {
            HBox trueFalseDisplay = new HBox();
            trueFalseDisplay.setAlignment(Pos.CENTER);
            trueFalseDisplay.setSpacing(20);
        
            Label correctLabel = new Label("Correct: " + correctAnswers);
            correctLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: green; -fx-font-family: 'Arial Black';");
        
            Label incorrectLabel = new Label("Incorrect: " + incorrectAnswers);
            incorrectLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: red; -fx-font-family: 'Arial Black';");
        
            trueFalseDisplay.getChildren().addAll(correctLabel, incorrectLabel);
            return trueFalseDisplay;
        }

        public static void connectToServer() {
        try (Socket socket = new Socket("139.179.135.253", 12345);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            // Read JSON data from the server
            String jsonResponse = in.readLine();
            System.out.println("Received JSON: " + jsonResponse);

            // Parse JSON to extract the user-password HashMap
            JsonObject jsonObject = gson.fromJson(jsonResponse, JsonObject.class);
            Type type = new TypeToken<HashMap<Integer, String>>() {}.getType();
            userPasswords = gson.fromJson(jsonObject.get("userPasswords"), type);

            System.out.println(userPasswords.isEmpty());
            for (Map.Entry<Integer, String> entry : userPasswords.entrySet()) {
                System.out.println("User " + entry.getKey() + " -> Password: " + entry.getValue());
            }

        } catch (Exception e) {
            System.out.println("Error connecting to server: " + e.getMessage());
        }
    }


        private VBox createQuestionDisplay() {

        VBox layout = new VBox();
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-radius: 30; -fx-border-radius: 30;");
        layout.setMaxWidth(480);
        layout.setMinWidth(480);
        layout.setMaxHeight(275);
        layout.setMinHeight(275);

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
        questionLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: seashell;-fx-font-family: 'Arial Black';");
        questionLabel.setMaxWidth(475);

        Label contentLabel = new Label(associatedContent);
        contentLabel.setWrapText(true);
        contentLabel.setStyle("-fx-font-family: monospace; -fx-font-size: 14px; -fx-text-fill: seashell; -fx-background-color: #700000;  -fx-background-radius: 30; -fx-border-radius: 30;");
        contentLabel.setMaxWidth(475);
        contentLabel.setMaxHeight(Double.MAX_VALUE);
        VBox.setVgrow(contentLabel, Priority.ALWAYS);

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
                    optionButton.setMaxWidth(480);
                    optionButton.setMinHeight(55);
                    optionButton.setWrapText(true);
                    optionButton.setStyle("-fx-font-size: 12px; -fx-padding: 10px; -fx-background-color: darkred; -fx-text-fill: white; -fx-background-radius: 30; -fx-border-radius: 30;");

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
                    optionButton.setMaxWidth(480);
                    optionButton.setMinHeight(55);
                    optionButton.setWrapText(true);
                    optionButton.setStyle("-fx-font-size: 12px; -fx-padding: 10px; -fx-background-color: darkred; -fx-text-fill: white; -fx-background-radius: 30; -fx-border-radius: 30;");

                    optionButton.setOnAction(event -> {
                    boolean isCorrect = debuggingQuestion.checkAnswer(option);
                    handleAnswer(isCorrect, optionButton);
                    });

                    optionsBox.getChildren().add(optionButton);
                }
                } else if (selectedQuestion instanceof AlgorithmTracingQuestion tracingQuestion) {
                for (String option : tracingQuestion.getOptions()) {
                    Button optionButton = new Button(option);
                    optionButton.setMaxWidth(480);
                    optionButton.setMinHeight(55);
                    optionButton.setWrapText(true);
                    optionButton.setStyle("-fx-font-size: 12px; -fx-padding: 10px; -fx-background-color: darkred; -fx-text-fill: white; -fx-background-radius: 30; -fx-border-radius: 30;");

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
                layout.setMaxSize(565, 300);
                layout.setMinSize(565, 300);
                return layout;
            }

            private VBox createDragAndDropScene(DragAndDrop question) {
                VBox dragAndDropArea = new VBox(6);
                dragAndDropArea.setAlignment(Pos.CENTER);
            
                
                List<String> correctAnswers = question.getCorrectAnswer();
                List<String> draggableItems = new ArrayList<>(question.getCorrectAnswer());
                Collections.shuffle(draggableItems); // Only shuffle draggable items
            
                // Droppable Targets
                VBox targetArea = new VBox(6);
                targetArea.setAlignment(Pos.CENTER);
                targetArea.setPadding(new Insets(10)); // Add padding to prevent overflow
            
                // Create 4 target areas
                for (int i = 0; i < correctAnswers.size(); i++) {
                    Label targetLabel = new Label("Drop here");
                    targetLabel.setMinWidth(230);
                    targetLabel.setMaxWidth(230);
                    targetLabel.setMinHeight(60);
                    targetLabel.setMaxHeight(60);
                    targetLabel.setAlignment(Pos.CENTER);
                    targetLabel.setWrapText(true);
                    targetLabel.setStyle("-fx-background-color: lightblue; -fx-border-color: black; -fx-font-size: 14; -fx-background-radius: 10; -fx-border-radius: 10;");
                    targetLabel.setUserData(correctAnswers.get(i)); // Store correct answer
            
                    targetLabel.setOnDragOver(event -> {
                        if (event.getGestureSource() != targetLabel && event.getDragboard().hasString()) {
                            event.acceptTransferModes(TransferMode.MOVE);
                        }
                        event.consume();
                    });
            
                    targetLabel.setOnDragDropped(event -> {
                        Dragboard dragboard = event.getDragboard();
                        boolean success = false;
                        if (dragboard.hasString()&& targetLabel.getText().equals("Drop here")) {
                            String droppedText = dragboard.getString();
                            targetLabel.setText(droppedText);
                            success = true;
                        }
                        event.setDropCompleted(success);
                        event.consume();
                    });
            
                    targetArea.getChildren().add(targetLabel);
                }
            
                // Draggable Items Area
                VBox draggableArea = new VBox(6);
                draggableArea.setAlignment(Pos.CENTER);
                draggableArea.setPadding(new Insets(10)); // Add padding to prevent overflow
            
                // Create 4 draggable items
                for (String item : draggableItems) {
                    Label draggableLabel = new Label(item);
                    draggableLabel.setMinWidth(230);
                    draggableLabel.setMaxWidth(230);
                    draggableLabel.setMinHeight(60);
                    draggableLabel.setMaxHeight(60);
                    draggableLabel.setAlignment(Pos.CENTER);
                    draggableLabel.setWrapText(true);
                    draggableLabel.setStyle("-fx-background-color: darkred; -fx-text-fill: white; -fx-border-color: black; -fx-font-size: 12; -fx-background-radius: 10; -fx-border-radius: 10;");
            
                    draggableLabel.setOnDragDetected(event -> {
                        Dragboard dragboard = draggableLabel.startDragAndDrop(TransferMode.MOVE);
                        ClipboardContent content = new ClipboardContent();
                        content.putString(draggableLabel.getText());
                        dragboard.setContent(content);
                        event.consume();
                    });
            
                    draggableLabel.setOnDragDone(event -> {
                        if (event.getTransferMode() == TransferMode.MOVE) {
                            draggableLabel.setVisible(false);
                        }
                        event.consume();
                    });
            
                    draggableArea.getChildren().add(draggableLabel);
                }
            
                // Layout with draggable items on left, targets on right
                HBox dragDropLayout = new HBox(17, draggableArea, targetArea);
                dragDropLayout.setAlignment(Pos.CENTER);
                dragAndDropArea.getChildren().add(dragDropLayout);
            
                // Submit button
                Button submitButton = new Button("Submit");
                submitButton.setStyle("-fx-background-color: darkgreen; -fx-text-fill: white; -fx-font-size: 16px; -fx-background-radius: 10; -fx-border-radius: 10;");
                
                submitButton.setOnAction(event -> {
                    List<String> userAnswers = new ArrayList<>();
                    boolean allAnswered = true;
            
                    // Collect all current answers
                    for (Node node : targetArea.getChildren()) {
                        Label targetLabel = (Label) node;
                        String answer = targetLabel.getText();
                        
                        if (answer.equals("Drop here")) {
                            allAnswered = false;
                            targetLabel.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-border-color: black; -fx-font-size: 12; -fx-background-radius: 10; -fx-border-radius: 10;");
                        } else {
                            userAnswers.add(answer);
                        }
                    }
            
                    if (allAnswered) {
                        // Set user's answers in the question object
                        question.setUserAnswer(userAnswers);
                        
                        // Check if the answers are correct
                        boolean isCorrect = question.isAnswerCorrect();
            
                        // Update the UI based on correctness
                        for (Node node : targetArea.getChildren()) {
                            Label targetLabel = (Label) node;
                            if (isCorrect) {
                                targetLabel.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-border-color: black; -fx-font-size: 12; -fx-background-radius: 10; -fx-border-radius: 10;");
                            } else {
                                targetLabel.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-border-color: black; -fx-font-size: 12; -fx-background-radius: 10; -fx-border-radius: 10;");
                            }
                        }
            
                        if (isCorrect) {
                            correctAnswersNumber++;
                            setCorrectAnswers(correctAnswersNumber);
                            updateQuestionInstance();
                           
                        }
                        else {
                            incorrectAnswersNumber++;
                            setIncorrectAnswers(incorrectAnswersNumber);
                            updateQuestionInstance();
                            incorrectId.add(question.getId());
                        }
                    }
                });
            
                dragAndDropArea.getChildren().add(submitButton);
                return dragAndDropArea;
            }

            public void playGameSound() {
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
            correctAnswersNumber++;
            System.out.println("Correct Answers: " + correctAnswersNumber);
            setCorrectAnswers(correctAnswersNumber);
            StackPane.setMargin(player1Car.getCar(), player1Car.setMargin()); 

            Random rand = new Random();
            int randomNum = rand.nextInt(9) + 1;
            switch (randomNum) {
                case 1:                    
                    StackPane.setMargin(player4Car.getCar(), new Insets(540-counter4*125, 278, 0, 0));
                    StackPane.setMargin(player5Car.getCar(), new Insets(540-counter5*125, 63, 0, 0));
                    counter4++;
                    counter5++;
                    break;
                case 2:
                    StackPane.setMargin(player2Car.getCar(), new Insets(540-counter2*125, 708, 0, 0));
                    StackPane.setMargin(player3Car.getCar(), new Insets(540-counter3*125, 493, 0, 0));
                    StackPane.setMargin(player4Car.getCar(), new Insets(540-counter4*125, 278, 0, 0));
                    StackPane.setMargin(player5Car.getCar(), new Insets(540-counter5*125, 638, 0, 0));
                    counter2++;
                    counter3++;
                    counter4++;
                    counter5++;
                    break;
                case 3:
                    StackPane.setMargin(player2Car.getCar(), new Insets(540-counter2*125, 708, 0, 0));
                    StackPane.setMargin(player3Car.getCar(), new Insets(540-counter3*125, 493, 0, 0));
                    StackPane.setMargin(player4Car.getCar(), new Insets(540-counter4*125, 278, 0, 0));
                    counter2++;
                    counter3++;
                    counter4++;
                    break;
                case 4:
                    StackPane.setMargin(player2Car.getCar(), new Insets(540-counter2*125, 708, 0, 0));
                    StackPane.setMargin(player3Car.getCar(), new Insets(540-counter3*125, 493, 0, 0));
                    StackPane.setMargin(player5Car.getCar(), new Insets(540-counter5*125, 63, 0, 0));
                    counter2++;
                    counter3++;
                    counter5++;
                    break;
                case 5:
                    StackPane.setMargin(player2Car.getCar(), new Insets(540-counter2*125, 708, 0, 0));
                    StackPane.setMargin(player4Car.getCar(), new Insets(540-counter4*125, 278, 0, 0));
                    StackPane.setMargin(player5Car.getCar(), new Insets(540-counter5*125, 63, 0, 0));
                    counter2++;
                    counter4++;
                    counter5++;
                    break;
                case 6:
                    StackPane.setMargin(player2Car.getCar(), new Insets(540-counter2*125, 708, 0, 0));
                    StackPane.setMargin(player3Car.getCar(), new Insets(540-counter3*125, 493, 0, 0));
                    counter2++;
                    counter3++;
                    break;
                case 7:
                    StackPane.setMargin(player2Car.getCar(), new Insets(540-counter2*125, 708, 0, 0));
                    StackPane.setMargin(player4Car.getCar(), new Insets(540-counter4*125, 278, 0, 0));
                    counter2++;
                    counter4++;
                    break;
                case 8:
                    StackPane.setMargin(player2Car.getCar(), new Insets(540-counter2*125, 708, 0, 0));
                    StackPane.setMargin(player5Car.getCar(), new Insets(540-counter5*125, 63, 0, 0));
                    counter2++;
                    counter5++;
                    break;
                case 9:
                    StackPane.setMargin(player3Car.getCar(), new Insets(540-counter3*125, 493, 0, 0));
                    StackPane.setMargin(player4Car.getCar(), new Insets(540-counter4*125, 278, 0, 0));
                    counter3++;
                    counter4++;
                    break;
                case 10:
                    StackPane.setMargin(player3Car.getCar(), new Insets(540-counter3*125, 278, 0, 0));
                    StackPane.setMargin(player5Car.getCar(), new Insets(540-counter5*125, 63, 0, 0));
                    counter3++;
                    counter5++;
                    break;
            }


            updateQuestionInstance();
        } else {
            
            incorrectAnswersNumber++;
            System.out.println("Incorrect Answers: " + incorrectAnswersNumber);
            setIncorrectAnswers(incorrectAnswersNumber);

            Random rand = new Random();
            int randomNum = rand.nextInt(9) + 1;
            switch (randomNum) {
                case 1:                    
                    StackPane.setMargin(player4Car.getCar(), new Insets(540-counter4*125, 278, 0, 0));
                    StackPane.setMargin(player5Car.getCar(), new Insets(540-counter5*125, 63, 0, 0));
                    counter4++;
                    counter5++;
                    break;
                case 2:
                    StackPane.setMargin(player2Car.getCar(), new Insets(540-counter2*125, 708, 0, 0));
                    StackPane.setMargin(player3Car.getCar(), new Insets(540-counter3*125, 493, 0, 0));
                    StackPane.setMargin(player4Car.getCar(), new Insets(540-counter4*125, 278, 0, 0));
                    StackPane.setMargin(player5Car.getCar(), new Insets(540-counter5*125, 638, 0, 0));
                    counter2++;
                    counter3++;
                    counter4++;
                    counter5++;
                    break;
                case 3:
                    StackPane.setMargin(player2Car.getCar(), new Insets(540-counter2*125, 708, 0, 0));
                    StackPane.setMargin(player3Car.getCar(), new Insets(540-counter3*125, 493, 0, 0));
                    StackPane.setMargin(player4Car.getCar(), new Insets(540-counter4*125, 278, 0, 0));
                    counter2++;
                    counter3++;
                    counter4++;
                    break;
                case 4:
                    StackPane.setMargin(player2Car.getCar(), new Insets(540-counter2*125, 708, 0, 0));
                    StackPane.setMargin(player3Car.getCar(), new Insets(540-counter3*125, 493, 0, 0));
                    StackPane.setMargin(player5Car.getCar(), new Insets(540-counter5*125, 63, 0, 0));
                    counter2++;
                    counter3++;
                    counter5++;
                    break;
                case 5:
                    StackPane.setMargin(player2Car.getCar(), new Insets(540-counter2*125, 708, 0, 0));
                    StackPane.setMargin(player4Car.getCar(), new Insets(540-counter4*125, 278, 0, 0));
                    StackPane.setMargin(player5Car.getCar(), new Insets(540-counter5*125, 63, 0, 0));
                    counter2++;
                    counter4++;
                    counter5++;
                    break;
                case 6:
                    StackPane.setMargin(player2Car.getCar(), new Insets(540-counter2*125, 708, 0, 0));
                    StackPane.setMargin(player3Car.getCar(), new Insets(540-counter3*125, 493, 0, 0));
                    counter2++;
                    counter3++;
                    break;
                case 7:
                    StackPane.setMargin(player2Car.getCar(), new Insets(540-counter2*125, 708, 0, 0));
                    StackPane.setMargin(player4Car.getCar(), new Insets(540-counter4*125, 278, 0, 0));
                    counter2++;
                    counter4++;
                    break;
                case 8:
                    StackPane.setMargin(player2Car.getCar(), new Insets(540-counter2*125, 708, 0, 0));
                    StackPane.setMargin(player5Car.getCar(), new Insets(540-counter5*125, 63, 0, 0));
                    counter2++;
                    counter5++;
                    break;
                case 9:
                    StackPane.setMargin(player3Car.getCar(), new Insets(540-counter3*125, 493, 0, 0));
                    StackPane.setMargin(player4Car.getCar(), new Insets(540-counter4*125, 278, 0, 0));
                    counter3++;
                    counter4++;
                    break;
                case 10:
                    StackPane.setMargin(player3Car.getCar(), new Insets(540-counter3*125, 278, 0, 0));
                    StackPane.setMargin(player5Car.getCar(), new Insets(540-counter5*125, 63, 0, 0));
                    counter3++;
                    counter5++;
                    break;
            }
        updateQuestionInstance();
        incorrectId.add(selectedQuestion.getId());
        for(int i : incorrectId){
            QuestionGenerator.incorrectQuestionReturner(i);
        }
        }
    }
    public void setGameSoundVolume(double volume) {
        if (gameSoundPlayer != null) {
            gameSoundPlayer.setVolume(volume);
        }
    }


    private void updateQuestionInstance() {
        questionNumber++;
        resetTimer();
        System.out.println(questionNumber);
        if (currentQuestionIndex >= questions.size() - 1) {
            currentQuestionIndex = 0; // Reset index
        }
        if (questionNumber >= 10) {
            endGame(Database.getLatestUsername(), correctAnswersNumber);
            CodeRacersGUI.navigateToEndGameScreen(); // Navigate to the end game screen
            
            return;
        }
        currentQuestionIndex = (currentQuestionIndex + 1) % questions.size();
        selectedQuestion = questions.get(currentQuestionIndex);
    
        // Update the trueFalseArea
        trueFalseArea.getChildren().clear();
        HBox newTrueFalseDisplay = createTrueandFalseDisplay(correctAnswersNumber, incorrectAnswersNumber);
        trueFalseArea.getChildren().addAll(newTrueFalseDisplay.getChildren());
    
        // Show the overlay pane and pause for 4 seconds before updating the scenes
        overlayPane.setVisible(true);
        PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
        pause.setOnFinished(event -> {
            // Update the scenes after the pause
            updateScenes();
            overlayPane.setVisible(false);
        });
        pause.play();
    }
private void resetTimer() {
    timeRemaining = 60;
    timerLabel.setText("Time remaining: " + timeRemaining + "\t\t\t\t");
    timeline.stop();
    timeline.playFromStart();
}

    public static int getCorrectAnswers() {        
        return correctAnswersNumber;
    }
    public static int getIncorrectAnswers() {
        return incorrectAnswersNumber;
    }
    public static void setCorrectAnswers(int correctAnswersNumber) {
        GameScreen.correctAnswersNumber = correctAnswersNumber;
    }
    public  static void setIncorrectAnswers(int incorrectAnswersNumber) {
       GameScreen.incorrectAnswersNumber = incorrectAnswersNumber;
    }
    

    private void updateScenes() {
        questionDisplayArea.getChildren().clear();
        VBox newQuestionDisplay = createQuestionDisplay();
        questionDisplayArea.getChildren().addAll(newQuestionDisplay.getChildren());
    
        optionsArea.getChildren().clear();
        VBox newOptionsScene = createOptionsScene();
        optionsArea.getChildren().addAll(newOptionsScene.getChildren());
    
        // Update the trueFalseArea
        trueFalseArea.getChildren().clear();
        HBox newTrueFalseDisplay = createTrueandFalseDisplay(correctAnswersNumber, incorrectAnswersNumber);
        trueFalseArea.getChildren().addAll(newTrueFalseDisplay.getChildren());
    }

    public static ArrayList<Integer> getIncorrectId() {
        return incorrectId;
    }

    public void endGame(String nickname, int score) {
        
        Database.addScore(nickname, score);
        
        System.out.println("Game ended. Score saved for: " + nickname);
    }
}
