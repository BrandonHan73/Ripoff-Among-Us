package main;

public class coords {

    private double x, y;

    public coords(double x, double y) {
        this.x = x;
        this.y = y;

    }

    public void move(double x, double y){
        this.x += x;
        this.y += y;

    }

    public int getX() {
        return (int)Math.round(x);

    }

    public int getY() {
        return (int)Math.round(y);

    }

}
