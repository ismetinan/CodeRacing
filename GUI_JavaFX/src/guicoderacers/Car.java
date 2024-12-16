package guicoderacers;

import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class Car {
    private String playerId;
    private ImageView car;
   
    private int count;

    public Car(String playerId,  int count) {
        this.playerId = playerId;

        this.count = count;
        setCar(SettingsScreen.getUserCarColor());
    }

    public void setCar(String StringColor) {
        if(StringColor.equalsIgnoreCase("Red")){
            car = CodeRacersGUI.createImageView(CodeRacersGUI.redCarIconImage, 60, 42);
        }
        else if((StringColor.equalsIgnoreCase("DarkBlue"))){
            car = CodeRacersGUI.createImageView(CodeRacersGUI.darkBlueCarIconImage, 60, 42);
        }
        else if(StringColor.equalsIgnoreCase("Green")){
            car = CodeRacersGUI.createImageView(CodeRacersGUI.greenCarIconImage, 60, 42);
        }
        else if(StringColor.equalsIgnoreCase("Yellow")){
            car = CodeRacersGUI.createImageView(CodeRacersGUI.yellowCarIconImage, 60, 42);
        }
        else if(StringColor.equalsIgnoreCase("Pink")){
            car = CodeRacersGUI.createImageView(CodeRacersGUI.pinkCarIconImage, 60, 42);
        }
        else if(StringColor.equalsIgnoreCase("Orange")){
            car = CodeRacersGUI.createImageView(CodeRacersGUI.orangeCarIconImage, 60, 42);
        }
        else if(StringColor.equalsIgnoreCase("Cyan")){
            car = CodeRacersGUI.createImageView(CodeRacersGUI.cyanCarIconImage, 60, 42);
        }
        car.setRotate(90);
    }

    public int getCount() {
        return count;
    }

    public ImageView getCar(){
        return car;
    }

    public void setMargin(int count) {
        StackPane.setMargin(car, new Insets(GameScreen.defaultLocx, GameScreen.defaultLocy-count*215, 0,0));
    }
    
    public Insets getMargin(int count){
        Insets margin = new Insets(GameScreen.defaultLocx, GameScreen.defaultLocy-count*215, 0,0);
        return margin;
    }



    // Getters and setters
    public String getPlayerId() {
        return playerId;
    }

   

    
}