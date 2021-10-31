/**
 *      USACO 2019 - 01 - Problem 3 - Mountain View
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class MountainView {

    public static String FileName = "mountains";

    public static void main (String [] args) throws IOException {

        // Input:
        BufferedReader f = new BufferedReader(new FileReader(FileName + ".in"));

        int n = Integer.parseInt(f.readLine());
        Mountain[] mountains = new Mountain[n];
        for (int i = 0; i < n; i++)
        {
            StringTokenizer st = new StringTokenizer(f.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            mountains[i] = new Mountain(x-y, x+y);
        }
        Arrays.sort(mountains);

        int result = 0;
        int rightend = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++)
        {
            if (mountains[i].right > rightend) {
                rightend = mountains[i].right;
                result++;
            }
        }

        // Output:
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(FileName + ".out")));
        out.println(result);
        out.close();
    }

    static class Mountain implements Comparable<Mountain> {

        public int left;
        public int right;

        public Mountain (int left, int right) {
            this.left = left;
            this.right = right;
        }

        public int compareTo(Mountain m) {
            if (this.left != m.left) {
                return this.left - m.left;
            }
            else {
                return m.right - this.right;
            }
        }
    }
}