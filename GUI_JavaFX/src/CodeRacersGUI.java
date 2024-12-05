import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class CodeRacersGUI extends Application {
    @Override
    public void start(Stage primaryStage) {

        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: seashell;"); 

        Image roadImage = new Image("file:/C:/Users/Ege/Desktop/CodeRacing/GUI_JavaFX/src/Images/Roads.png");
        ImageView roadView = new ImageView(roadImage);
        roadView.setFitWidth(800); 
        roadView.setFitHeight(400); 


        Image logoImage = new Image("file:/C:/Users/Ege/Desktop/CodeRacing/GUI_JavaFX/src/Images/GameLogo.png");
        ImageView logoView = new ImageView(logoImage);
        logoView.setFitWidth(300); 
        logoView.setPreserveRatio(true); 

        VBox centerBox = new VBox(25); // Spacing between elements
        centerBox.setAlignment(Pos.CENTER);

        double rectWidth = 300;
        double rectHeight = 200;
        Rectangle centerBackground = new Rectangle(rectWidth, rectHeight, Color.rgb(112,0,0));
        centerBackground.setArcWidth(20);
        centerBackground.setArcHeight(20);

        Label titleLabel = new Label("Set Your Car!");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-text-fill: white; -fx-font-family: 'Arial Black';");

        TextField usernameField = new TextField();
        usernameField.setPromptText("Enter your username");  
        usernameField.setMaxSize(rectWidth, 20);  
        usernameField.setStyle("-fx-background-color: seashell;");
        usernameField.setStyle("-fx-font-size: 16px;-fx-font-family: 'Arial Black';");

        Button startButton = new Button("Race!");
        startButton.setStyle("-fx-font-size: 16px; -fx-background-color: black; -fx-text-fill: #700000;-fx-font-family: 'Arial Black';");

        centerBox.getChildren().addAll(titleLabel, usernameField, startButton);


        StackPane.setAlignment(logoView, Pos.TOP_CENTER);
        StackPane.setAlignment(centerBox, Pos.CENTER);   
        StackPane.setAlignment(roadView, Pos.BOTTOM_CENTER);
        root.getChildren().addAll(roadView, logoView, centerBackground, centerBox);


        Scene scene = new Scene(root, 800, 600); 
        primaryStage.setTitle("Code Racers");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false); 
        primaryStage.show();
     // --- Second Screen ---
     StackPane secondRoot = new StackPane();
     secondRoot.setStyle("-fx-background-color: white;");

     ImageView secondRoadView = new ImageView(roadImage);
     secondRoadView.setFitWidth(800);
     secondRoadView.setPreserveRatio(true);

     ImageView secondLogoView = new ImageView(logoImage);
     secondLogoView.setFitWidth(300);
     secondLogoView.setPreserveRatio(true);

     // Top Right Icons
     HBox topIcons = new HBox(10);
     topIcons.setAlignment(Pos.TOP_RIGHT);
     ImageView lightIcon = new ImageView(new Image("file:/C:/Users/Ege/Desktop/CodeRacing/GUI_JavaFX/src/Images/LightIcon.png"));
     ImageView profileIcon = new ImageView(new Image("file:/C:/Users/Ege/Desktop/CodeRacing/GUI_JavaFX/src/Images/ProfilIcon.png"));
     lightIcon.setFitWidth(30);
     lightIcon.setFitHeight(30);
     profileIcon.setFitWidth(50);
     profileIcon.setFitHeight(50);
     topIcons.getChildren().addAll(lightIcon, profileIcon);

     // Buttons
     VBox buttonBox = new VBox(15);
     buttonBox.setAlignment(Pos.CENTER);

     Button fullThrottleButton = new Button("Full Throttle");
     Button leaderboardButton = new Button("Leaderboard");
     Button settingsButton = new Button("Settings");

     fullThrottleButton.setStyle("-fx-font-size: 16px; -fx-background-color: #8B0000; -fx-text-fill: white;");
     leaderboardButton.setStyle("-fx-font-size: 16px; -fx-background-color: #8B0000; -fx-text-fill: white;");
     settingsButton.setStyle("-fx-font-size: 16px; -fx-background-color: #8B0000; -fx-text-fill: white;");

     buttonBox.getChildren().addAll(fullThrottleButton, leaderboardButton, settingsButton);

     StackPane.setAlignment(secondLogoView, Pos.TOP_CENTER);
     StackPane.setAlignment(topIcons, Pos.TOP_RIGHT);
     StackPane.setAlignment(buttonBox, Pos.CENTER);

     secondRoot.getChildren().addAll(secondRoadView, secondLogoView, topIcons, buttonBox);

     Scene secondScene = new Scene(secondRoot, 800, 600);

     // Transition
     startButton.setOnAction(e -> primaryStage.setScene(secondScene));

     primaryStage.setTitle("Code Racers");
     primaryStage.setScene(scene);
     primaryStage.show();
        }

        public static void main(String[] args) {
            launch(args);
        }
        }