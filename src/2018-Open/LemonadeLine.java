/**
 *      USACO 2018 - Open - Problem 2 - Lemonade Line
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class LemonadeLine {

    public static String FileName = "lemonade";

    public static void main (String [] args) throws IOException {

        // Input:
        BufferedReader f = new BufferedReader(new FileReader(FileName + ".in"));

        int n = Integer.parseInt(f.readLine());
        int[] waits = new int[n];

        StringTokenizer st = new StringTokenizer(f.readLine());
        for (int i = 0; i < n; i++)
        {
            waits[i] = Integer.parseInt(st.nextToken());;
        }
        Arrays.sort(waits);

        int result = 0;
        for (int i = n - 1; i >= 0; i--)
        {
            if (waits[i] >= result) {
                result++;
            }
            else {
                break;
            }
        }

        // Output:
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(FileName + ".out")));
        out.println(result);
        out.close();
    }
}