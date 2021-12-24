/**
 *      USACO 2021 - 12 - Problem 3 - Convoluted Intervals
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class ConvolutedIntervals {

    public static void main(String[] args) throws IOException {

        // Input:
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));

        // Output:
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        long[] startCount = new long[m+1];
        long[] endCount = new long[m+1];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(f.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            startCount[start]++;
            endCount[end]++;
        }

        int M = 2*m + 1;
        long[] startPrefixSum = new long[M];
        long[] endPrefixSum = new long[M];
        for (int i = 0; i <= m; i++)
        {
            for (int j = 0; j <= m; j++)
            {
                startPrefixSum[i+j] += startCount[i] * startCount[j];
                endPrefixSum[i+j] += endCount[i] * endCount[j];
            }
        }

        long[] prefixSum = new long[M+1];
        for (int i = 0; i < M; i++)
        {
            prefixSum[i] += startPrefixSum[i];
            prefixSum[i+1] -= endPrefixSum[i];
        }

        long[] results = new long[M];
        results[0] = prefixSum[0];
        for (int i = 1; i < M; i++)
        {
            results[i] = results[i-1] + prefixSum[i];
        }

        for (int i = 0; i < M; i++)
        {
            out.println(results[i]);
        }

        out.close();
    }
}