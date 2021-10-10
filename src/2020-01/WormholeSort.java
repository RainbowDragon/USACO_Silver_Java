/**
 *      USACO 2020 - 01 - Problem 3 - Wormhole Sort
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class WormholeSort {

    public static String FileName = "wormsort";

    public static int[] positions;
    public static ArrayList<Wormhole>[] wormholelist;

    public static void main (String [] args) throws IOException {

        // Input:
        BufferedReader f = new BufferedReader(new FileReader(FileName + ".in"));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        positions = new int[n];
        wormholelist = new ArrayList[n];
        st = new StringTokenizer(f.readLine());
        for (int i = 0; i < n; i++)
        {
            positions[i] = Integer.parseInt(st.nextToken()) - 1;
            wormholelist[i] = new ArrayList<Wormhole>();
        }

        int maxWeight = 0;
        for (int i = 0; i < m; i++)
        {
            st = new StringTokenizer(f.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            int weight = Integer.parseInt(st.nextToken());
            maxWeight = Math.max(maxWeight, weight);

            wormholelist[start].add(new Wormhole(end, weight));
            wormholelist[end].add(new Wormhole(start, weight));
        }

        int result = -1;

        if (!isSorted(n)) {
            int min = 1;
            int max = maxWeight;

            while (min != max) {
                int mid = (min + max + 1) / 2;

                if (isValid(n, mid)) {
                    min = mid;
                } else {
                    max = mid - 1;
                }
            }

            result = min;
        }

        // Output:
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(FileName + ".out")));
        out.println(result);
        out.close();
    }

    public static boolean isSorted(int n) {

        for (int i = 0; i < n; i++)
        {
            if (positions[i] != i) {
                return false;
            }
        }

        return true;
    }

    public static boolean isValid(int n, int weight) {

        int[] roots = new int[n];
        int index = 1;

        for (int i = 0; i < n; i++)
        {
            if (roots[i] == 0) {
                dfs(roots, i, index, weight);
                index++;
            }
        }

        for (int i = 0; i < n; i++)
        {
            if (roots[i] != roots[positions[i]]) {
                return false;
            }
        }

        return true;
    }

    public static void dfs(int[] roots, int position, int index, int weight) {

        roots[position] = index;

        for(Wormhole wh :  wormholelist[position])
        {
            if (wh.weight >= weight && roots[wh.dest] == 0) {
                dfs(roots, wh.dest, index, weight);
            }
        }
    }

    static class Wormhole {

        public int dest;
        public int weight;

        public Wormhole (int dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }
    }
}