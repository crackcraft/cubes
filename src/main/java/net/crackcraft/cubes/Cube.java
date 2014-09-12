package net.crackcraft.cubes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by crackcraft on 12.09.2014.
 */
public class Cube {
    public static final int SIZE = 5;
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

    public Side getSide(int i) {
        return sides[i];
    }
}
