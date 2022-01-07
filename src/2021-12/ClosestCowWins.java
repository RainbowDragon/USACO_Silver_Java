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
            long position = Long.parseLong(st.nextToken());
            long tastiness = Long.parseLong(st.nextToken());
            patches[i] = new Patch(position, tastiness);
        }
        Arrays.sort(patches);

        long[] cows = new long[M];
        for (int i = 0; i < M; i++)
        {
            cows[i] = Long.parseLong(f.readLine());
        }
        Arrays.sort(cows);

        ArrayList<Long> tastes = new ArrayList<>();

        int index = 0;
        long first = 0;
        while (index < K && patches[index].position < cows[0])
        {
            first += patches[index].tastiness;
            index++;
        }
        tastes.add(first);

        for (int i = 1; i < M; i++)
        {
            while (index < K && patches[index].position <= cows[i-1])
            {
                index++;
            }

            long sum = 0;
            long maxHalf = 0;
            long current = 0;

            int start = index;
            long distance = cows[i] - cows[i-1];

            while (index < K && patches[index].position < cows[i])
            {
                sum += patches[index].tastiness;
                current += patches[index].tastiness;

                while ((patches[index].position - patches[start].position)*2 >= distance)
                {
                    current -= patches[start].tastiness;
                    start++;
                }

                maxHalf = Math.max(maxHalf, current);
                index++;
            }

            tastes.add(maxHalf);
            tastes.add(sum - maxHalf);
        }

        while (index < K && patches[index].position <= cows[M-1])
        {
            index++;
        }
        long last = 0;
        while (index < K)
        {
            last += patches[index].tastiness;
            index++;
        }
        tastes.add(last);

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

        public long position;
        public long tastiness;

        public Patch (long position, long tastiness) {
            this.position = position;
            this.tastiness = tastiness;
        }

        public int compareTo(Patch patch) {
            return Long.compare(this.position, patch.position);
        }
    }
}