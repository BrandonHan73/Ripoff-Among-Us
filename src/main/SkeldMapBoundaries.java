package main;

import java.util.ArrayList;

public class SkeldMapBoundaries {

    private ArrayList<Coordinate> coords;
    private boolean[][] map;

    private SkeldMapBoundaries() {
        coords = new ArrayList<Coordinate>();
        map = new boolean[7090][4120];

        coords.add(new Coordinate(2974, 533));
        coords.add(new Coordinate(3282, 223));

    }

}
