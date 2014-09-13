package net.crackcraft.cubes;

/**
 * Created by crackcraft on 12.09.2014.
 */
public class State {

    private final boolean[] data = data();
    private static boolean[] data() {
        return new boolean[Cube.SIZE*Cube.SIZE*Cube.SIZE];
    }

    private int idx(int x, int y, int z) {
        return x*Cube.SIZE*Cube.SIZE+y*Cube.SIZE+z;
    }

    private boolean get(int x, int y, int z) {
        return data[idx(x, y, z)];
    }
    private void set(int x, int y, int z, boolean v) {
        data[idx(x, y, z)] = v;
    }

    public boolean apply(Side side, int rot) {
        for(int x=0; x<Cube.SIZE; x++) {
            for(int y=0; y<Cube.SIZE; y++) {
                if(side.get(x, y, rot)) {
                    if(get(x, y, 0)) {
                        return false;
                    } else {
                        set(x, y, 0, true);
                    }
                }
            }
        }
        return true;
    }

    private void transform(int[] m) {
        boolean next[] = data();

        for(int x=0; x<Cube.SIZE; x++) {
            final int x1 = x-Cube.OFFSET;
            for(int y=0; y<Cube.SIZE; y++) {
                final int y1 = y-Cube.OFFSET;
                for(int z=0; z<Cube.SIZE; z++) {
                    if(get(x, y, z)) {
                        final int z1 = z - Cube.OFFSET;
                        int x2 = x1 * m[0] + y1 * m[3] + z1 * m[6];
                        int y2 = x1 * m[1] + y1 * m[4] + z1 * m[7];
                        int z2 = x1 * m[2] + y1 * m[5] + z1 * m[8];

                        next[idx(x2 + Cube.OFFSET, y2 + Cube.OFFSET, z2 + Cube.OFFSET)] = true;
                    }
                }
            }
        }
        System.arraycopy(next, 0, data, 0, data.length);
    }

    public void rotX() { transform(ROTATE_X); }
    private static int[] ROTATE_X = new int[]{
        1, 0, 0,
        0, 0, -1,
        0, 1, 0
    };


    public void rotY() { transform(ROTATE_Y); }
    private static int[] ROTATE_Y = new int[]{
        0, 0, 1,
        0, 1, 0,
        -1, 0, 0
    };


    public void rotZ() { transform(ROTATE_Z); }
    private static int[] ROTATE_Z = new int[]{
        0, -1, 0,
        1, 0, 0,
        0, 0, 1
    };

}
