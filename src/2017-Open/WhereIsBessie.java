/**
 *      USACO 2017 - Open - Problem 3 - Where's Bessie?
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class WhereIsBessie {

    public static String FileName = "where";

    static int n;
    static char[][] image;
    static boolean[][] visited;
    static Vector<PCL> pcls;
    static int[] di = {-1, 0, 1, 0};
    static int[] dj = {0, -1, 0, 1};

    public static void main (String [] args) throws IOException {

        // Input:
        BufferedReader f = new BufferedReader(new FileReader(FileName + ".in"));

        int n = Integer.parseInt(f.readLine());
        image = new char[n][n];
        visited = new boolean[n][n];

        for (int i = 0; i < n; i++)
        {
            String s = f.readLine();
            for (int j = 0; j < n; j++)
            {
                image[i][j] = s.charAt(j);
            }
        }

        pcls = new Vector<PCL>();
        for (int i1 = 0; i1 < n; i1++)
            for (int i2 = i1; i2 < n; i2++)
                for (int j1 = 0; j1 < n; j1++)
                    for (int j2 = j1; j2 < n; j2++)
                    {
                        if (isPCL(i1, i2, j1, j2)) {
                            pcls.add(new PCL(i1, i2, j1, j2));
                        }
                    }

        int result = 0;
        for (int k = 0; k < pcls.size(); k++)
        {
            if (isMaxPCL(k)) {
                result++;
            }
        }

        // Output:
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(FileName + ".out")));
        out.println(result);
        out.close();
    }

    public static boolean isPCL (int i1, int i2, int j1, int j2) {

        for (int i = i1; i <= i2; i++)
            for (int j = j1; j <= j2; j++)
            {
                visited[i][j] = false;
            }

        int num_color = 0;
        int[] color_count = new int[26];

        for (int i = i1; i <= i2; i++)
            for (int j = j1; j <= j2; j++)
            {
                if (!visited[i][j]) {

                    int index = image[i][j] - 'A';
                    if (color_count[index] == 0) {
                        num_color++;
                    }
                    color_count[index]++;

                    dfs(i, j, image[i][j], i1, i2, j1, j2);
                }
            }

        if (num_color != 2) {
            return false;
        }

        boolean found_one = false;
        boolean found_many = false;
        for (int k = 0; k < 26; k++)
        {
            if (color_count[k] == 1) {
                found_one = true;
            }

            if (color_count[k] > 1) {
                found_many = true;
            }
        }

        return found_one && found_many;
    }

    public static void dfs (int i, int j, char c, int i1, int i2, int j1, int j2) {

        if (i < i1 || i > i2 || j < j1 || j > j2 || visited[i][j] || image[i][j] != c) {
            return;
        }

        visited[i][j] = true;

        for (int k = 0; k < di.length; k++)
        {
            int ni = i + di[k];
            int nj = j + dj[k];

            dfs(ni, nj, c, i1, i2, j1, j2);
        }
    }

    public static boolean isMaxPCL(int index) {

        for (int k = 0; k < pcls.size(); k++)
        {
            if (index != k & isPCLinPCL(index, k)) {
                return false;
            }
        }

        return true;
    }

    public static boolean isPCLinPCL(int index, int k) {

        PCL one = pcls.get(index);
        PCL two = pcls.get(k);

        return one.i1 >= two.i1 && one.i2 <= two.i2 && one.j1 >= two.j1 && one.j2 <= two.j2;
    }

    static class PCL {

        public int i1;
        public int i2;
        public int j1;
        public int j2;

        public PCL (int i1, int i2, int j1, int j2) {
            this.i1 = i1;
            this.i2 = i2;
            this.j1 = j1;
            this.j2 = j2;
        }
    }
}