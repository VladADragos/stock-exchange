// package src;

import java.util.Comparator;

public class SellComparator implements Comparator<Bid> {

    @Override
    public int compare(Bid o1, Bid o2) {

        if (o1.bid > o2.bid)
            return 1;
        if (o1.bid == o2.bid)
            return 0;
        return -1;

    }
}