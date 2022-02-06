/**
 *      USACO 2022 - 01 - Problem 1 - Searching For Soulmates
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class SearchingForSoulmates {

    static BufferedReader f;

    public static void main (String [] args) throws IOException {

        // Input:
        f = new BufferedReader(new InputStreamReader(System.in));

        // Output:
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        int n = Integer.parseInt(f.readLine());
        long[] results = new long[n];
        for (int i = 0; i < n; i++)
        {
            results[i] = solve();
        }

        for (int i = 0; i < n; i++)
        {
            out.println(results[i]);
        }

        out.close();
    }

    public static long solve() throws IOException {

        StringTokenizer st = new StringTokenizer(f.readLine());
        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());

        if (a == b) {
            return 0;
        }

        long result = Long.MAX_VALUE;

        long p1 = 0;
        long p2 = 0;
        while (a != b)
        {
            if (a < b) {
                result = Math.min(result, p1+p2+b-a);
            }

            if (a > b) {
                if (a % 2 == 1) {
                    a++;
                }
                else {
                    a /= 2;
                }
                p1++;
            }
            else {
                if (b % 2 == 1) {
                    b--;
                }
                else {
                    b /= 2;
                }
                p2++;
            }
        }
        result = Math.min(result, p1+p2);

        return result;
    }
}