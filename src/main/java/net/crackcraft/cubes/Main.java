package net.crackcraft.cubes;

import java.util.List;

/**
 * Created by crackcraft on 12.09.2014.
 */
public class Main {

    private final static String TASK0 =
            "  o  o o o  o  \n" +
            " ooo ooooo oooo\n" +
            "ooooo ooo oooo \n" +
            " ooo ooooo oooo\n" +
            "  o  o o o  o  \n" +
            " o o  o o  o o \n" +
            "oooo ooooo oooo\n" +
            " oooo ooo oooo \n" +
            "oooo ooooo oooo\n" +
            "oo o o o  oo oo\n";

    private final static String TASK1 =
            "  o  o o o  o  \n" +
            " ooo ooooo oooo\n" +
            "ooooo ooo oooo \n" +
            " ooo ooooo oooo\n" +
            "  o  o o o  o  \n" +

            " o o  o o  o o \n" +
            "oooo ooooo oooo\n" +
            " oooo ooo oooo \n" +
            "oooo ooooo oooo\n" +
            "oo o o o  oo oo";

    public static void main(String[] args) {
        Cube cube = new Cube(TASK1);
        List<Solution> sols = cube.solve(false);

        for (Solution sol : sols) {
            assert test(sol.unfold());
        }

        for (Solution sol : sols) {
            System.out.println(sol);
        }

    }

    private static boolean test(char[][] out) {
        // test by folding
        return
            check(out, 0, 5, 0, 4, 19, 5) &&
            check(out, 1, 5, 1, 4) &&
            check(out, 2, 5, 2, 4) &&
            check(out, 3, 5, 3, 4) &&
            check(out, 4, 5, 4, 4, 5, 5) &&
            check(out, 6, 5, 4, 3) &&
            check(out, 7, 5, 4, 2) &&
            check(out, 8, 5, 4, 1) &&
            check(out, 9, 5, 4, 0, 10, 5) &&
            check(out, 11, 5, 3, 0) &&
            check(out, 12, 5, 2, 0) &&
            check(out, 13, 5, 1, 0) &&
            check(out, 14, 5, 0, 0, 15, 5) &&
            check(out, 16, 5, 0, 1) &&
            check(out, 17, 5, 0, 2) &&
            check(out, 18, 5, 0, 3) &&

            check(out, 0, 9, 0, 10, 19, 9) &&
            check(out, 1, 9, 1, 10) &&
            check(out, 2, 9, 2, 10) &&
            check(out, 3, 9, 3, 10) &&
            check(out, 4, 9, 4, 10, 5, 9) &&
            check(out, 6, 9, 4, 11) &&
            check(out, 7, 9, 4, 12) &&
            check(out, 8, 9, 4, 13) &&
            check(out, 9, 9, 4, 14, 10, 9) &&
            check(out, 11, 9, 3, 14) &&
            check(out, 12, 9, 2, 14) &&
            check(out, 13, 9, 1, 14) &&
            check(out, 14, 9, 0, 14, 15, 9) &&
            check(out, 16, 9, 0, 13) &&
            check(out, 17, 9, 0, 12) &&
            check(out, 18, 9, 0, 11);
    }

    private static boolean check(char[][] out, int... coords) {
        int s = 0;
        for(int i=0; i<coords.length; i+=2) {
            if(out[coords[i]][coords[i+1]]!=' ') {
                s++;
            }
        }
        if(s==1) {
            return true;
        }
        for(int i=0; i<coords.length; i+=2) {
            out[coords[i]][coords[i+1]] = (out[coords[i]][coords[i+1]]!=' ')? '#': '*';
        }
        return false;
    }

}
