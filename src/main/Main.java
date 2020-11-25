package main;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main {

    // Constants
    static final ImageIcon MapImage = new ImageIcon("src/images/map.jpg");
    static final int characterSpeed = 5;
    static final double rt2 = Math.sqrt(2);
    static final int mapXSize = 7090;
    static final int mapYSize = 4120;
    static final int characterXSize = 87;
    static final int characterYSize = 120;
    static final double characterStartX = 3530;
    static final double characterStartY = 854;
    static final int shadowHeight = 12;
    static final int shadowWidth = 67;

    // Main variables
    private static JFrame frame;
    private static JLabel map;
    private static Keyboard keyboardState;
    private static Coordinate playerCoordinate;

    public static void main(String[] args) {

        // Set up window
        frame = new JFrame("Ripoff Among Us because I'm bored");
        frame.setVisible(true);
        frame.setSize(1900, 1100);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Keyboard
        keyboardState = new Keyboard();

        // Background
        map = new JLabel(MapImage);
        playerCoordinate  = new Coordinate(characterStartX, characterStartY);

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

        // Setting boundaries
        MapBoundaries.setSkeldBoundaries();
        MapBoundaries.drawBoundaries();

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
        wantedX = (frame.getSize().width / 2) - characterXSize / 2;
        wantedY = (frame.getSize().height / 2) - characterYSize / 2;

        JCharacter.getInstance().get().setBounds(wantedX, wantedY, characterXSize, characterYSize);
        map.setBounds(0-playerCoordinate.getX() + wantedX, 0-playerCoordinate.getY() + wantedY, 7090, 4120);

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
            if(keyboardState.w) secureMove(0, 0-wantedSpeed);
            if(keyboardState.a) secureMove(0-wantedSpeed, 0);
            if(keyboardState.s) secureMove(0, wantedSpeed);
            if(keyboardState.d) secureMove(wantedSpeed, 0);

        }

    }

    public static void secureMove(double x, double y) {
        Coordinate endCoords = new Coordinate(playerCoordinate.getX(), playerCoordinate.getY());
        boolean permission = true;
        endCoords.move(x, y);
        if(!((0 < endCoords.getX()) && (endCoords.getX() < mapXSize - characterXSize) && (0 < endCoords.getY()) && (endCoords.getY() < mapYSize - characterYSize))) permission = false;
        else {
            for(int i = endCoords.getX() + ((characterXSize - shadowWidth) / 2); i < endCoords.getX() + characterXSize - ((characterXSize - shadowWidth) / 2); i++) {
                for(int j = endCoords.getY() + characterYSize - shadowHeight; j < endCoords.getY() + characterYSize; j++) {
                    if(MapBoundaries.getLoc(i, j)) {
                        permission = false;
                        break;

                    }

                }
                if(!permission) break;

            }

        }
        if(permission) playerCoordinate.move(x, y);

    }

}
