package main;

import javax.swing.*;

public class JCharacter {

    private JLabel output = new JLabel();

    private final ImageIcon idleLeft = new ImageIcon("src/images/character/idle/left.png");
    private final ImageIcon idleRight = new ImageIcon("src/images/character/idle/right.png");

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

        walkLeft[0] = new ImageIcon("src/images/character/walking/left/front-forward.png");
        walkLeft[1] = new ImageIcon("src/images/character/walking/left/back-passing.png");
        walkLeft[2] = new ImageIcon("src/images/character/walking/left/back-forward.png");
        walkLeft[3] = new ImageIcon("src/images/character/walking/left/front-passing.png");

        walkRight[0] = new ImageIcon("src/images/character/walking/right/front-forward.png");
        walkRight[1] = new ImageIcon("src/images/character/walking/right/back-passing.png");
        walkRight[2] = new ImageIcon("src/images/character/walking/right/back-forward.png");
        walkRight[3] = new ImageIcon("src/images/character/walking/right/front-passing.png");

        output.setBounds(100, 100, 87, 120);

    }

    public void setDirection(direction d) {
        currentDirection = d;

    }

    public void setState(state s) {
        currentState = s;

    }

    public void update() {

        long currentTime = System.currentTimeMillis();

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

    private static JCharacter sInstance;

    public JLabel get() {
        return output;

    }

    public static JCharacter getInstance() {
        if(sInstance == null) sInstance = new JCharacter();
        return sInstance;

    }

}
