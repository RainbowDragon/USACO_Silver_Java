/**
 *      USACO 2016 - 12 - Problem 3 - Moocast
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class Moocast {

    public static String FileName = "moocast";

    static boolean[][] canReach;
    static boolean[] visited;
    static int n, count;

    public static void main (String [] args) throws IOException {

        // Input:
        BufferedReader f = new BufferedReader(new FileReader(FileName + ".in"));

        n = Integer.parseInt(f.readLine());
        Point[] points = new Point[n];

        for (int i = 0; i < n; i++)
        {
            StringTokenizer st = new StringTokenizer(f.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());

            points[i] = new Point(x, y, p);
        }

        canReach = new boolean[n][n];

        for (int i = 0; i < n; i++)
            for (int j = i; j < n; j++)
            {
                int dist2 = (points[i].x - points[j].x)*(points[i].x - points[j].x) + (points[i].y - points[j].y)*(points[i].y - points[j].y);
                canReach[i][j] = (dist2 <= points[i].p*points[i].p);
                canReach[j][i] = (dist2 <= points[j].p*points[j].p);
            }

        int result = 0;
        for (int i = 0; i < n; i++)
        {
            count = 0;
            visited = new boolean[n];

            dfs(i);

            result = Math.max(result, count);
        }

        // Output:
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(FileName + ".out")));
        out.println(result);
        out.close();
    }

    public static void dfs(int i) {

        if (visited[i]) {
            return;
        }

        visited[i] = true;
        count++;

        for (int j = 0; j < n; j++)
        {
            if (canReach[i][j] && !visited[j])
            {
                dfs(j);
            }
        }
    }

    static class Point {

        public int x;
        public int y;
        public int p;

        public Point (int x, int y, int p) {
            this.x = x;
            this.y = y;
            this.p = p;
        }
    }
}