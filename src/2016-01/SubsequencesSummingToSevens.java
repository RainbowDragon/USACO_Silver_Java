/**
 *      USACO 2016 - 02 - Problem 2 - Subsequences Summing to Sevens
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class SubsequencesSummingToSevens {

    public static String FileName = "div7";

    public static void main (String [] args) throws IOException {

        // Input:
        BufferedReader f = new BufferedReader(new FileReader(FileName + ".in"));

        int n = Integer.parseInt(f.readLine());
        int m = 7;

        int[] firstIndex = new int[m];
        int[] lastIndex = new int[m];

        for (int i = 0; i < m; i++)
        {
            firstIndex[i] = n;
        }

        int sum = 0;
        for (int i = 0; i < n; i++)
        {
            int value = Integer.parseInt(f.readLine());
            sum += value;
            sum %= m;

            if (firstIndex[sum] > i) {
                firstIndex[sum] = i;
            }
            lastIndex[sum] = i;
        }

        int result = 0;
        for (int i = 0; i < m; i++)
        {
            int length = lastIndex[i] - firstIndex[i];
            if (length > result) {
                result = length;
            }
        }

        // Output:
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(FileName + ".out")));
        out.println(result);
        out.close();
    }
}