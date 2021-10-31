/**
 *      USACO 2016 - Open - Problem 3 - Closing the Farm
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class ClosingTheFarm {

    public static String FileName = "closing";

    static ArrayList<Integer>[] graph;
    static boolean[] opened, visited, result;
    static int[] order;
    static int n;

    public static void main (String [] args) throws IOException {

        // Input:
        BufferedReader f = new BufferedReader(new FileReader(FileName + ".in"));

        StringTokenizer st = new StringTokenizer(f.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        opened = new boolean[n];
        graph = new ArrayList[n];
        for (int i = 0; i < n; i++)
        {
            graph[i] = new ArrayList<Integer>();
            opened[i] = true;
        }

        for (int i = 0; i < m; i++)
        {
            st = new StringTokenizer(f.readLine());
            int s = Integer.parseInt(st.nextToken()) - 1;
            int t = Integer.parseInt(st.nextToken()) - 1;

            graph[s].add(t);
            graph[t].add(s);
        }

        order = new int[n];
        for (int i = 0; i < n; i++)
        {
            order[i] = Integer.parseInt(f.readLine()) - 1;
        }

        result = new boolean[n];
        for (int k = 0; k < n; k++)
        {
            visited = new boolean[n];

            for (int i = 0; i < n; i++)
            {
                if (opened[i]) {
                    dfs(i);
                    break;
                }
            }

            boolean connected = true;
            for (int i = 0; i < n; i++)
            {
                if (opened[i] && !visited[i])
                {
                    connected = false;
                    break;
                }
            }

            result[k] = connected;

            opened[order[k]] = false;
        }

        // Output:
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(FileName + ".out")));
        for (int i = 0; i < n; i++)
        {
            if (result[i]) {
                out.println("YES");
            } else {
                out.println("NO");
            }
        }
        out.close();
    }

    public static void dfs(int i) {

        if (visited[i] || !opened[i]) {
            return;
        }

        visited[i] = true;

        for (int j : graph[i])
        {
            if (!visited[j] && opened[j]) {
                dfs(j);
            }
        }
    }
}