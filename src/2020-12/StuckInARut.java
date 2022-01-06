/**
 *      USACO 2020 - 12 - Problem 3 - Stuck in a Rut
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class StuckInARut {

    static ArrayList<Cow> northCows;
    static ArrayList<Cow> eastCows;

    public static void main (String [] args) throws IOException {

        // Input:
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));

        // Output:
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        int n = Integer.parseInt(f.readLine());
        northCows = new ArrayList<>();
        eastCows = new ArrayList<>();

        for (int i = 0; i < n; i++)
        {
            StringTokenizer st = new StringTokenizer(f.readLine());
            String dir = st.nextToken();
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            Cow cow = new Cow(x, y, i);

            if (dir.equals("N")) {
                northCows.add(cow);
            } else {
                eastCows.add(cow);
            }
        }

        northCows.sort(new CowXComparator());
        eastCows.sort(new CowYComparator());

        int[] results = new int[n];
        boolean[] stopped = new boolean[n];

        for (Cow east : eastCows)
            for (Cow north : northCows)
            {
                if (!stopped[east.index] && !stopped[north.index] && east.x < north.x && east.y > north.y) {
                    int xdiff = north.x - east.x;
                    int ydiff = east.y - north.y;

                    if (xdiff > ydiff) {
                        stopped[east.index] = true;
                        results[north.index] += results[east.index] + 1;
                    } else if (xdiff < ydiff) {
                        stopped[north.index] = true;
                        results[east.index] += results[north.index] + 1;
                    }
                }
            }

        for (int i = 0; i < n; i++)
        {
            out.println(results[i]);
        }

        out.close();
    }

    static class Cow {

        public int x;
        public int y;
        public int index;

        public Cow (int x, int y, int index) {
            this.x = x;
            this.y = y;
            this.index = index;
        }
    }

    static class CowXComparator implements Comparator<Cow> {

        public int compare(Cow c1, Cow c2) {
            return c1.x - c2.x;
        }
    }

    static class CowYComparator implements Comparator<Cow> {

        public int compare(Cow c1, Cow c2) {
            return c1.y - c2.y;
        }
    }
}