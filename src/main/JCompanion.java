package main;

import javax.swing.*;

public class JCompanion {

    // Constants
    private int stopDistance;
    private int width = 58;
    private int height = 80;
    private int speed = 5;
    private ImageIcon idleLeft, idleRight;
    private ImageIcon[] walkLeft = new ImageIcon[4];
    private ImageIcon[] walkRight = new ImageIcon[4];

    private JLabel output = new JLabel();

    private long time;
    private int frame;

    private state currentState;
    private direction currentDirection;

    private Coordinate location;

    public enum direction {
        LEFT, RIGHT

    }

    public enum state {
        IDLE, WALKING

    }

    public JCompanion(int stopDistance, String imageFilePath, int width, int height, int speed) {

        this.stopDistance = stopDistance;

        currentDirection = direction.RIGHT;
        currentState = state.IDLE;
        location = new Coordinate(3530, 854);

        idleLeft = new ImageIcon(imageFilePath + "/idle/left.png");
        idleRight = new ImageIcon(imageFilePath + "/idle/right.png");

        walkLeft[0] = new ImageIcon(imageFilePath + "/walking/left/left-front.png");
        walkLeft[1] = new ImageIcon(imageFilePath + "/walking/left/right-passing.png");
        walkLeft[2] = new ImageIcon(imageFilePath + "/walking/left/right-front.png");
        walkLeft[3] = new ImageIcon(imageFilePath + "/walking/left/left-passing.png");

        walkRight[0] = new ImageIcon(imageFilePath + "/walking/right/left-front.png");
        walkRight[1] = new ImageIcon(imageFilePath + "/walking/right/right-passing.png");
        walkRight[2] = new ImageIcon(imageFilePath + "/walking/right/right-front.png");
        walkRight[3] = new ImageIcon(imageFilePath + "/walking/right/left-passing.png");

        output.setBounds(100, 100, 87, 120);

    }

    public Coordinate getLocation() {
        return location;

    }

    public void setDirection(direction d) {
        currentDirection = d;

    }

    public void setState(state s) {
        currentState = s;

    }

    public int getStopDistance() {
        return stopDistance;

    }

    public int getWidth() {
        return width;

    }

    public int getHeight() {
        return height;

    }

    public int getSpeed() {
        return height;

    }

    private double calcDistance(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));

    }

    public void move(int x, int y) {
        double dtp = calcDistance(location.getX(), location.getY(), x, y);
        double moveX, moveY;
        if(dtp > getStopDistance()) {
            currentState = state.WALKING;
            if(location.getX() > x) currentDirection = direction.LEFT;
            if(location.getX() < x) currentDirection = direction.RIGHT;
            if(location.getX() != x) {
                double m = (y - location.getY()) / (double) (x - location.getX());
                moveX = speed / (Math.pow(m, 2) + 1);
                moveY = Math.sqrt(Math.pow(speed, 2) - Math.pow(moveX, 2));

            } else {
                moveX = 0;
                moveY = speed;

            }
            if(location.getX() < x) location.move(moveX, 0);
            else location.move(0-moveX, 0);
            if(location.getY() < y) location.move(0, moveY);
            else location.move(0, 0-moveY);

        } else currentState = state.IDLE;

    }

    public void update(int playerX, int playerY) {

        long currentTime = System.currentTimeMillis();

        move(playerX, playerY);

        if(currentState == state.WALKING) {
            if(Math.abs(time - currentTime) > 150) {
                frame = (frame + 1) % 4;
                time = currentTime;

            }

        }

        updateGraphics();

    }

    public void updateGraphics() {

        if (currentState == state.IDLE) {
            switch(currentDirection) {
                case LEFT: output.setIcon(idleLeft); break;
                case RIGHT: output.setIcon(idleRight); break;

            }

        }

        if(currentState == state.WALKING) {
            switch(currentDirection) {
                case LEFT: output.setIcon(walkLeft[frame]); break;
                case RIGHT: output.setIcon(walkRight[frame]); break;

            }

        }


    }

    private static JCompanion sInstance;

    public JLabel get() {
        return output;

    }

    public static JCompanion getInstance() {
        if(sInstance == null) sInstance = new JCompanion(200, "src/images/companion", 58, 80, 5);
        return sInstance;

    }

}
