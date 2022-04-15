/**
 *      USACO 2022 - Open - Problem 3 - COW Operations
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class COWOperations {

    public static void main (String [] args) throws IOException {

        // Input:
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));

        HashMap<Character, Integer> map = new HashMap<>();
        map.put('C', 0);
        map.put('O', 1);
        map.put('W', 2);

        String str = f.readLine();
        int n = str.length();
        int[][] prefixSum = new int[3][n+1];
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                prefixSum[j][i+1] = prefixSum[j][i];
            }
            char c = str.charAt(i);
            prefixSum[map.get(c)][i+1]++;
        }

        int q = Integer.parseInt(f.readLine());
        char[] results = new char[q];
        for (int i = 0; i < q; i++)
        {
            StringTokenizer st = new StringTokenizer(f.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            results[i] = solve(prefixSum, from, to);
        }

        // Output:
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        out.println(String.valueOf(results));
        out.close();
    }

    public static char solve (int[][] prefixSum, int from, int to) {

        boolean cEven = (((prefixSum[0][to] - prefixSum[0][from-1]) % 2) == 0);
        boolean oEven = (((prefixSum[1][to] - prefixSum[1][from-1]) % 2) == 0);
        boolean wEven = (((prefixSum[2][to] - prefixSum[2][from-1]) % 2) == 0);

        if ((cEven && !oEven && !wEven) || (!cEven && oEven && wEven)) {
            return 'Y';
        }
        else {
            return 'N';
        }
    }
}
