package main;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame("window");
        frame.setVisible(true);
        frame.setSize(1900, 1100);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        final ImageIcon CharacterRight = new ImageIcon("src/images/idle/right.png");
        final ImageIcon CharacterLeft = new ImageIcon("src/images/idle/left.png");
        final ImageIcon Map = new ImageIcon("src/images/map.jpg");

        Keyboard k = new Keyboard();
        Coordinate c = new Coordinate(-2600, -300);

        JLabel l = new JLabel(CharacterRight);
        frame.add(JCharacter.getInstance().get());

        JLabel map = new JLabel(Map);
        frame.add(map);

        frame.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {

            }

            public void keyPressed(KeyEvent e) {
                char key = e.getKeyChar();

                switch(key) {
                    case 'w': k.w = true; break;
                    case 'a': k.a = true; JCharacter.getInstance().setDirection(JCharacter.direction.LEFT); break;
                    case 's': k.s = true; break;
                    case 'd': k.d = true; JCharacter.getInstance().setDirection(JCharacter.direction.RIGHT); break;

                }

                JCharacter.getInstance().setState(JCharacter.state.WALKING);

            }

            public void keyReleased(KeyEvent e) {
                char key = e.getKeyChar();

                switch(key) {
                    case 'w': k.w = false; break;
                    case 'a': k.a = false; break;
                    case 's': k.s = false; break;
                    case 'd': k.d = false; break;

                }

            }

        });

        int speed = 5;

        while(true) {
            if(k.w) {
                c.move(0, speed);

            }
            if(k.a) {
                c.move(speed, 0);

            }
            if(k.s) {
                c.move(0, 0-speed);

            }
            if(k.d) {
                c.move(0-speed, 0);

            }

            int wantedX, wantedY;
            wantedY = (frame.getSize().height / 2) - 120;
            wantedX = (frame.getSize().width / 2) - 110;

            JCharacter.getInstance().get().setBounds(wantedX, wantedY, 87, 120);
            l.setBounds(wantedX, wantedY, 220, 241);
            map.setBounds(c.getX(), c.getY(), 7090, 4120);

            JCharacter.getInstance().update();

            long time = System.currentTimeMillis();
            while(true) {
                if(Math.abs(System.currentTimeMillis() - time) > 10) break;

            }

        }

    }

}
