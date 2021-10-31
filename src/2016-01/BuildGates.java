/**
 *      USACO 2016 - 01 - Problem 3 - Build Gates
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class BuildGates {

    public static String FileName = "gates";

    public static HashSet<String> vertices;
    public static HashSet<String> edges;

    public static void main (String [] args) throws IOException {

        // Input:
        BufferedReader f = new BufferedReader(new FileReader(FileName + ".in"));

        int n = Integer.parseInt(f.readLine());
        String path = f.readLine();

        vertices = new HashSet<>();
        edges = new HashSet<>();

        int x = 0;
        int y = 0;
        int dx, dy;

        addVertex(x, y);

        for (int i = 0; i < n; i++)
        {
            dx = x;
            dy = y;

            char c = path.charAt(i);

            switch(c) {
                case 'N' :
                    y++;
                    break;
                case 'S' :
                    y--;
                    break;
                case 'E' :
                    x++;
                    break;
                case 'W' :
                    x--;
                    break;
            }

            addVertex(x, y);
            addEdge(x, y, dx, dy);
        }

        int result = edges.size() - vertices.size() + 1;

        // Output:
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(FileName + ".out")));
        out.println(result);
        out.close();
    }

    static void addVertex(int x, int y) {
        vertices.add(x + "-" + y);
    }

    static void addEdge(int x, int y, int dx, int dy) {

        if (x > dx) {
            addEdge(dx, dy, x, y);
            return;
        }

        if (y > dy) {
            addEdge(dx, dy, x, y);
            return;
        }

        edges.add(x + "-" + y + "|" + dx + "-" + dy);
        return;
    }
}