// package src;

public class SimpleTest {
        

    public static void main(String[] args){
            PriorityQueue<Bid> sell_pq = new PriorityQueue<Bid>(new SellComparator()  );
            PriorityQueue<Bid> buy_pq = new PriorityQueue<Bid>( new BuyComparator());
            sell_pq.add(new Bid("name0", 65536));
            sell_pq.add(new Bid("name1",131072));
            //sell_pq.add(new Bid("name2",262144));
            //sell_pq.add(new Bid("name3",98304));
            //sell_pq.add(new Bid("name69",1));
            // sell_pq.add(new Bid("name5",1));
            while( sell_pq.size()>0){
                System.out.println(sell_pq.minimum());
                sell_pq.deleteMinimum();
            }

            //System.out.println(sell_pq.heap.get(0));
        //System.out.println(sell_pq.heap.get(1));
                    // System.out.println("dafuq");
        }


}