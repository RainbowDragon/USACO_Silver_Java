/**
 *      USACO 2022 - 02 - Problem 3 - Email Filing
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class EmailFiling {

    static BufferedReader f;

    public static void main (String [] args) throws IOException {

        // Input:
        f = new BufferedReader(new InputStreamReader(System.in));

        // Output:
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        int t = Integer.parseInt(f.readLine());
        boolean[] results = new boolean[t];
        for (int i = 0; i < t; i++)
        {
            results[i] = solve();
        }

        for (int i = 0; i < t; i++)
        {
            if (results[i]) {
                out.println("YES");
            }
            else {
                out.println("NO");
            }
        }

        out.close();
    }

    public static boolean solve() throws IOException {

        StringTokenizer st = new StringTokenizer(f.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] counts = new int[m+1];
        int[] emails = new int[n];
        boolean[] filed = new boolean[n];

        st = new StringTokenizer(f.readLine());
        for (int i = 0; i < n; i++)
        {
            emails[i] = Integer.parseInt(st.nextToken());
            counts[emails[i]]++;
        }

        Stack<Integer> stack = new Stack<>();
        Deque<Integer> deque = new ArrayDeque<>();
        int first = 1;
        for (int i = 0; i < n; i++)
        {
            if (emails[i] <= first+k-1) {
                filed[i] = true;
                counts[emails[i]]--;

                if (counts[first] == 0) {
                    while (first < m && counts[first] == 0) {
                        first++;
                    }
                    Deque<Integer> temp = new ArrayDeque<>();
                    while (!deque.isEmpty()) {
                        int index = deque.poll();
                        if (emails[index] <= first+k-1) {
                            filed[index] = true;
                            counts[emails[index]]--;
                        }
                        else {
                            temp.add(index);
                        }
                    }
                    deque = temp;
                }
            }
            else {
                if (deque.size() == k) {
                    stack.push(deque.poll());
                }
                deque.add(i);
            }
        }

        while (first < m && counts[first] == 0) {
            first++;
        }

        while(!stack.empty() && deque.size() < k)
        {
            int top = stack.pop();

            if (emails[top] <= first+k-1) {
                filed[top] = true;
                counts[emails[top]]--;

                if (counts[first] == 0) {
                    while (first < m && counts[first] == 0) {
                        first++;
                    }
                    Deque<Integer> temp = new ArrayDeque<>();
                    while (!deque.isEmpty()) {
                        int index = deque.poll();
                        if (emails[index] <= first+k-1) {
                            filed[index] = true;
                            counts[emails[index]]--;
                        }
                        else {
                            temp.add(index);
                        }
                    }
                    deque = temp;
                }
            }
            else {
                deque.addFirst(top);
            }
        }

        System.out.println(6277 + " ==== " + emails[6277]);

        return deque.isEmpty() && stack.isEmpty();
    }
}