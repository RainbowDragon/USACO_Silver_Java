/**
 *      USACO 2022 - 01 - Problem 3 - Cereal 2
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class Cereal2 {

    static ArrayList<Edge>[] graph;
    static int[] cereal;
    static int root;
    static int cycleEdge;
    static boolean[] hasCereal;
    static ArrayList<Integer> result;

    public static void main (String [] args) throws IOException {

        // Input:
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));

        // Output:
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        graph = new ArrayList[m+1];
        for (int i = 1; i <= m; i++)
        {
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i <= n; i++)
        {
            st = new StringTokenizer(f.readLine());
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());
            graph[first].add(new Edge(i, first, second, second));
            graph[second].add(new Edge(i, first, second, first));
        }

        cereal = new int[m+1];
        hasCereal = new boolean[n+1];
        result = new ArrayList<>();
        for (int i = 1; i <= m; i++)
        {
            if (cereal[i] == 0) {
                root = -1;
                cycleEdge = -1;

                dfs(i, -1);

                if (root == -1) {
                    assign(i);
                }
                else {
                    assign(root);
                }
            }
        }

        int count = 0;
        for (int i = 1; i <= n; i++)
        {
            if (!hasCereal[i]) {
                count++;
                result.add(i);
            }
        }

        out.println(count);

        for (int i : result)
        {
            out.println(i);
        }

        out.close();
    }

    static void dfs(int index, int parent) {

        cereal[index] = 1;

        for (Edge e : graph[index])
        {
            if (cereal[e.end] == 0) {
                dfs(e.end, index);
            }
            else {
                if (root == -1 && e.end != parent) {
                    root = e.first;
                    cycleEdge = e.index;
                    hasCereal[e.index] = true;
                    result.add(e.index);
                }
            }
        }
    }

    static void assign(int index) {

        cereal[index] = 2;

        for (Edge e : graph[index])
        {
            if (cereal[e.end] != 2 && e.index != cycleEdge) {
                hasCereal[e.index] = true;
                result.add(e.index);
                assign(e.end);
            }
        }
    }

    static class Edge {

        public int index;
        public int first;
        public int second;
        public int end;

        public Edge (int index, int first, int second, int end) {
            this.index = index;
            this.first = first;
            this.second = second;
            this.end = end;
        }
    }
}