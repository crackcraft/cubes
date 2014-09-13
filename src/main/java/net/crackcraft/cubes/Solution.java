package net.crackcraft.cubes;

import java.util.Arrays;

/**
* Created by ivanove on 13.09.2014.
*/
public class Solution {
    private final Cube cube;
    private final int[] pos = new int[] {0, 1, 2, 3, 4, 5};
    private final int[] rot = new int[6];

    public Solution(Cube cube) {
        this.cube = cube;
    }

    public Solution(Solution that) {
        cube = that.cube;
        System.arraycopy(that.pos, 0, pos, 0, pos.length);
        System.arraycopy(that.rot, 0, rot, 0, rot.length);
    }

    public boolean next() {
        return nextRot() || nextPos();
    }

    private boolean nextRot() {
        for(int i=0; i<rot.length; i++) {
            rot[i]++;
            if(rot[i]<8) {
                return true;
            }
            rot[i] = 0;
        }
        return false;
    }

    private boolean nextPos() {
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
        Arrays.sort(pos, i + 1, pos.length);

        return true;
    }

    public boolean test() {
        State state = new State();
        if (!state.apply(cube.side(pos[0]), rot[0])) {
            return false;
        }

        state.rotX();
        if (!state.apply(cube.side(pos[1]), rot[1])) {
            return false;
        }

        state.rotX();
        if (!state.apply(cube.side(pos[2]), rot[2])) {
            return false;
        }

        state.rotX();
        if (!state.apply(cube.side(pos[3]), rot[3])) {
            return false;
        }

        state.rotY();
        if (!state.apply(cube.side(pos[4]), rot[4])) {
            return false;
        }

        state.rotY();
        state.rotY();
        if (!state.apply(cube.side(pos[5]), rot[5])) {
            return false;
        }

        return true;
    }

    public char[][] unfold() {
        char[][] out = new char[4 * Cube.SIZE][];
        for (int i = 0; i < out.length; i++) {
            out[i] = new char[3 * Cube.SIZE];
            Arrays.fill(out[i], ' ');
        }

        for (int i = 0; i < 4; i++) {
            cube.side(pos[i]).print(out, Cube.SIZE * i, Cube.SIZE, rot[i], 1);
        }
        cube.side(pos[4]).print(out, 0, 2 * Cube.SIZE, rot[4], 2);
        cube.side(pos[5]).print(out, 0, 0, rot[5], 0);
        return out;
    }

    @Override
    public String toString() {
        char[][] out = unfold();
        StringBuilder sb = new StringBuilder();
        for(char[] line: out) {
            sb.append(line).append('\n');
        }
        return sb.toString();
    }
}
