package main;

public class Coordinate {

    double x, y;

    public Coordinate(double x, double y) {
        this.x = x;
        this.y = y;

    }

    public void move(double x, double y) {
        this.x += x;
        this.y += y;

    }

    public int getX() {
        return (int)x;

    }

    public int getY() {
        return (int)y;

    }

}
