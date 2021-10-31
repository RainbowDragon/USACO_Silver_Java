/**
 *      USACO 2017 - 01 - Problem 1 - Cow Dance Show
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class CowDanceShow {

    public static String FileName = "cowdance";

    public static void main (String [] args) throws IOException {

        // Input:
        BufferedReader f = new BufferedReader(new FileReader(FileName + ".in"));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int n = Integer.parseInt(st.nextToken());
        int tmax = Integer.parseInt(st.nextToken());

        int[] time = new int[n];

        for (int i = 0; i < n; i++)
        {
            time[i] = Integer.parseInt(f.readLine());
        }

        int min = 1;
        int max = n;
        while (min != max) {
            int mid = (min + max) / 2;

            if (isValid(time, mid, tmax)) {
                max = mid;
            } else {
                min = mid + 1;
            }
        }

        int result = min;

        // Output:
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(FileName + ".out")));
        out.println(result);
        out.close();
    }

    static boolean isValid(int[] time, int k, int tmax) {
        int t = 0;

        PriorityQueue<Integer> q = new PriorityQueue<Integer>();
        for (int i = 0; i < k; i++)
        {
            q.add(time[i]);
        }

        for (int i = k; i < time.length; i++)
        {
            t = q.peek() + time[i];
            q.poll();

            if (t > tmax) {
                return false;
            }

            q.add(t);
        }

        return true;
    }
}