/**
 *      USACO 2021 - 12 - Problem 1 - Closest Cow Wins
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class ClosestCowWins {

    public static void main (String [] args) throws IOException {

        // Input:
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));

        // Output:
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int K = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        Patch[] patches = new Patch[K];
        for (int i = 0; i < K; i++)
        {
            st = new StringTokenizer(f.readLine());
            int position = Integer.parseInt(st.nextToken());
            int tastiness = Integer.parseInt(st.nextToken());
            patches[i] = new Patch(position, tastiness);
        }
        Arrays.sort(patches);

        int[] cows = new int[M];
        for (int i = 0; i < M; i++)
        {
            cows[i] = Integer.parseInt(f.readLine());
        }
        Arrays.sort(cows);

        Interval[] intervals = new Interval[K];
        int index = 0;
        for (int i = 0; i < K; i++)
        {
            while (index < M && cows[index] < patches[i].position)
            {
                index++;
            }

            if (index == M) {
                index--;
            }

            int distance = Math.abs(cows[index] - patches[i].position);
            if (index > 0) {
                distance = Math.min(distance, Math.abs(cows[index-1] - patches[i].position));
            }

            intervals[i] = new Interval(patches[i].position - distance, patches[i].position + distance);
        }

        ArrayList<Long> tastes = new ArrayList<>();
        index = 0;
        while (index < K)
        {
            long current = 0;
            int end = intervals[index].end;
            while (index < K && intervals[index].start < end)
            {
                current += patches[index].tastiness;
                index++;
            }

            tastes.add(current);
        }
        Collections.sort(tastes, Collections.reverseOrder());

        long result = 0;
        for (int i = 0; i < N; i++)
        {
            result += tastes.get(i);
        }

        out.println(result);

        out.close();

    }

    static class Patch implements Comparable<Patch> {

        public int position;
        public int tastiness;

        public Patch (int position, int tastiness) {
            this.position = position;
            this.tastiness = tastiness;
        }

        public int compareTo(Patch patch) {
            return this.position - patch.position;
        }
    }

    static class Interval {

        public int start;
        public int end;

        public Interval (int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}