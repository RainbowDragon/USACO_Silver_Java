/**
 *      USACO 2016 - Open - Problem 2 - Diamond Collector
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class DiamondCollector {

    public static String FileName = "diamond";

    public static void main (String [] args) throws IOException {

        // Input:
        BufferedReader f = new BufferedReader(new FileReader(FileName + ".in"));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] diamonds = new int[n];

        for (int i = 0; i < n; i++)
        {
            diamonds[i] = Integer.parseInt(f.readLine());
        }
        Arrays.sort(diamonds);

        int[] counts = new int[n];
        int index = 0;
        int count = 0;
        for (int i = 0; i < n; i++)
        {
            int limit = diamonds[i] + k;
            while (index < n && diamonds[index] <= limit)
            {
                count++;
                index++;
            }

            counts[i] = count;
            count--;
        }

        Stack<Status> stack = new Stack<Status>();

        int last = 0;
        for (int i = n-1; i >= 0; i--)
        {
            if (counts[i] > last) {
                stack.push(new Status(counts[i], diamonds[i]));
                last = counts[i];
            }
        }

        int result = 0;
        for (int i = 0; i < n; i++)
        {
            int limit = diamonds[i] + k;
            while (!stack.empty() && stack.peek().size <= limit)
            {
                stack.pop();
            }

            int second = stack.empty() ? 0 : stack.peek().count;

            result = Math.max(result, counts[i]+second);
        }

        // Output:
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(FileName + ".out")));
        out.println(result);
        out.close();
    }

    static class Status {

        public int count;
        public int size;

        public Status (int count, int size) {
            this.count = count;
            this.size = size;
        }
    }
}