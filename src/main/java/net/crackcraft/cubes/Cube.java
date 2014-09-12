package net.crackcraft.cubes;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by crackcraft on 12.09.2014.
 */
public class Cube {
    public static final int SIZE = 5;
    public static final int OFFSET = (Cube.SIZE-1)/2;
    private final Side[] sides;
    public Cube(String desc3cols2rows) {
        Pattern re = Pattern.compile("^(.....)\\s?(.....)\\s?(.....)$", Pattern.MULTILINE);
        Matcher m = re.matcher(desc3cols2rows);
        String[] rows = new String[2*SIZE*3];
        for(int i=0; i<rows.length/3; i++) {
            if(!m.find()) {
                throw new IllegalArgumentException("row "+i+" is not found");
            }
            rows[3*i+0] = m.group(1);
            rows[3*i+1] = m.group(2);
            rows[3*i+2] = m.group(3);
        }

        sides = new Side[] {
            new Side(rows[0], rows[3], rows[6], rows[9], rows[12]),
            new Side(rows[1], rows[4], rows[7], rows[10], rows[13]),
            new Side(rows[2], rows[5], rows[8], rows[11], rows[14]),
            new Side(rows[15], rows[18], rows[21], rows[24], rows[27]),
            new Side(rows[16], rows[19], rows[22], rows[25], rows[28]),
            new Side(rows[17], rows[20], rows[23], rows[26], rows[29])
        };
    }

    public boolean test(int[] pos, int[] rot) {
        State state = new State();
        if(!state.apply(sides[pos[0]], rot[0])) {
            return false;
        }

        state.rotX();
        if(!state.apply(sides[pos[1]], rot[1])) {
            return false;
        }

        state.rotX();
        if(!state.apply(sides[pos[2]], rot[2])) {
            return false;
        }

        state.rotX();
        if(!state.apply(sides[pos[3]], rot[3])) {
            return false;
        }

        state.rotY();
        if(!state.apply(sides[pos[4]], rot[4])) {
            return false;
        }

        state.rotY();
        state.rotY();
        if(!state.apply(sides[pos[5]], rot[5])) {
            return false;
        }

        // print the solution
        char[][] out = new char[4*Cube.SIZE][];
        for(int i=0; i<out.length; i++) {
            out[i] = new char[3*Cube.SIZE];
            Arrays.fill(out[i], ' ');
        }

        for(int i=0; i<4; i++) {
            sides[pos[i]].print(out, Cube.SIZE*i, Cube.SIZE, rot[i], 1);
        }
        sides[pos[4]].print(out, 0, 2*Cube.SIZE, pos[4], 6);
        sides[pos[5]].print(out, 0, 0, pos[5], 1);

        for(int i=0; i<out.length; i++) {
            System.out.println(new String(out[i]));
        }

        return true;
    }
}
