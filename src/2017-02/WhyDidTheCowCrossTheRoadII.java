/**
 *      USACO 2017 - 02 - Problem 2 - Why did the Cow Cross the Road II
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class WhyDidTheCowCrossTheRoadII {

    public static String FileName = "maxcross";

    public static void main (String [] args) throws IOException {

        // Input:
        BufferedReader f = new BufferedReader(new FileReader(FileName + ".in"));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        boolean[] signals = new boolean[n];
        for (int i = 0; i < b; i++)
        {
            int index = Integer.parseInt(f.readLine()) - 1;
            signals[index] = true;
        }

        int result = 0;
        for (int i = 0; i < k; i++)
        {
            if (signals[i]) {
                result++;
            }
        }

        int current = result;
        for (int i = k; i < n; i++)
        {
            if (signals[i]) {
                current++;
            }
            if (signals[i-k]) {
                current--;
            }

            if (current < result) {
                result = current;
            }
        }

        // Output:
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(FileName + ".out")));
        out.println(result);
        out.close();
    }
}