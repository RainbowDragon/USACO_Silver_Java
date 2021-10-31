/**
 *      USACO 2016 - 12 - Problem 2 - Cities and States
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class CitiesAndStates {

    public static String FileName = "citystate";

    public static void main (String [] args) throws IOException {

        // Input:
        BufferedReader f = new BufferedReader(new FileReader(FileName + ".in"));

        int n = Integer.parseInt(f.readLine());
        HashMap<String, Long> counts = new HashMap<String, Long>();

        for (int i = 0; i < n; i++)
        {
            StringTokenizer st = new StringTokenizer(f.readLine());
            String city = st.nextToken().substring(0,2);
            String state = st.nextToken();

            if (!city.equals(state)) {
                String key = city + state;
                if (!counts.containsKey(key)) {
                    counts.put(key, 0L);
                }
                counts.put(key, counts.get(key) + 1);
            }
        }

        long result = 0;
        for (String key : counts.keySet())
        {
            String other = key.substring(2,4) + key.substring(0,2);
            if (counts.containsKey(other)) {
                result += counts.get(key) * counts.get(other);
            }
        }
        result = result / 2;

        // Output:
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(FileName + ".out")));
        out.println(result);
        out.close();
    }
}