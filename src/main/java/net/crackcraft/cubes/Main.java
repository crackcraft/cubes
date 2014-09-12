package net.crackcraft.cubes;

/**
 * Created by crackcraft on 12.09.2014.
 */
public class Main {
    public static void main(String[] args) {
        Cube cube = new Cube(
            "  o  o o o  o  \n" +
            " ooo ooooo oooo\n" +
            "ooooo ooo oooo \n" +
            " ooo ooooo oooo\n" +
            "  o  o o o  o  \n" +
            " o o  o o  o o \n" +
            "oooo ooooo oooo\n" +
            " oooo ooo oooo \n" +
            "oooo ooooo oooo\n" +
            "oo o o o  oo oo\n"
        );

        System.out.println(cube.getSide(0));
        System.out.println(cube.getSide(5));
    }


}
