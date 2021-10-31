/**
 *      USACO 2018 - 01 - Problem 1 - Lifeguards
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class Lifeguards {

    public static String FileName = "lifeguards";

    public static void main (String [] args) throws IOException {

        // Input:
        BufferedReader f = new BufferedReader(new FileReader(FileName + ".in"));

        int n = Integer.parseInt(f.readLine());

        State[] states = new State[n*2];
        for (int i = 0; i < n; i++)
        {
            StringTokenizer st = new StringTokenizer(f.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            states[2*i] = new State(start, i, true);
            states[2*i+1] = new State(end, i, false);
        }
        Arrays.sort(states);

        int[] alone = new int[n];
        TreeSet<Integer> set = new TreeSet<>();
        int last = 0;
        int total = 0;
        for (State state : states)
        {
            int time = state.time - last;

            if (set.size() == 1) {
                alone[set.first()] += time;
            }

            if (!set.isEmpty()) {
                total += time;
            }

            if (state.isStart) {
                set.add(state.index);
            } else {
                set.remove(state.index);
            }

            last = state.time;
        }

        int result = 0;
        for (int out : alone)
        {
            result = Math.max(result, total - out);
        }

        // Output:
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(FileName + ".out")));
        out.println(result);
        out.close();
    }

    static class State implements Comparable<State> {

        public int time;
        public int index;
        public boolean isStart;

        public State (int time, int index, boolean isStart) {
            this.time = time;
            this.index = index;
            this.isStart = isStart;
        }

        public int compareTo(State state) {
            return this.time - state.time;
        }
    }
}