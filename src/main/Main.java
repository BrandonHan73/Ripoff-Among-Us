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

        Keyboard k = new Keyboard();
        Coordinate c = new Coordinate(-100, 100);

        JLabel l = new JLabel(new ImageIcon("D:/Pictures/among-us-character.png"));
        l.setBounds(100, 100, 440, 482);
        frame.add(l);

        JLabel map = new JLabel(new ImageIcon("D:/Pictures/among-us-map.jpg"));
        map.setBounds(-1000, -1000, 7090, 4120);
        frame.add(map);

        frame.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {

            }

            public void keyPressed(KeyEvent e) {
                char key = e.getKeyChar();

                switch(key) {
                    case 'w': k.w = true; break;
                    case 'a': k.a = true; break;
                    case 's': k.s = true; break;
                    case 'd': k.d = true; break;

                }

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

        l.setBounds(100, 101, 440, 482);
        map.setBounds(-1001, -1000, 7090, 4120);

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

            l.setBounds(900, 100, 440, 482);
            map.setBounds(c.getX(), c.getY(), 7090, 4120);

            long time = System.currentTimeMillis();
            while(true) {
                if(Math.abs(System.currentTimeMillis() - time) > 10) break;

            }

        }

    }

}
