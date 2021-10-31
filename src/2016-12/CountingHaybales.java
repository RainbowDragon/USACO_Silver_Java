/**
 *      USACO 2016 - 12 - Problem 1 - Counting Haybales
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class CountingHaybales {

    public static String FileName = "haybales";

    public static void main (String [] args) throws IOException {

        // Input:
        BufferedReader f = new BufferedReader(new FileReader(FileName + ".in"));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        int[] haybales = new int[n];
        st = new StringTokenizer(f.readLine());
        for (int i = 0; i < n; i++)
        {
            haybales[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(haybales);

        // Output:
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(FileName + ".out")));
        for (int i = 0; i < q; i++)
        {
            st = new StringTokenizer(f.readLine());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());
            int result = getCount(haybales, right) - getCount(haybales, left-1);
            out.println(result);
        }
        out.close();
    }

    static int getCount(int[] array, int value) {

        if (value < array[0]) {
            return 0;
        }

        if (value >= array[array.length-1]) {
            return array.length;
        }

        int left = 0;
        int right = array.length - 1;

        while (left < right) {
            int mid = (left + right) / 2;

            if (value < array[mid]) {
                right = mid;
            } else if (value > array[mid]) {
                left = mid + 1;
            } else {
                return mid + 1;
            }
        }

        return left;
    }
}