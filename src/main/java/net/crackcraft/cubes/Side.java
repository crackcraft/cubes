package net.crackcraft.cubes;

/**
 * Created by crackcraft on 12.09.2014.
 */
public class Side {
    private final boolean[][] data;
    public Side(String... rows) {
        final int N = rows.length;
        data = new boolean[N][];
        for(int x=0; x<N; x++) {
            data[x] = new boolean[N];
            for(int y=0; y<N; y++) {
                data[x][y] = !Character.isSpaceChar(rows[x].charAt(y));
            }
        }
    }

    public boolean get(int x, int y) {
        return data[x][y];
    }

    private final static int[][] ROTATIONS = new int[][] {{
        1, 0,
        0, 1,
    }, {
        0,-1,
        1, 0
    }, {
        -1, 0,
        0, -1
    }, {
        0, 1,
        -1, 0
    }, {
        1, 0,
        0, -1
    }, {
        0, -1,
        -1, 0
    }, {
        -1, 0,
        0, 1
    }, {
        0, 1,
        1, 0
    }};

    public boolean get(int x, int y, int rot) {
        int x1 = x - Cube.OFFSET;
        int y1 = y - Cube.OFFSET;
        int x2 = x1 * ROTATIONS[rot][0] + y1 * ROTATIONS[rot][2];
        int y2 = x1 * ROTATIONS[rot][1] + y1 * ROTATIONS[rot][3];
        return data[x2+Cube.OFFSET][y2+Cube.OFFSET];
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        for(int x=0; x<Cube.SIZE; x++) {
            out.append('\n');
            for(int y=0; y<Cube.SIZE; y++) {
                out.append(data[x][y]? 'o': ' ');
            }
        }
        out.append('\n');
        return out.toString();
    }

    private int rotx(int x, int y, int rot) {
        int x1 = x - Cube.OFFSET;
        int y1 = y - Cube.OFFSET;
        return Cube.OFFSET + x1 * ROTATIONS[rot][0] + y1 * ROTATIONS[rot][2];
    }
    private int roty(int x, int y, int rot) {
        int x1 = x - Cube.OFFSET;
        int y1 = y - Cube.OFFSET;
        return Cube.OFFSET + x1 * ROTATIONS[rot][1] + y1 * ROTATIONS[rot][3];
    }


    public void print(char[][] out, int x0, int y0, int rot, int rot2) {
        for(int x=0; x<Cube.SIZE; x++) {
            for(int y=0; y<Cube.SIZE; y++) {
                out[x0+rotx(x, y, rot2)][y0+roty(x, y, rot2)] = (get(x, y, rot)? 'o': ' ');
            }
        }

    }

}
