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
        coords.add(new Coordinate(4731, 917));
        coords.add(new Coordinate(5189, 925));

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
            boolean vertical = false;
            double m = 0;
            if(c1.getX() != c2.getX()) {
                m = (c1.getY() - c2.getY()) / (double)(c1.getX() - c2.getX());

            } else vertical = true;
            if((Math.abs(m) <= 1) && !vertical) {
                for(int j = c1.getX(); j != c2.getX(); j += (c2.getX() - c1.getX()) / Math.abs((c1.getX() - c2.getX()))) {
                    map[j][(int) f(m, c1.getX(), c1.getY(), j)] = true;

                }

            } else {
                if(!vertical) m = 1 / m;
                for(int j = c1.getY(); j != c2.getY(); j += (c2.getY() - c1.getY()) / Math.abs((c1.getY() - c2.getY()))) {
                    map[(int) f(m, c1.getY(), c1.getX(), j)][j] = true;

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
