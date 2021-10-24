/**
 *      USACO 2015 - 12 - Problem 3 - Breed Counting
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class BreedCounting {

    public static String FileName = "bcount";

    static int k = 3;   // the number of breeds

    public static void main (String [] args) throws IOException {

        // Input:
        BufferedReader f = new BufferedReader(new FileReader(FileName + ".in"));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        int[][] cdf = new int[k][n+1];

        for (int j = 0; j < k; j++)
        {
            cdf[j][0] = 0;
        }

        for (int i = 1; i <= n; i++)
        {
            int breed = Integer.parseInt(f.readLine());

            for (int j = 0; j < k; j++)
            {
                if (i == 0) {
                    cdf[j][i] = 0;
                }
                else {
                    cdf[j][i] = cdf[j][i-1];
                }
            }

            cdf[breed-1][i]++;
        }

        // Output:
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(FileName + ".out")));

        for (int i = 0; i < q; i++)
        {
            st = new StringTokenizer(f.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            for (int j = 0; j < k; j++)
            {
                // No extra space at the end of line.
                if (j > 0) {
                    out.print(" ");
                }

                out.print(cdf[j][end] - cdf[j][start-1]);
            }
            out.println();
        }

        out.close();
    }
}