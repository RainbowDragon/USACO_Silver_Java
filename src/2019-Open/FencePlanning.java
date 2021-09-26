/**
 *      USACO 2019 - Open - Problem 3 - Fence Planning
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class FencePlanning {

    public static String FileName = "fenceplan";

    static ArrayList<Integer>[] graph;
    static Point[] points;
    static boolean[] visited;
    static int n, minX, maxX, minY, maxY;

    public static void main (String [] args) throws IOException {

        // Input:
        BufferedReader f = new BufferedReader(new FileReader(FileName + ".in"));

        StringTokenizer st = new StringTokenizer(f.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        visited = new boolean[n];
        graph = new ArrayList[n];
        for (int i = 0; i < n; i++)
        {
            graph[i] = new ArrayList<Integer>();
        }

        points = new Point[n];
        for (int i = 0; i < n; i++)
        {
            st = new StringTokenizer(f.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            points[i] = new Point(x, y);
        }

        for (int i = 0; i < m; i++)
        {
            st = new StringTokenizer(f.readLine());
            int s = Integer.parseInt(st.nextToken()) - 1;
            int t = Integer.parseInt(st.nextToken()) - 1;

            graph[s].add(t);
            graph[t].add(s);
        }

        int result = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++)
        {
            if (!visited[i]) {

                minX = Integer.MAX_VALUE;
                maxX = Integer.MIN_VALUE;
                minY = Integer.MAX_VALUE;
                maxY = Integer.MIN_VALUE;

                dfs(i);

                int perimeter = ((maxX - minX) + (maxY - minY)) * 2;

                result = Math.min(result, perimeter);
            }
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

        minX = Math.min(minX, points[i].x);
        maxX = Math.max(maxX, points[i].x);
        minY = Math.min(minY, points[i].y);
        maxY = Math.max(maxY, points[i].y);

        for (int j : graph[i])
        {
            if (!visited[j]) {
                dfs(j);
            }
        }
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