/**
 *      USACO 2019 - 01 - Problem 2 - Icy Perimeter
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class IcyPerimeter {

    public static String FileName = "perimeter";

    static char[][] board;
    static int n, area, perimeter;

    public static void main (String [] args) throws IOException {

        // Input:
        BufferedReader f = new BufferedReader(new FileReader(FileName + ".in"));

        n = Integer.parseInt(f.readLine());
        board = new char[n][n];

        for (int i = 0; i < n; i++)
        {
            String str = f.readLine();
            for (int j = 0; j < n; j++)
            {
                board[i][j] = str.charAt(j);
            }
        }

        int maxArea = 0;
        int minPerimeter = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (board[i][j] == '#') {

                    area = perimeter = 0;

                    dfs(i, j);

                    if (area > maxArea) {
                        maxArea = area;
                        minPerimeter = perimeter;
                    } else if (area == maxArea) {
                        minPerimeter = Math.min(minPerimeter, perimeter);
                    }
                }
            }
        }

        String result = "";
        result += maxArea + " " + minPerimeter;

        // Output:
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(FileName + ".out")));
        out.println(result);
        out.close();
    }

    public static void dfs(int i, int j) {

        if (i < 0 || j < 0 || i >= n || j >= n || board[i][j] != '#') {
            return;
        }

        board[i][j] = '!';

        area++;

        if (i == 0 || board[i-1][j] == '.') {
            perimeter++;
        }
        if (i == n-1 || board[i+1][j] == '.') {
            perimeter++;
        }
        if (j == 0 || board[i][j-1] == '.') {
            perimeter++;
        }
        if (j == n-1 || board[i][j+1] == '.') {
            perimeter++;
        }

        dfs(i-1, j);
        dfs(i+1, j);
        dfs(i, j-1);
        dfs(i, j+1);
    }
}