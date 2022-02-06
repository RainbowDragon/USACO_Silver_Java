/**
 *      USACO 2022 - 01 - Problem 2 - Cow Frisbee
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class CowFrisbee {

    public static void main(String[] args) throws IOException {

        // Input:
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));

        // Output:
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        int n = Integer.parseInt(f.readLine());
        long[] height = new long[n];

        StringTokenizer st = new StringTokenizer(f.readLine());
        for (int i = 0; i < n; i++) {
            height[i] = Long.parseLong(st.nextToken());
        }

        long result = 0;
        Stack<Integer> s = new Stack<>();
        for (int i = 0; i < n; i++)
        {
            while (!s.isEmpty() && height[s.peek()] <= height[i])
            {
                result += i - s.peek() + 1;
                s.pop();
            }

            if (!s.isEmpty()) {
                result += i - s.peek() + 1;
            }

            s.add(i);
        }

        out.println(result);

        out.close();
    }
}