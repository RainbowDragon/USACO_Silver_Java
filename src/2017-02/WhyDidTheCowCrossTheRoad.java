/**
 *      USACO 2017 - 02 - Problem 1 - Why did the Cow Cross the Road
 *
 */

import java.io.*;
import java.lang.*;
import java.lang.reflect.Array;
import java.util.*;

public class WhyDidTheCowCrossTheRoad {

    public static String FileName = "helpcross";

    public static void main (String [] args) throws IOException {

        // Input:
        BufferedReader f = new BufferedReader(new FileReader(FileName + ".in"));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int c = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        int[] times = new int[c];
        for (int i = 0; i < c; i++)
        {
            times[i] = Integer.parseInt(f.readLine());
        }
        Arrays.sort(times);

        Interval[] intervals = new Interval[n];
        for (int i = 0; i < n; i++)
        {
            st = new StringTokenizer(f.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            intervals[i] = new Interval(start, end);
        }
        Arrays.sort(intervals);

        int result = 0;
        int index = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < c; i++)
        {
            while (index < n && intervals[index].start <= times[i])
            {
                pq.add(intervals[index].end);
                index++;
            }

            while (!pq.isEmpty() && pq.peek() < times[i])
            {
                pq.poll();
            }

            if (!pq.isEmpty()) {
                pq.poll();
                result++;
            }
        }

        // Output:
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(FileName + ".out")));
        out.println(result);
        out.close();
    }

    static class Interval implements Comparable<Interval> {

        public int start;
        public int end;

        public Interval (int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int compareTo(Interval interval) {
            if (this.start == interval.start) {
                return this.end - interval.end;
            } else {
                return this.start - interval.start;
            }
        }
    }
}