package guicoderacers;

import javafx.geometry.Insets;
import javafx.scene.image.ImageView;

public class Car {
    private String playerId;
    private ImageView car;
    private int count = 0;
    private int YPosition=540;
    private int XPosition=-932;
    private String selectedColor = "Red";

    public Car(String playerId, int count, String StringColor) {
        this.playerId = playerId;
        this.count = count;
        if (StringColor.equalsIgnoreCase("Red")) {
            car = CodeRacersGUI.createImageView(CodeRacersGUI.redCarIconImage, 60, 42);
        } else if (StringColor.equalsIgnoreCase("DarkBlue")) {
            car = CodeRacersGUI.createImageView(CodeRacersGUI.darkBlueCarIconImage, 60, 42);
        } else if (StringColor.equalsIgnoreCase("Green")) {
            car = CodeRacersGUI.createImageView(CodeRacersGUI.greenCarIconImage, 60, 42);
        } else if (StringColor.equalsIgnoreCase("Yellow")) {
            car = CodeRacersGUI.createImageView(CodeRacersGUI.yellowCarIconImage, 60, 42);
        } else if (StringColor.equalsIgnoreCase("Pink")) {
            car = CodeRacersGUI.createImageView(CodeRacersGUI.pinkCarIconImage, 60, 42);
        } else if (StringColor.equalsIgnoreCase("Orange")) {
            car = CodeRacersGUI.createImageView(CodeRacersGUI.orangeCarIconImage, 60, 42);
        } else if (StringColor.equalsIgnoreCase("Cyan")) {
            car = CodeRacersGUI.createImageView(CodeRacersGUI.cyanCarIconImage, 60, 42);
        }
        car.setRotate(90);
        count++;
    }
    

    public  void setCar(String StringColor) {
        if (StringColor.equalsIgnoreCase("Red")) {
            car = CodeRacersGUI.createImageView(CodeRacersGUI.redCarIconImage, 60, 42);
        } else if (StringColor.equalsIgnoreCase("DarkBlue")) {
            car = CodeRacersGUI.createImageView(CodeRacersGUI.darkBlueCarIconImage, 60, 42);
        } else if (StringColor.equalsIgnoreCase("Green")) {
            car = CodeRacersGUI.createImageView(CodeRacersGUI.greenCarIconImage, 60, 42);
        } else if (StringColor.equalsIgnoreCase("Yellow")) {
            car = CodeRacersGUI.createImageView(CodeRacersGUI.yellowCarIconImage, 60, 42);
        } else if (StringColor.equalsIgnoreCase("Pink")) {
            car = CodeRacersGUI.createImageView(CodeRacersGUI.pinkCarIconImage, 60, 42);
        } else if (StringColor.equalsIgnoreCase("Orange")) {
            car = CodeRacersGUI.createImageView(CodeRacersGUI.orangeCarIconImage, 60, 42);
        } else if (StringColor.equalsIgnoreCase("Cyan")) {
            car = CodeRacersGUI.createImageView(CodeRacersGUI.cyanCarIconImage, 60, 42);
        }
        car.setRotate(90);
    }

    public ImageView getCar() {
        return car;
    }


    public void moveUp() {
        double currentY = car.getLayoutY();
        System.out.println("Current Y Position: " + currentY); // Debug current position
        car.setLayoutY(currentY - 10); // Move the car up
        System.out.println("New Y Position: " + car.getLayoutY()); // Verify new position
    }

    public Insets getMargin(int count) {
        return new Insets(YPosition, 0, 0, XPosition); // Adjust the margin as needed
    }
    public Insets setMargin( ){
        YPosition-=75;
        return new Insets(YPosition,0,0,XPosition);
    }
}