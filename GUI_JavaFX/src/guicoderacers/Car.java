package guicoderacers;

import javafx.scene.image.ImageView;

public class Car {
    private String playerId;
    private ImageView car;
    private double x;
    private double y;
    private double speed;

    public Car(String playerId, double x, double y, double speed) {
        this.playerId = playerId;
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    public void setCar(String StringColor) {
        StringColor = SettingsScreen.getUserCarColor();
        if(StringColor.equalsIgnoreCase("Red")){
            car = CodeRacersGUI.createImageView(CodeRacersGUI.redCarIconImage, 60, 42);
            car.setRotate(90);
        }
        else if((StringColor.equalsIgnoreCase("DarkBlue"))){
            car = CodeRacersGUI.createImageView(CodeRacersGUI.darkBlueCarIconImage, 60, 42);
            car.setRotate(90);
        }
        else if(StringColor.equalsIgnoreCase("Green")){
            car = CodeRacersGUI.createImageView(CodeRacersGUI.greenCarIconImage, 60, 42);
            car.setRotate(90);
        }
        else if(StringColor.equalsIgnoreCase("Yellow")){
            car = CodeRacersGUI.createImageView(CodeRacersGUI.yellowCarIconImage, 60, 42);
            car.setRotate(90);
        }
        else if(StringColor.equalsIgnoreCase("Pink")){
            car = CodeRacersGUI.createImageView(CodeRacersGUI.pinkCarIconImage, 60, 42);
            car.setRotate(90);
        }
        else if(StringColor.equalsIgnoreCase("Orange")){
            car = CodeRacersGUI.createImageView(CodeRacersGUI.orangeCarIconImage, 60, 42);
            car.setRotate(90);
        }
        else if(StringColor.equalsIgnoreCase("Cyan")){
            car = CodeRacersGUI.createImageView(CodeRacersGUI.cyanCarIconImage, 60, 42);
            car.setRotate(90);
        }
    }

    public ImageView getCar(){
        return car;
    }

    // Getters and setters
    public String getPlayerId() {
        return playerId;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
}
