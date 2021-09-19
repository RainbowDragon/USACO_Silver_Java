/**
 *      USACO 2021 - 02 - Problem 1 - Comfortable Cows
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class ComfortableCows {

    static int size = 3000;
    static int shift = 1000;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static boolean[][] cows = new boolean[size][size];
    static int[][] neighbors = new int[size][size];
    static int result = 0;

    public static void main (String [] args) throws IOException {

        // Input:
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));

        // Output:
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        int n = Integer.parseInt(f.readLine());

        for (int i = 0; i < n; i++)
        {
            StringTokenizer st = new StringTokenizer(f.readLine());
            int x = Integer.parseInt(st.nextToken()) + shift;
            int y = Integer.parseInt(st.nextToken()) + shift;

            result--;
            dfs(x, y);

            out.println(result);
        }

        out.close();
    }

    public static void dfs(int x, int y) {

        if (cows[x][y])
        {
            return;
        }
        cows[x][y] = true;
        result++;

        if (neighbors[x][y] == 3)
        {
            add(x, y);
        }

        for (int i = 0; i < dx.length; i++)
        {
            int newX = x + dx[i];
            int newY = y + dy[i];
            neighbors[newX][newY]++;

            if (cows[newX][newY] && neighbors[newX][newY] == 3)
            {
                add(newX, newY);
            }
        }
    }

    public static void add(int x, int y) {

        for (int i = 0; i < dx.length; i++)
        {
            int newX = x + dx[i];
            int newY = y + dy[i];
            if (!cows[newX][newY])
            {
                dfs(newX, newY);
            }
        }
    }
}