
// package src;

import java.io.*;
import java.util.*;

public class Lab2 {
    public static String pureMain(String[] commands) {

        // Comparator<Bid> comp = new BidCompare();
        PriorityQueue<Bid> sell_pq = new PriorityQueue<Bid>(   new SellComparator());
        PriorityQueue<Bid> buy_pq = new PriorityQueue<Bid>(new BuyComparator()  );

        StringBuilder sb = new StringBuilder();

       

        for (int line_no = 0; line_no < commands.length; line_no++) {
            String line = commands[line_no];

            if (line.equals("")) {

                continue;
            }

            String[] parts = line.split("\\s+");
            if (parts.length != 3 && parts.length != 4)
                throw new RuntimeException("line " + line_no + ": " + parts.length + " words");
            String name = parts[0];
            if (name.charAt(0) == '\0')
                throw new RuntimeException("line " + line_no + ": invalid name");
            String action = parts[1];
            int price;
            try {
                price = Integer.parseInt(parts[2]);
            } catch (NumberFormatException e) {
                throw new RuntimeException("line " + line_no + ": invalid price");
            }

            if (action.equals("K")) {
                buy_pq.add(new Bid(name, price));
            } else if (action.equals("S")) {
                sell_pq.add(new Bid(name, price));
            } else if (action.equals("NK")) {

                int newPrice;
                try {
                    newPrice = Integer.parseInt(parts[3]);
                } catch (NumberFormatException e) {
                    throw new RuntimeException("line " + line_no + ": invalid price");
                }

                Bid oldBid = new Bid(name, price);
                Bid newBid = new Bid(name, newPrice);

                buy_pq.update(oldBid, newBid);

            } else if (action.equals("NS")) {

                int newPrice;
                try {
                    newPrice = Integer.parseInt(parts[3]);
                } catch (NumberFormatException e) {
                    throw new RuntimeException("line " + line_no + ": invalid price");
                }   

                Bid oldBid = new Bid(name, price);
                Bid newBid = new Bid(name, newPrice);

                sell_pq.update(oldBid, newBid);
            } else {
                throw new RuntimeException("line " + line_no + ": invalid action");
            }
            if (sell_pq.size() == 0 || buy_pq.size() == 0)
                continue;

            
            while (sell_pq.size() != 0 && buy_pq.size() != 0 && sell_pq.minimum().bid <= buy_pq.minimum().bid) {
                Bid min = sell_pq.minimum();
                Bid max = buy_pq.minimum();
                sell_pq.deleteMinimum();
                buy_pq.deleteMinimum();
                sb.append(min.name + " buys a share from " + max.name + " for " + max.bid + "\n");

            }
        } // End of for-loop
          // compare the bids of highest priority from each of
          // each priority queues.
          // if the lowest seller price is lower than or equal to
          // the highest buyer price, then remove one bid from
          // each priority queue and add a description of the
          // transaction to the output.}sb.append("Order book:\n");

        sb.append("Säljare: ");

        // System.out.println("START");
        while (sell_pq.size() > 0) {
            Bid min = sell_pq.minimum();
            // System.out.println(min.bid);
            sb.append(min.toString() + ", ");

            sell_pq.deleteMinimum();

        }
        // System.out.println("END");
        sb.append("\n");
        // can remove from priority queue until it is empty.

        sb.append("Köpare: ");
        // can remove from priority queue until it is empty.
        while (buy_pq.size() > 0) {
            Bid max = buy_pq.minimum();
            sb.append(max.toString()+ ", ");
            buy_pq.deleteMinimum();
        }

        return sb.toString();

    }

    public static void main(String[] args) throws IOException {
        final BufferedReader actions;
        if (args.length != 1) {
            actions = new BufferedReader(new InputStreamReader(System.in));
        } else {
            actions = new BufferedReader(new FileReader(args[0]));
        }
        List<String> lines = new LinkedList<String>();
        while (true) {
            String line = actions.readLine();
            if (line == null)
                break;
            lines.add(line);
        }
        actions.close();
        System.out.println(pureMain(lines.toArray(new String[lines.size()])));
    }
}