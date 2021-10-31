/**
 *      USACO 2020 - Open - Problem 1 - Social Distancing
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class SocialDistancing {

    public static String FileName = "socdist";

    static ArrayList<Interval> intervals;

    public static void main (String [] args) throws IOException {

        // Input:
        BufferedReader f = new BufferedReader(new FileReader(FileName + ".in"));

        // Output:
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(FileName + ".out")));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        intervals = new ArrayList<Interval>();
        long min = 1;
        long max = 0;
        for (int i = 0; i < m; i++)
        {
            st = new StringTokenizer(f.readLine());
            long low = Long.parseLong(st.nextToken());
            long high = Long.parseLong(st.nextToken());
            intervals.add(new Interval(low, high));
            max = Math.max(max, high);
        }
        intervals.sort(new IntervalComparator());

        while (min != max) {
            long mid = (min + max + 1) / 2;

            if (isValid(n, mid)) {
                min = mid;
            } else {
                max = mid - 1;
            }
        }
        long result = min;

        out.println(result);

        out.close();
    }

    static boolean isValid(int n, long d) {

        int count = 0;
        long last = Long.MIN_VALUE;
        for (Interval interval : intervals)
        {
            while (Math.max(last+d, interval.low) <= interval.high)
            {
                last = Math.max(last+d, interval.low);
                count++;
            }

            if (count >= n) {
                break;
            }
        }

        return count >= n;
    }

    static class Interval {

        public long low;
        public long high;

        public Interval (long low, long high) {
            this.low = low;
            this.high = high;
        }
    }

    static class IntervalComparator implements Comparator<Interval> {

        public int compare(Interval i1, Interval i2) {
            return i1.low < i2.low ? -1 : 1;
        }
    }
}