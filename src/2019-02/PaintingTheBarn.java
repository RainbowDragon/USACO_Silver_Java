/**
 *      USACO 2019 - 02 - Problem 2 - Painting the Barn
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class PaintingTheBarn {

    public static String FileName = "paintbarn";

    static int Width = 1000;

    public static void main (String [] args) throws IOException {

        // Input:
        BufferedReader f = new BufferedReader(new FileReader(FileName + ".in"));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] barns = new int[Width+1][Width+1];
        for (int i = 0; i < n; i++)
        {
            st = new StringTokenizer(f.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            barns[x1][y1]++;
            barns[x2][y2]++;
            barns[x1][y2]--;
            barns[x2][y1]--;
        }

        int result = 0;
        for (int i = 0; i < Width; i++)
            for (int j = 0; j < Width; j++)
            {
                if (i > 0) {
                    barns[i][j] += barns[i-1][j];
                }
                if (j > 0) {
                    barns[i][j] += barns[i][j-1];
                }
                if (i > 0 && j > 0) {
                    barns[i][j] -= barns[i-1][j-1];
                }

                if (barns[i][j] == k) {
                    result++;
                }
            }

        // Output:
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(FileName + ".out")));
        out.println(result);
        out.close();
    }
}