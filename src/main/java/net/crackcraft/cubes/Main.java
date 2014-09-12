package net.crackcraft.cubes;

import java.util.Arrays;

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

        int[] pos = new int[] {0, 1, 2, 3, 4, 5};
        int[] rot = new int[6];
        do {
            if(cube.test(pos, rot)) {
                break;
            }
        } while(nextRot(rot) || nextPos(pos));
    }

    // return false if rot is overflow
    private static boolean nextRot(int[] rot) {
        for(int i=0; i<rot.length; i++) {
            rot[i]++;
            if(rot[i]<8) {
                return true;
            }
            rot[i] = 0;
        }
        return false;
    }

    // return false if pos is overflow
    private static boolean nextPos(int[] pos) {
        int i = pos.length - 2;
        while(i>=0 && pos[i]>pos[i+1]) {
            i--;
        }
        if(i<0) {
            return false;
        }
        int k = i+1;

        for(int j=i+2; j<pos.length; j++) {
            if(pos[j]> pos[i] && pos[j]<pos[k]) {
                k = j;
            }
        }

        int t = pos[i];
        pos[i] = pos[k];
        pos[k] = t;
        Arrays.sort(pos, i+1, pos.length);

        return true;
    }


}
