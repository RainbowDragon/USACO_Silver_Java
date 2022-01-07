/**
 *      USACO 2021 - 12 - Problem 2 - Connecting Two Barns
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class ConnectingTwoBarns {

    static BufferedReader f;
    static ArrayList<Integer>[] graph;
    static int[] colors;

    public static void main (String [] args) throws IOException {

        // Input:
        f = new BufferedReader(new InputStreamReader(System.in));

        // Output:
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        int t = Integer.parseInt(f.readLine());

        long[] results = new long[t];

        for (int i = 0; i < t; i++)
        {
            results[i] = solve();
        }

        for (int i = 0; i < t; i++)
        {
            out.println(results[i]);
        }

        out.close();
    }

    public static long solve() throws IOException {

        StringTokenizer st = new StringTokenizer(f.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n];
        colors = new int[n];
        for (int i = 0; i < n; i++)
        {
            graph[i] = new ArrayList<>();
            colors[i] = -1;
        }

        for (int i = 0; i < m; i++)
        {
            st = new StringTokenizer(f.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            graph[start].add(end);
            graph[end].add(start);
        }

        dfs(0, 0);

        if (colors[n-1] == 0) {
            return 0;
        }

        dfs(n-1, 1);
        int c = 2;
        for (int i = 0; i < n; i++)
        {
            if (colors[i] == -1) {
                dfs(i, c);
                c++;
            }
        }

        long[] d2s = new long[n];
        for (int i = 0; i < n; i++)
        {
            d2s[i] = Long.MAX_VALUE;
        }
        int last = 200001;
        for (int i = 0; i < n; i++)
        {
            if (colors[i] == 0) {
                last = i;
            }

            d2s[i] = Math.min(d2s[i], distance(i, last));
        }
        last = -100001;
        for (int i = n-1; i >= 0; i--)
        {
            if (colors[i] == 0) {
                last = i;
            }

            d2s[i] = Math.min(d2s[i], distance(i, last));
        }

        long[] d2e = new long[n];
        for (int i = 0; i < n; i++)
        {
            d2e[i] = Long.MAX_VALUE;
        }
        last = 200001;
        for (int i = 0; i < n; i++)
        {
            if (colors[i] == 1) {
                last = i;
            }

            d2e[i] = Math.min(d2e[i], distance(i, last));
        }
        last = -100001;
        for (int i = n-1; i >= 0; i--)
        {
            if (colors[i] == 1) {
                last = i;
            }

            d2e[i] = Math.min(d2e[i], distance(i, last));
        }

        long[] c2s = new long[c];
        for (int i = 0; i < c; i++)
        {
            c2s[i] = Long.MAX_VALUE;
        }
        for (int i = 0; i < n; i++)
        {
            c2s[colors[i]] = Math.min(c2s[colors[i]], d2s[i]);
        }

        long[] c2e = new long[c];
        for (int i = 0; i < c; i++)
        {
            c2e[i] = Long.MAX_VALUE;
        }
        for (int i = 0; i < n; i++)
        {
            c2e[colors[i]] = Math.min(c2e[colors[i]], d2e[i]);
        }

        long result = Long.MAX_VALUE;
        for (int i = 0; i < c; i++)
        {
            result = Math.min(result, c2s[i]+c2e[i]);
        }

        return result;
    }

    public static void dfs(int node, int color) {

        if (colors[node] != -1) {
            return;
        }

        colors[node] = color;
        for (int i : graph[node])
        {
            dfs(i, color);
        }
    }

    public static long distance(int a, int b) {

        long result = (long)a - (long)b;
        return result * result;
    }
}