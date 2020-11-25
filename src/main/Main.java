package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main {

    public static void main(String[] args) {

        Keyboard keyb = new Keyboard();

        JFrame frame = new JFrame("frame");
        frame.setSize(1900, 1100);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(null);

        coords co = new coords(-1000, -1000);
        JLabel map = new JLabel(new ImageIcon("D:/Pictures/among-us-map.jpg"));
        map.setBounds(co.getX(), co.getY(), 7090, 4120);
        frame.add(map);

        JLabel character1234 = new JLabel(new ImageIcon("D:/Pictures/among-us-character.png"));
        character1234.setBounds(100, 100, 482, 440);
        frame.add(character1234);

        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                char charPressed = e.getKeyChar();

                switch(charPressed) {
                    case 'w': keyb.w = true; break;
                    case 'a': keyb.a = true; break;
                    case 's': keyb.s = true; break;
                    case 'd': keyb.d = true; break;

                }

            }

            @Override
            public void keyReleased(KeyEvent e) {
                char charPressed = e.getKeyChar();

                switch(charPressed) {
                    case 'w': keyb.w = false; break;
                    case 'a': keyb.a = false; break;
                    case 's': keyb.s = false; break;
                    case 'd': keyb.d = false; break;

                }

            }
        });

        while(true) {

            int speed = 20;

            if(keyb.w = true) {
                co.move(0, speed);

            }
            if(keyb.a = true) {
                co.move(speed, 0);

            }
            if(keyb.s = true) {
                co.move(0, 0-speed);

            }
            if(keyb.d = true) {
                co.move(0-speed, 0);

            }

            map.setBounds(co.getX(), co.getY(), 7090, 4120);

        }

    }

}
