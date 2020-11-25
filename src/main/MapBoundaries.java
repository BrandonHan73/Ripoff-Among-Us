package main;

import java.util.ArrayList;

public class MapBoundaries {

    private static boolean[][] map;

    private MapBoundaries() {}

    private static void init() {
        map = new boolean[7090][4120];

    }

    public static void setSkeldBoundaries() {
        ArrayList<Coordinate> coords;

        coords = new ArrayList<Coordinate>();
        coords.add(new Coordinate(2973, 532));
        coords.add(new Coordinate(3282, 220));
        coords.add(new Coordinate(4276, 220));
        coords.add(new Coordinate(4731, 673));
        coords.add(new Coordinate(4731, 916));
        coords.add(new Coordinate(4741, 916));
        coords.add(new Coordinate(4786, 957));
        drawBoundaries(coords);

        coords = new ArrayList<Coordinate>();
        coords.add(new Coordinate(2391, 1328));
        coords.add(new Coordinate(2200, 1328));
        coords.add(new Coordinate(2200, 1922));
        coords.add(new Coordinate(2328, 2084));
        coords.add(new Coordinate(3136, 2084));
        coords.add(new Coordinate(3136, 2013));
        coords.add(new Coordinate(2857, 1741));
        coords.add(new Coordinate(2857, 1328));
        coords.add(new Coordinate(2638, 1328));
        drawBoundaries(coords);

    }

    public static void resetMap() {
        init();
        for(boolean[] i : map) {
            for(boolean j : i) {
                j = false;

            }

        }

    }

    public static void drawBoundaries(ArrayList<Coordinate> coords) {
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
