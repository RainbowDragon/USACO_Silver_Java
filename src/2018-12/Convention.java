/**
 *      USACO 2018 - 12 - Problem 1 - Convention
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class Convention {

    public static String FileName = "convention";

    public static int n, m, c, result;
    public static int[] times;
    public static boolean[][] visited;

    public static void main (String [] args) throws IOException {

        // Input:
        BufferedReader f = new BufferedReader(new FileReader(FileName + ".in"));

        StringTokenizer st = new StringTokenizer(f.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        times = new int[n];
        st = new StringTokenizer(f.readLine());
        for (int i = 0; i < n; i++)
        {
            times[i] = Integer.parseInt(st.nextToken());;
        }
        Arrays.sort(times);

        int min = 1;
        int max = times[n-1];
        while (min != max) {
            int mid = (min + max) / 2;

            if (isValid(mid)) {
                max = mid;
            } else {
                min = mid + 1;
            }
        }
        result = min;

        // Output:
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(FileName + ".out")));
        out.println(result);
        out.close();
    }

    static boolean isValid(int wait) {

        int start = times[0];
        int cc = 1;
        int cb = 1;

        for (int i = 1; i < n; i++)
        {
            if (cc < c && (times[i] - start) <= wait) {
                cc++;
            } else {
                start = times[i];
                cc = 1;
                cb++;
            }

            if (cb > m) {
                return false;
            }
        }

        return true;
    }
}