/**
 *      USACO 2021 - 12 - Problem 2 - Connecting Two Barns
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class ConnectingTwoBarns {

    static BufferedReader f;

    public static void main (String [] args) throws IOException {

        // Input:
        f = new BufferedReader(new InputStreamReader(System.in));

        // Output:
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        int t = Integer.parseInt(f.readLine());

        long[] results = new long[t];

        for (int i = 0; i < t; i++)
        {
            results[i] = solve();
        }

        for (int i = 0; i < t; i++)
        {
            out.println(results[i]);
        }

        out.close();
    }

    public static long solve() throws IOException {

        StringTokenizer st = new StringTokenizer(f.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] states = new int[n];
        for (int i = 0; i < n; i++)
        {
            states[i] = i;
        }

        for (int i = 0; i < m; i++)
        {
            st = new StringTokenizer(f.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            union(states, start, end);
        }

        for (int i = 0; i < n; i++)
        {
            states[i] = find(states, i);
        }

        int startState = states[0];
        int endState = states[n-1];
        if (startState == endState) {
            return 0;
        }

        ArrayList<Integer> starts = new ArrayList<>();
        ArrayList<Integer> ends = new ArrayList<>();
        ArrayList<Integer> middles = new ArrayList<>();

        for (int i = 0; i < n; i++)
        {
            if (states[i] == startState) {
                starts.add(i);
            }
            else if (states[i] == endState) {
                ends.add(i);
            }
            else {
                middles.add(i);
            }
        }

        long result = getShortestDistance(starts, ends);

        HashMap<Integer, Long> startDistance = new HashMap<>();
        HashMap<Integer, Long> endDistance = new HashMap<>();

        getEndDistance(starts, middles, states, startDistance);
        getEndDistance(ends, middles, states, endDistance);

        for (Integer key : startDistance.keySet())
        {
            if (key != startState && key != endState) {
                result = Math.min(result, startDistance.get(key)+endDistance.get(key));
            }
        }

        return result;
    }

    public static int find(int[] states, int index) {

        while (index != states[index])
        {
            index = states[index];
        }

        return index;
    }

    public static void union(int[] states, int index1, int index2) {

        int root1 = find(states, index1);
        int root2 = find(states, index2);

        if (root1 != root2) {
            states[root1] = states[root2] = Math.min(root1, root2);
        }
    }

    public static long getShortestDistance(ArrayList<Integer> list1, ArrayList<Integer> list2) {

        long result = distance(list1.get(0), list2.get(0));
        int p1 = 0;
        int p2 = 0;

        while (p1 < list1.size() && p2 < list2.size())
        {
            long current = distance(list1.get(p1), list2.get(p2));
            result = Math.min(result, current);

            if (list1.get(p1) < list2.get(p2)) {
                p1++;
            }
            else {
                p2++;
            }
        }

        return result;
    }

    public static void getEndDistance(ArrayList<Integer> oneEnd, ArrayList<Integer> middle, int[] states, HashMap<Integer, Long> endDistance) {

        int index = 0;
        for (int i = 0; i < middle.size(); i++)
        {
            while (index < oneEnd.size() && oneEnd.get(index) < middle.get(i))
            {
                index++;
            }

            if (index == oneEnd.size()) {
                index--;
            }

            long current = distance(oneEnd.get(index), middle.get(i));
            if (index > 0) {
                current = Math.min(current, distance(oneEnd.get(index-1), middle.get(i)));
            }

            int key = states[middle.get(i)];
            if (endDistance.containsKey(key)) {
                endDistance.put(key, Math.min(endDistance.get(key), current));
            }
            else {
                endDistance.put(key, current);
            }
        }
    }

    public static long distance(int a, int b) {

        long result = a - b;
        return result * result;
    }
}