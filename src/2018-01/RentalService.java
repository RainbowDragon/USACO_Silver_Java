/**
 *      USACO 2018 - 01 - Problem 2 - Rental Service
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class RentalService {

    public static String FileName = "rental";

    public static void main (String [] args) throws IOException {

        // Input:
        BufferedReader f = new BufferedReader(new FileReader(FileName + ".in"));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        int[] cows = new int[n];
        for (int i = 0; i < n; i++)
        {
            cows[i] = Integer.parseInt(f.readLine());
        }
        Arrays.sort(cows);

        Store[] stores = new Store[m];
        for (int i = 0; i < m; i++)
        {
            st = new StringTokenizer(f.readLine());
            int q = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            stores[i] = new Store(q, p);
        }
        Arrays.sort(stores);

        int[] rents = new int[r];
        for (int i = 0; i < r; i++)
        {
            rents[i] = Integer.parseInt(f.readLine());
        }
        Arrays.sort(rents);

        long[] rent_total = new long[n+1];
        rent_total[0] = 0;
        int index = r - 1;
        for (int i = 1; i <= n; i++)
        {
            if (index >= 0) {
                rent_total[i] = rent_total[i-1] + rents[index];
            }
            else {
                rent_total[i] = rent_total[i-1];
            }
            index--;
        }

        long[] sell_total = new long[n+1];
        sell_total[n] = 0;
        index = m - 1;
        for (int i = n-1; i >= 0; i--)
        {
            int amount = cows[i];
            sell_total[i] = sell_total[i+1];

            while (amount > 0 && index >= 0)
            {
                if (stores[index].gallon > amount) {
                    sell_total[i] += stores[index].price * amount;
                    stores[index].gallon -= amount;
                    amount = 0;
                }
                else {
                    sell_total[i] += stores[index].price * stores[index].gallon;
                    amount -= stores[index].gallon;
                    index--;
                }
            }
        }

        long result = 0;
        for (int i = 0; i <= n; i++)
        {
            result = Math.max(result, rent_total[i]+sell_total[i]);
        }

        // Output:
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(FileName + ".out")));
        out.println(result);
        out.close();
    }

    static class Store implements Comparable<Store> {

        public int gallon;
        public int price;

        public Store (int gallon, int price) {
            this.gallon = gallon;
            this.price = price;
        }

        public int compareTo(Store store) {
            return this.price - store.price;
        }
    }
}