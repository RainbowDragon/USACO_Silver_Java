/**
 *      USACO 2022 - 02 - Problem 1 - Redistributing Gifts
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class RedistributingGifts {

    static ArrayList<Integer>[] graph;
    static Stack<Integer> comp;
    static int[][] wishlist, preflist;
    static boolean[] visited;
    static int[] dfs_num, dfs_low, results;
    static int count = 1;

    public static void main(String[] args) throws IOException {

        // Input:
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(f.readLine());
        graph = new ArrayList[n+1];
        dfs_num = new int[n+1];
        for (int i = 1; i <= n; i++)
        {
            graph[i] = new ArrayList<>();
            dfs_num[i] = -1;
        }

        visited = new boolean[n+1];
        dfs_low = new int[n+1];
        results = new int[n+1];

        wishlist = new int[n+1][n+1];
        preflist = new int[n+1][n+1];
        for (int i = 1; i <= n; i++)
        {
            StringTokenizer st = new StringTokenizer(f.readLine());
            boolean found = false;
            for (int j = 1; j <= n; j++)
            {
                int value = Integer.parseInt(st.nextToken());
                wishlist[i][j] = value;
                preflist[i][value] = j;
                if (value == i) {
                    found = true;
                    results[i] = j;
                }
                if (!found) {
                    graph[i].add(value);
                }
            }
        }

        comp = new Stack<>();
        for (int i = 1; i <= n; i++)
        {
            if (dfs_num[i] == -1) {
                dfs(i);
            }
        }

        // Output:
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        for (int i = 1; i <= n; i++)
        {
            out.println(wishlist[i][results[i]]);
        }
        out.close();
    }

    public static void dfs(int node) {

        visited[node] = true;
        dfs_low[node] = dfs_num[node] = count;
        count++;
        comp.add(node);

        for (int next : graph[node])
        {
            if (dfs_num[next] == -1) {
                dfs(next);
            }
            if (visited[next]) {
                dfs_low[node] = Math.min(dfs_low[node], dfs_low[next]);
            }
        }

        if (dfs_num[node] == dfs_low[node]) {

            ArrayList<Integer> circle = new ArrayList<>();
            while (!comp.isEmpty() && comp.peek() != node)
            {
                int cur = comp.peek();
                comp.pop();
                circle.add(cur);
            }
            comp.pop();
            circle.add(node);

            for (int first : circle)
            {
                visited[first] = false;
                for (int second : circle)
                {
                    if (first != second) {
                        results[first] = Math.min(results[first], preflist[first][second]);
                    }
                }
            }
        }
    }
}