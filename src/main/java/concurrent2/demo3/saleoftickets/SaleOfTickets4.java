package concurrent2.demo3.saleoftickets;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * 有10000张火车票，10个窗口同时对外售票
 * 请写一个程序模拟
 */
public class SaleOfTickets4 {
    private static Queue<Integer> tickets=new ConcurrentLinkedDeque<>();
    static{
        for (int i=0;i<10000;i++){
            tickets.add(i);
        }
    }

    public static void main(String[] args) {
        for (int i=0;i<10;i++){
            new Thread(()->{
                while (true){
                    Integer poll=tickets.poll();
                    if(poll==null){
                        break;
                    }
                    System.out.println(Thread.currentThread().getName() + "销售票编号:" + poll);
                }
            }).start();
        }
    }
}
