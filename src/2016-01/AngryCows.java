/**
 *      USACO 2016 - 01 - Problem 1 - Angry Cows
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class AngryCows {

    public static String FileName = "angry";

    public static void main (String [] args) throws IOException {

        // Input:
        BufferedReader f = new BufferedReader(new FileReader(FileName + ".in"));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] bales = new int[n];

        for (int i = 0; i < n; i++)
        {
            int value = Integer.parseInt(f.readLine());
            bales[i] = value;
        }

        Arrays.sort(bales);

        int left = 0;
        int right = (bales[n-1] + 1) / 2;

        while (left != right)
        {
            int mid = (left + right) / 2;

            int index = 0;
            int count = 0;

            while (index < n)
            {
                count++;
                int location = bales[index] + 2*mid;
                while (index < n && bales[index] <= location)
                {
                    index++;
                }
            }

            if (count <= k) {
                right = mid;
            }
            else {
                left = mid + 1;
            }
        }

        int result = left;

        // Output:
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(FileName + ".out")));
        out.println(result);
        out.close();
    }
}