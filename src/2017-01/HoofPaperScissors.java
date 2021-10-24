/**
 *      USACO 2017 - 01 - Problem 2 - Hoof, Paper, Scissors
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class HoofPaperScissors {

    public static String FileName = "hps";

    public static void main (String [] args) throws IOException {

        // Input:
        BufferedReader f = new BufferedReader(new FileReader(FileName + ".in"));

        int n = Integer.parseInt(f.readLine());
        String[] hands = new String[n];
        int[][] cdf = new int[n][3];
        int[][] rcdf = new int[n][3];

        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("H", 0);
        map.put("P", 1);
        map.put("S", 2);

        for (int i = 0; i < n; i++)
        {
            hands[i] = f.readLine();

            for (int j = 0; j < 3; j++)
            {
                if (i > 0) {
                    cdf[i][j] = cdf[i-1][j];
                }
                else {
                    cdf[i][j] = 0;
                }
            }

            cdf[i][map.get(hands[i])]++;
        }

        for (int i = n-1; i >= 0; i--)
        {
            for (int j = 0; j < 3; j++)
            {
                if (i < n-1) {
                    rcdf[i][j] = rcdf[i+1][j];
                }
                else {
                    rcdf[i][j] = 0;
                }
            }

            rcdf[i][map.get(hands[i])]++;
        }

        int result = 0;

        for (int i = 0; i < n-1; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                for (int k = 0; k < 3; k++)
                {
                    int cur = cdf[i][j] + rcdf[i+1][k];
                    if (cur > result)
                    {
                        result = cur;
                    }
                }
            }
        }

        // Output:
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(FileName + ".out")));
        out.println(result);
        out.close();
    }
}