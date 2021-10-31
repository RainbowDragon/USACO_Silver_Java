/**
 *      USACO 2016 - 02 - Problem 1 - Circular Barn
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class CircularBarn {

    public static String FileName = "cbarn";

    public static void main (String [] args) throws IOException {

        // Input:
        BufferedReader f = new BufferedReader(new FileReader(FileName + ".in"));

        int n = Integer.parseInt(f.readLine());

        int[] barns = new int[n];

        // find the start index
        int min_index = 0;
        int cur_min = Integer.MAX_VALUE;
        int cur_sum = 0;
        for (int i = 0; i < n; i++)
        {
            barns[i] = Integer.parseInt(f.readLine());
            cur_sum += barns[i] - 1;

            if (cur_sum < cur_min) {
                cur_min = cur_sum;
                min_index = i;
            }
        }
        int index = (min_index + 1) % n;

        // calculate the energy
        long result = 0;
        Queue<State> q = new LinkedList<>();
        for (int j = 0; j < n; j++)
        {
            if (barns[index] > 0) {
                q.add(new State(j, barns[index]));
            }

            State front = q.peek();
            result += (j - front.index)*(j - front.index);
            front.count--;

            if (front.count == 0) {
                q.poll();
            }

            index++;
            index = index % n;
        }

        // Output:
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(FileName + ".out")));
        out.println(result);
        out.close();
    }

    static class State {

        public int index;
        public int count;

        public State (int index, int count) {
            this.index = index;
            this.count = count;
        }
    }
}