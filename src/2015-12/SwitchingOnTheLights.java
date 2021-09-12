/**
 *      USACO 2015 - 12 - Problem 1 - Switching on the Lights
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class SwitchingOnTheLights {

    public static String FileName = "lightson";

    static boolean[][] isOn;
    static boolean[][] visited;
    static ArrayList<Room>[][] switches;
    static int n, result;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main (String [] args) throws IOException {

        // Input:
        BufferedReader f = new BufferedReader(new FileReader(FileName + ".in"));

        StringTokenizer st = new StringTokenizer(f.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        switches = new ArrayList[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
            {
                switches[i][j] = new ArrayList<Room>();
            }

        for (int i = 0; i < m; i++)
        {
            st = new StringTokenizer(f.readLine());
            int sx = Integer.parseInt(st.nextToken()) - 1;
            int sy = Integer.parseInt(st.nextToken()) - 1;
            int tx = Integer.parseInt(st.nextToken()) - 1;
            int ty = Integer.parseInt(st.nextToken()) - 1;

            switches[sx][sy].add(new Room(tx, ty));
        }

        isOn = new boolean[n][n];
        visited = new boolean[n][n];

        isOn[0][0] = true;
        result = 1;
        dfs(0, 0);

        // Output:
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(FileName + ".out")));
        out.println(result);
        out.close();
    }

    static void dfs(int x, int y) {

        if (!isValidIndex(x, y) || visited[x][y] || !isOn[x][y]) {
            return;
        }

        visited[x][y] = true;

        // turn on the switches in the room
        for (Room room : switches[x][y]) {
            if (!isOn[room.x][room.y]) {
                isOn[room.x][room.y] = true;
                result++;
            }
            if (!visited[room.x][room.y] && canVisit(room)) {
                dfs(room.x, room.y);
            }
        }

        // check the neighbors
        for (int i = 0; i < dx.length; i++)
        {
            dfs(x + dx[i], y + dy[i]);
        }
    }

    static boolean canVisit(Room room) {

        for (int i = 0; i < dx.length; i++)
        {
            int nx = room.x + dx[i];
            int ny = room.y + dy[i];

            if (isValidIndex(nx, ny) && visited[nx][ny])
            {
                return true;
            }
        }

        return false;
    }

    static boolean isValidIndex(int x, int y) {
        return x >= 0 && y >= 0 && x < n && y < n;
    }

    static class Room {

        public int x;
        public int y;

        public Room (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}