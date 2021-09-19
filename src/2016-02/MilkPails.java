/**
 *      USACO 2016 - 02 - Problem 3 - Milk Pails
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class MilkPails {

    public static String FileName = "pails";

    public static void main (String [] args) throws IOException {

        // Input:
        BufferedReader f = new BufferedReader(new FileReader(FileName + ".in"));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        boolean[][] visited = new boolean[x+1][y+1];

        Queue<State> q = new LinkedList<>();
        q.add(new State(0,0,0));
        visited[0][0] = true;

        int result = m;
        boolean isDone = false;

        while (!q.isEmpty() && !isDone)
        {
            State p = q.peek();
            q.remove();
            int level = p.level + 1;

            result = Math.min(result, Math.abs(p.x+p.y-m));
            if (result == 0) {
                isDone = true;
            }

            // fill x
            if (!visited[x][p.y] && level <= k) {
                q.add(new State(x, p.y, level));
                visited[x][p.y] = true;
            }

            // fill y
            if (!visited[p.x][y] && level <= k) {
                q.add(new State(p.x, y, level));
                visited[p.x][y] = true;
            }

            // empty x
            if (!visited[0][p.y] && level <= k) {
                q.add(new State(0, p.y, level));
                visited[0][p.y] = true;
            }

            // empty y
            if (!visited[p.x][0] && level <= k) {
                q.add(new State(p.x, 0, level));
                visited[p.x][0] = true;
            }

            // x -> y
            int x2y = Math.min(p.x, y-p.y);
            if (!visited[p.x-x2y][p.y+x2y] && level <= k) {
                q.add(new State(p.x-x2y, p.y+x2y, level));
                visited[p.x-x2y][p.y+x2y] = true;
            }

            // y -> x
            int y2x = Math.min(x-p.x, p.y);
            if (!visited[p.x+y2x][p.y-y2x] && level <= k) {
                q.add(new State(p.x+y2x, p.y-y2x, level));
                visited[p.x+y2x][p.y-y2x] = true;
            }
        }

        // Output:
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(FileName + ".out")));
        out.println(result);
        out.close();
    }

    static class State {

        public int x;
        public int y;
        public int level;

        public State (int x, int y, int l) {
            this.x = x;
            this.y = y;
            this.level = l;
        }
    }
}