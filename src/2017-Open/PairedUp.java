/**
 *      USACO 2017 - Open - Problem 1 - Paired Up
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class PairedUp {

    public static String FileName = "pairup";

    public static void main (String [] args) throws IOException {

        // Input:
        BufferedReader f = new BufferedReader(new FileReader(FileName + ".in"));

        int n = Integer.parseInt(f.readLine());

        Cow[] cows = new Cow[n];
        for (int i = 0; i < n; i++)
        {
            StringTokenizer st = new StringTokenizer(f.readLine());
            int count = Integer.parseInt(st.nextToken());
            int output = Integer.parseInt(st.nextToken());
            cows[i] = new Cow(count, output);
        }
        Arrays.sort(cows);

        int result = 0;
        int low = 0;
        int high = n - 1;

        while (low < high)
        {
            int sum = cows[low].output + cows[high].output;
            result = Math.max(result, sum);

            int count = Math.min(cows[low].count, cows[high].count);
            cows[low].count -= count;
            cows[high].count -= count;

            if (cows[low].count == 0) {
                low++;
            }
            if (cows[high].count == 0) {
                high--;
            }
        }

        // Output:
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(FileName + ".out")));
        out.println(result);
        out.close();
    }

    static class Cow implements Comparable<Cow> {

        public int count;
        public int output;

        public Cow (int count, int output) {
            this.count = count;
            this.output = output;
        }

        public int compareTo(Cow cow) {
            return this.output - cow.output;
        }
    }
}