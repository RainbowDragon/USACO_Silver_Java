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

        int[][] prefixSum = new int[k][n+1];

        for (int j = 0; j < k; j++)
        {
            prefixSum[j][0] = 0;
        }

        for (int i = 1; i <= n; i++)
        {
            int breed = Integer.parseInt(f.readLine()) - 1;

            for (int j = 0; j < k; j++)
            {
                prefixSum[j][i] = prefixSum[j][i-1];
            }

            prefixSum[breed][i]++;
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

                out.print(prefixSum[j][end] - prefixSum[j][start-1]);
            }
            out.println();
        }

        out.close();
    }
}