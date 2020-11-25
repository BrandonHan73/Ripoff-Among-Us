package main;

import javax.swing.*;
import java.awt.*;

public class JCharacter {

    private JLabel output = new JLabel();

    private final ImageIcon idleLeft = new ImageIcon("src/images/idle/left.png");
    private final ImageIcon idleRight = new ImageIcon("src/images/idle/right.png");

    private long time;
    private int frame;

    private ImageIcon[] walkLeft = new ImageIcon[4];
    private ImageIcon[] walkRight = new ImageIcon[4];

    private state currentState;
    private direction currentDirection;

    public enum direction {
        LEFT, RIGHT

    }

    public enum state {
        IDLE, WALKING

    }

    private JCharacter() {

        currentDirection = direction.RIGHT;
        currentState = state.IDLE;

        walkLeft[0] = new ImageIcon("src/images/walking/left/left-front.png");
        walkLeft[1] = new ImageIcon("src/images/walking/left/right-passing.png");
        walkLeft[2] = new ImageIcon("src/images/walking/left/right-front.png");
        walkLeft[3] = new ImageIcon("src/images/walking/left/left-passing.png");

        walkRight[0] = new ImageIcon("src/images/walking/right/left-front.png");
        walkRight[1] = new ImageIcon("src/images/walking/right/right-passing.png");
        walkRight[2] = new ImageIcon("src/images/walking/right/right-front.png");
        walkRight[3] = new ImageIcon("src/images/walking/right/left-passing.png");

        output.setBounds(100, 100, 87, 120);

    }

    public void setDirection(direction d) {
        currentDirection = d;

    }

    public void setState(state s) {
        currentState = s;
        if(s == state.WALKING) time = System.currentTimeMillis();

    }

    public void update() {

        long currentTime = System.currentTimeMillis();

        if (currentState == state.IDLE) {
            switch(currentDirection) {
                case LEFT: output.setIcon(idleLeft); break;
                case RIGHT: output.setIcon(idleRight); break;

            }

        }

        if(currentState == state.WALKING) {
            if(Math.abs(time - currentTime) > 150) {
                frame = (frame + 1) % 4;
                time = currentTime;

            }
            switch(currentDirection) {
                case LEFT: output.setIcon(walkLeft[frame]); break;
                case RIGHT: output.setIcon(walkRight[frame]); break;

            }

        }

    }

    private static JCharacter sInstance;

    public JLabel get() {
        return output;

    }

    public static JCharacter getInstance() {
        if(sInstance == null) sInstance = new JCharacter();
        return sInstance;

    }

}
