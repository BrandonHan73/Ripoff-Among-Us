package main;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main {

    // Constants
    static final ImageIcon MapImage = new ImageIcon("src/images/map.jpg");
    static final int characterSpeed = 5;
    static final double rt2 = Math.sqrt(2);

    // Main variables
    private static JFrame frame;
    private static JLabel map;
    private static Keyboard keyboardState;
    private static Coordinate mapCoordinate;

    public static void main(String[] args) {

        // Set up window
        frame = new JFrame("window");
        frame.setVisible(true);
        frame.setSize(1900, 1100);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Keyboard
        keyboardState = new Keyboard();

        // Background
        map = new JLabel(MapImage);
        mapCoordinate  = new Coordinate(-2600, -300);

        // Add character and frame to window
        frame.add(JCharacter.getInstance().get());
        frame.add(map);

        // Add key listener
        frame.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {}

            public void keyPressed(KeyEvent e) {
                switch(e.getKeyChar()) {
                    case 'w': keyboardState.w = true; break;
                    case 'a': keyboardState.a = true; JCharacter.getInstance().setDirection(JCharacter.direction.LEFT); break;
                    case 's': keyboardState.s = true; break;
                    case 'd': keyboardState.d = true; JCharacter.getInstance().setDirection(JCharacter.direction.RIGHT); break;
                    case 'q': System.exit(0);

                }
                JCharacter.getInstance().setState(JCharacter.state.WALKING);

            }

            public void keyReleased(KeyEvent e) {
                switch(e.getKeyChar()) {
                    case 'w': keyboardState.w = false; break;
                    case 'a': keyboardState.a = false; break;
                    case 's': keyboardState.s = false; break;
                    case 'd': keyboardState.d = false; break;

                }
                if(!keyboardState.w && !keyboardState.a && !keyboardState.s && !keyboardState.d) JCharacter.getInstance().setState(JCharacter.state.IDLE);

            }

        });

        // Game loop
        while(true) {
            moveAll();
            resetBounds();
            JCharacter.getInstance().update();
            wait(10);

        }

    }

    public static void resetBounds() {
        int wantedX, wantedY;
        wantedY = (frame.getSize().height / 2) - 120;
        wantedX = (frame.getSize().width / 2) - 110;

        JCharacter.getInstance().get().setBounds(wantedX, wantedY, 87, 120);
        map.setBounds(mapCoordinate.getX(), mapCoordinate.getY(), 7090, 4120);

    }

    public static void wait(int milliseconds) {
        long time = System.currentTimeMillis();
        while(true) {
            if(Math.abs(System.currentTimeMillis() - time) > milliseconds) break;

        }

    }

    public static void moveAll() {
        if(!((keyboardState.w == keyboardState.a) && (keyboardState.s == keyboardState.d) && (keyboardState.w == keyboardState.s))) {
            double wantedSpeed;
            if ((keyboardState.w != keyboardState.s) && (keyboardState.a != keyboardState.d)) wantedSpeed = characterSpeed / rt2;
            else wantedSpeed = characterSpeed;
            if(keyboardState.w) mapCoordinate.move(0, wantedSpeed);
            if(keyboardState.a) mapCoordinate.move(wantedSpeed, 0);
            if(keyboardState.s) mapCoordinate.move(0, 0-wantedSpeed);
            if(keyboardState.d) mapCoordinate.move(0-wantedSpeed, 0);

        }

    }

}
