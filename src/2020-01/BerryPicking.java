/**
 *      USACO 2020 - 01 - Problem 1 - Berry Picking
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class BerryPicking {

    public static String FileName = "berries";

    public static void main (String [] args) throws IOException {

        // Input:
        BufferedReader f = new BufferedReader(new FileReader(FileName + ".in"));

        // Output:
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(FileName + ".out")));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(f.readLine());
        int[] berries = new int[n];
        int maxBerry = 0;
        for (int i = 0; i < n; i++)
        {
            berries[i] = Integer.parseInt(st.nextToken());
            maxBerry = Math.max(maxBerry, berries[i]);
        }

        int result = 0;
        int[] rem = new int[n];
        for (int b = 1; b <= maxBerry; b++)
        {
            int num = 0;
            for (int i = 0; i < n; i++)
            {
                num += berries[i] / b;
                rem[i] = berries[i] % b;
            }

            if (num < k / 2) {
                continue;
            }

            if (num >= k) {
                result = Math.max(result, (k / 2) * b);
                continue;
            }

            Arrays.sort(rem);
            int cur = (num - k / 2) * b;
            int count = k - num;
            for (int i = 1; i <= count && i <= n; i++)
            {
                cur += rem[n - i];
            }

            result = Math.max(result, cur);
        }

        out.println(result);
        out.close();
    }
}