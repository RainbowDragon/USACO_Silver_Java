/**
 *      USACO 2022 - Open - Problem 2 - Subset Equality
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class SubsetEquality {

    public static void main (String [] args) throws IOException {

        // Input:
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));

        String strS = f.readLine();
        String strT = f.readLine();

        int[] countS = new int[26];
        for (int i = 0; i < strS.length(); i++)
        {
            countS[strS.charAt(i)-'a']++;
        }
        int[] countT = new int[26];
        for (int i = 0; i < strT.length(); i++)
        {
            countT[strT.charAt(i)-'a']++;
        }

        boolean[][] pairWorks = new boolean[26][26];
        for (int i = 0; i < 26; i++)
            for (int j = i+1; j < 26; j++)
            {
                boolean works = true;
                int ps = 0;
                int pt = 0;

                while (true)
                {
                    while (ps < strS.length() && strS.charAt(ps)-'a' != i && strS.charAt(ps)-'a' != j)
                    {
                        ps++;
                    }
                    while (pt < strT.length() && strT.charAt(pt)-'a' != i && strT.charAt(pt)-'a' != j)
                    {
                        pt++;
                    }

                    if (ps >= strS.length() && pt >= strT.length()) {
                        break;
                    }

                    if (ps >= strS.length() || pt >= strT.length()) {
                        works = false;
                        break;
                    }

                    if (strS.charAt(ps) != strT.charAt(pt)) {
                        works = false;
                        break;
                    }

                    ps++;
                    pt++;
                }

                pairWorks[i][j] = works;
            }

        int q = Integer.parseInt(f.readLine());
        char[] results = new char[q];
        for (int i = 0; i < q; i++)
        {
            String query = f.readLine();
            results[i] = solve(countS, countT, pairWorks, query);
        }

        // Output:
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        out.println(String.valueOf(results));
        out.close();
    }

    public static char solve (int[] countS, int[] countT, boolean[][] pairWorks, String query) {

        boolean works = true;
        int n = query.length();

        for (int i = 0; i < n; i++)
        {
            char c = query.charAt(i);
            if (countS[c-'a'] != countT[c-'a']) {
                works = false;
                break;
            }
        }

        if (works) {
            for (int i = 0; i < n; i++)
                for (int j = i+1; j < n; j++)
                {
                    if (!pairWorks[query.charAt(i)-'a'][query.charAt(j)-'a']) {
                        works = false;
                        break;
                    }
                }
        }

        if (works) {
            return 'Y';
        }
        else {
            return 'N';
        }
    }
}
