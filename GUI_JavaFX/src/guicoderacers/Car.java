package guicoderacers;

public class Car {
    private String playerId;
    private double x;
    private double y;
    private double speed;

    public Car(String playerId, double x, double y, double speed) {
        this.playerId = playerId;
        this.x = x;
        this.y = y;
        this.speed = speed;
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
