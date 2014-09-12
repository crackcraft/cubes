package net.crackcraft.cubes;

/**
 * Created by crackcraft on 12.09.2014.
 */
public class Side {
    private final boolean[][] plane;
    public Side(String... rows) {
        final int N = rows.length;
        plane = new boolean[N][];
        for(int x=0; x<N; x++) {
            plane[x] = new boolean[N];
            for(int y=0; y<N; y++) {
                plane[x][y] = !Character.isSpaceChar(rows[x].charAt(y));
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        for(int x=0; x<plane.length; x++) {
            out.append('\n');
            for(int y=0; y<plane[x].length; y++) {
                out.append(plane[x][y]? '#': ' ');
            }
        }
        out.append('\n');
        return out.toString();
    }
}
