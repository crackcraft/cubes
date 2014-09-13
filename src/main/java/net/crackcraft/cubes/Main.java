package net.crackcraft.cubes;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by crackcraft on 12.09.2014.
 */
public class Main {

    private static synchronized void print(String s) {
        System.out.print(s);
    }

    private static void solve(final String name, final String task) throws IOException {

        new Thread(new Runnable() {
            @Override
            public void run() {
                Cube cube = new Cube(task);
                List<Solution> sols = cube.solve(false);
                print(name + "\n" + task + "\n== " + sols.size() + " solution(s)\n\n");
                try {
                    FileWriter out = new FileWriter(name);
                    for (Solution sol : sols) {
                        out.append(sol.toString());
                        out.append("--8<-----------\n");
                    }
                    out.close();
                } catch(IOException ex) {
                    print(name + ": fail to dump solutions to file");
                }
            }
        }).start();
    }

    public static void main(String[] args) throws IOException {

        solve("task1",
            "  o  o o o  o  \n" +
            " ooo ooooo oooo\n" +
            "ooooo ooo oooo \n" +
            " ooo ooooo oooo\n" +
            "  o  o o o  o  \n" +

            " o o  o o  o o \n" +
            "oooo ooooo oooo\n" +
            " oooo ooo oooo \n" +
            "oooo ooooo oooo\n" +
            "oo o o o  oo oo"
        );

        solve("task2",
            "   oo o o  oo o\n" +
            " ooo oooo ooooo\n" +
            "ooooo oooo ooo \n" +
            " ooo oooo ooooo\n" +
            " o oo o   o  oo\n" +

            "  o    oo  oo  \n" +
            "oooo ooooo ooo \n" +
            " oooo ooo ooooo\n" +
            "oooo ooooo ooo \n" +
            "  o  o o  oo oo"
        );

        solve("task3",
            "oo o    oo o   \n" +
            "oooo oooo oooo \n" +
            "oooo ooooo oooo\n" +
            " oooo ooo oooo \n" +
            "  o   o o   o  \n" +

            "oo oo  o o o oo\n" +
            " oooo oooo ooo \n" +
            "oooo ooooo oooo\n" +
            " ooo oooo oooo \n" +
            " o o o oo oo o "
        );

        solve("task4",
            "  o    o o  o o\n" +
            "oooo ooooo oooo\n" +
            " oooo ooo oooo \n" +
            "oooo oooo ooooo\n" +
            " o o  o o o o  \n" +

            "o o o  o   o o \n" +
            "ooooo oooo ooo \n" +
            " ooo oooo ooooo\n" +
            "ooooo oooo ooo \n" +
            "o o  oo o  o oo"
        );
    }
}
