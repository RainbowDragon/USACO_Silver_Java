/**
 *      USACO 2016 - 02 - Problem 2 - Load Balancing
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class LoadBalancing {

    public static String FileName = "balancing";

    public static void main (String [] args) throws IOException {

        // Input:
        BufferedReader f = new BufferedReader(new FileReader(FileName + ".in"));

        int n = Integer.parseInt(f.readLine());
        Point[] points = new Point[n];

        StringTokenizer st;
        for (int i = 0; i < n; i++)
        {
            st = new StringTokenizer(f.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            points[i] = new Point(x, y);
        }

        Arrays.sort(points);

        int result = n;

        for (int i = 0; i < n; i++)
        {
            int leftabove = 0;
            int rightabove = 0;
            int leftbelow = 0;
            int rightbelow = 0;

            for (int j = 0; j < n; j++)
            {
                if (points[j].y > points[i].y) {
                    rightabove++;
                } else {
                    rightbelow++;
                }
            }

            for (int j = 0; j < n; j++)
            {
                if (points[j].y > points[i].y) {
                    leftabove++;
                    rightabove--;
                } else {
                    leftbelow++;
                    rightbelow--;
                }

                int curMax = Math.max(Math.max(leftabove, rightabove), Math.max(leftbelow, rightbelow));
                result = Math.min(result, curMax);
            }
        }

        // Output:
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(FileName + ".out")));
        out.println(result);
        out.close();
    }

    static class Point implements Comparable<Point> {

        public int x;
        public int y;

        public Point (int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int compareTo(Point p) {
            return this.x - p.x;
        }
    }
}