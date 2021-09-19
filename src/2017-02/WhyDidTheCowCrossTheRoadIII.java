/**
 *      USACO 2017 - 02 - Problem 3 - Why did the Cow Cross the Road III
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class WhyDidTheCowCrossTheRoadIII {

    public static String FileName = "countcross";

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static HashSet<String>[][] roads;

    public static void main (String [] args) throws IOException {

        // Input:
        BufferedReader f = new BufferedReader(new FileReader(FileName + ".in"));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        roads = new HashSet[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
            {
                roads[i][j] = new HashSet<>();
            }

        for (int i = 0; i < r; i++)
        {
            st = new StringTokenizer(f.readLine());
            int x1 = Integer.parseInt(st.nextToken()) - 1;
            int y1 = Integer.parseInt(st.nextToken()) - 1;
            int x2 = Integer.parseInt(st.nextToken()) - 1;
            int y2 = Integer.parseInt(st.nextToken()) - 1;
            roads[x1][y1].add(x2 + "|" + y2);
            roads[x2][y2].add(x1 + "|" + y1);
        }

        Point[] points = new Point[k];
        for (int i = 0; i < k; i++)
        {
            st = new StringTokenizer(f.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            points[i] = new Point(x, y);
        }

        int result = 0;
        for (int i = 0; i < k; i++)
        {
            boolean[][] visited = new boolean[n][n];
            dfs(visited, points[i].x, points[i].y, n);
            result += getCount(visited, points);
        }
        result = result / 2;

        // Output:
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(FileName + ".out")));
        out.println(result);
        out.close();
    }

    static void dfs (boolean[][] visited, int x, int y, int n) {

        if (x < 0 || x >= n || y < 0 || y >= n || visited[x][y]) {
            return;
        }

        visited[x][y] = true;

        HashSet<String> blocks = roads[x][y];
        for (int i = 0; i < dx.length; i++)
        {
            int nx = x + dx[i];
            int ny = y + dy[i];
            String key = nx + "|" + ny;

            if (!blocks.contains(key))
            {
                dfs(visited, nx, ny, n);
            }
        }
    }

    static int getCount (boolean[][] visited, Point[] points) {

        int count = 0;
        for (Point p : points)
        {
            if (!visited[p.x][p.y]) {
                count++;
            }
        }

        return count;
    }

    static class Point {

        public int x;
        public int y;

        public Point (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}