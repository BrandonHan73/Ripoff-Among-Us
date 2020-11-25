package main;

import javax.net.ssl.SSLKeyException;
import java.util.ArrayList;

public class SkeldMapBoundaries {

    private static ArrayList<Coordinate> coords;
    private static boolean[][] map;

    private SkeldMapBoundaries() {

    }

    public static void setBoundaries() {
        coords = new ArrayList<Coordinate>();
        map = new boolean[7090][4120];

        coords.add(new Coordinate(2974, 533));
        coords.add(new Coordinate(3282, 223));

    }

    public static void drawBoundaries() {
        for(boolean[] i : map) {
            for(boolean j : i) {
                j = false;

            }

        }
        System.out.println(map[100][100]);

    }

}
