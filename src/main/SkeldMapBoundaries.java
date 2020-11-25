package main;

import javax.net.ssl.SSLKeyException;
import java.util.ArrayList;

public class SkeldMapBoundaries {

    private static ArrayList<Coordinate> coords;
    private static boolean[][] map;

    private SkeldMapBoundaries() {}

    public static void setBoundaries() {
        coords = new ArrayList<Coordinate>();
        map = new boolean[7090][4120];

        coords.add(new Coordinate(2974, 533));
        coords.add(new Coordinate(3282, 223));
        coords.add(new Coordinate(4277, 221));
        coords.add(new Coordinate(4730, 673));

    }

    public static void drawBoundaries() {
        for(boolean[] i : map) {
            for(boolean j : i) {
                j = false;

            }

        }
        for(int i = 0; i < coords.size() - 1; i++) {
            Coordinate c1 = coords.get(i);
            Coordinate c2 = coords.get(i + 1);
            if(c1.getX() != c2.getX()) {
                double m = (c1.getY() - c2.getY()) / (double)(c1.getX() - c2.getX());
                for(int j = c1.getX(); j != c2.getX(); j += (c2.getX() - c1.getX()) / Math.abs((c1.getX() - c2.getX()))) {
                    map[j][(int)f(m, c1.getX(), c1.getY(), j)] = true;

                }

            }

        }

    }

    private static double f(double slope, int x, int y, int input) {
        return slope * (input - x) + y;

    }

    public static boolean getLoc(int x, int y) {
        return map[x][y];

    }

}
