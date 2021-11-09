/**
 *      USACO 2020 - 12 - Problem 2 - Rectangular Pasture
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class RectangularPasture {

    public static void main (String [] args) throws IOException {

        // Input:
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));

        // Output:
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        int n = Integer.parseInt(f.readLine());

        Point[] points = new Point[n];
        for (int i = 0; i < n; i++)
        {
            StringTokenizer st = new StringTokenizer(f.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            points[i] = new Point(x, y);
        }
        Arrays.sort(points);

        long[] upper = new long[n];
        long[] lower = new long[n];

        long result = 1;
        for (int i = 0; i < n; i++)
        {
            result++;
            long top = 0;
            long bottom = 0;
            for (int j = i-1; j >= 0; j--)
            {
                if (points[j].y < points[i].y) {
                    result += (top+1)*(lower[j]+1);
                    upper[j]++;
                    bottom++;
                }
                else if (points[j].y > points[i].y) {
                    result += (bottom+1)*(upper[j]+1);
                    lower[j]++;
                    top++;
                }
                else {
                    result += (top+1)*(bottom+1);
                }
            }
        }
        
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
            if (this.x < p.x) {
                return -1;
            }
            else if (this.x > p.x) {
                return 1;
            }
            else {
                return 0;
            }
        }
    }
}