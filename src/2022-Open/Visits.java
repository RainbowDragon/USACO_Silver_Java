/**
 *      USACO 2022 - Open - Problem 1 - Visits
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class Visits {

    static int[] roots;

    public static void main (String [] args) throws IOException {

        // Input:
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(f.readLine());
        roots = new int[n+1];
        for (int i = 0; i <= n; i++)
        {
            roots[i] = i;
        }

        ArrayList<Edge> edges = new ArrayList<>();
        for (int i = 1; i <= n; i++)
        {
            StringTokenizer st = new StringTokenizer(f.readLine());
            int to = Integer.parseInt(st.nextToken());
            long moo = Long.parseLong(st.nextToken());
            edges.add(new Edge(i, to, moo));
        }

        Collections.sort(edges, Collections.reverseOrder());

        long result = 0;
        for (int i = 0; i < edges.size(); i++)
        {
            Edge edge = edges.get(i);
            if (union(edge.from, edge.to)) {
                result += edge.moo;
            }
        }

        // Output:
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        out.println(result);
        out.close();
    }

    static int find (int index) {

        int current = index;
        while (roots[current] != current) {
            current = roots[current];
        }
        roots[index] = current;

        return current;
    }

    static boolean union (int first, int second) {

        int root1 = find(first);
        int root2 = find(second);

        if (root1 != root2) {
            int root = Math.min(root1, root2);
            roots[root1] = root;
            roots[root2] = root;
            return true;
        }
        else {
            return false;
        }
    }

    static class Edge implements Comparable<Edge> {

        public int from;
        public int to;
        public long moo;

        public Edge (int from, int to, long moo) {
            this.from = from;
            this.to = to;
            this.moo = moo;
        }

        public int compareTo(Edge edge) {
            return Long.compare(this.moo, edge.moo);
        }
    }
}