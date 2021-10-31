/**
 *      USACO 2015 - 12 - Problem 2 - High Card Wins
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class HighCardWins {

    public static String FileName = "highcard";

    public static void main (String [] args) throws IOException {

        // Input:
        BufferedReader f = new BufferedReader(new FileReader(FileName + ".in"));

        int n = Integer.parseInt(f.readLine());
        int N = 2*n;

        boolean[] hasCard = new boolean[N];

        for (int i = 0; i < n; i++)
        {
            int value = Integer.parseInt(f.readLine());
            hasCard[value-1] = true;
        }

        int[] bessie = new int[n];
        int[] elsie = new int[n];

        int bi = 0;
        int ei = 0;
        for (int i = 0; i < N; i++)
        {
            if (hasCard[i]) {
                elsie[ei] = i;
                ei++;
            } else {
                bessie[bi] = i;
                bi++;
            }
        }

        int result = 0;

        bi = 0;
        ei = 0;
        while (bi < n) {

            if (elsie[ei] < bessie[bi]) {
                ei++;
                result++;
            }
            bi++;
        }

        // Output:
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(FileName + ".out")));
        out.println(result);
        out.close();
    }
}