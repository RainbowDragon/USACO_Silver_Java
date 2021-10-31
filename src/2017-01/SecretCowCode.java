/**
 *      USACO 2017 - 01 - Problem 3 - Secret Cow Code
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class SecretCowCode {

    public static String FileName = "cowcode";

    public static void main (String [] args) throws IOException {

        // Input:
        BufferedReader f = new BufferedReader(new FileReader(FileName + ".in"));

        StringTokenizer st = new StringTokenizer(f.readLine());
        String word = st.nextToken();
        long n = Long.parseLong(st.nextToken());

        long len = word.length();

        while (len < n) {
            len = len * 2;
        }

        char result = getIndex(word, len, n);

        // Output:
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(FileName + ".out")));
        out.println(result);
        out.close();
    }

    static char getIndex(String word, long len, long index) {

        if (len == word.length()) {
            return word.charAt((int)index-1);
        }

        len = len / 2;

        if (index > len) {
            index = index - len;
            if (index == 1) {
                index = len;
            } else {
                index--;
            }

        }

        return getIndex(word, len, index);
    }
}