package main;

import javax.swing.*;
import java.util.ArrayList;

public class AJLabel extends JLabel {

    private JLabel mainLabel;
    private ArrayList<ImageIcon> icons;
    private int frame;
    private long time;

    public AJLabel(ImageIcon icon) {
        icons = new ArrayList<ImageIcon>();
        icons.add(icon);
        mainLabel = new JLabel(icon);
        frame = 0;
        time = System.currentTimeMillis();

    }

    public JLabel get() {
        return mainLabel;

    }

    public void addFrame(ImageIcon icon) {
        icons.add(icon);

    }

    public void nextImage() {
        frame++;
        if(frame == icons.size()) frame = 0;
        mainLabel.setIcon(icons.get(frame));

    }

    public void update() {

        if(Math.abs(time - System.currentTimeMillis()) > 1000) {
            time = System.currentTimeMillis();
            nextImage();

        }

    }

}
