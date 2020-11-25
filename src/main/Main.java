package main;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame("window");
        frame.setVisible(true);
        frame.setSize(1000, 1000);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JLabel l = new JLabel(new ImageIcon("D:/Pictures/among-us-character.png"));
        l.setBounds(100, 100, 440, 482);
        frame.add(l);

        JLabel map = new JLabel(new ImageIcon("D:/Pictures/among-us-map.jpg"));
        map.setBounds(-1000, -1000, 7090, 4120);
        frame.add(map);

        l.setBounds(100, 101, 440, 482);
        map.setBounds(-1001, -1000, 7090, 4120);

    }

}
