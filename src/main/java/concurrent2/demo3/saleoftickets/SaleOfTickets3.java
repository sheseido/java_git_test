package concurrent2.demo3.saleoftickets;
import java.util.LinkedList;
import java.util.List;

/**
 * 有10000张火车票，10个窗口同时对外售票
 * 请写一个程序模拟
 */
public class SaleOfTickets3 {
    private static List<Integer> tickets=new LinkedList<>();
    static{
        for (int i=0;i<10000;i++){
            tickets.add(i);
        }
    }

    public static void main(String[] args) {
        for (int i=0;i<10;i++){
            new Thread(()->{
                while (true){
                    synchronized (tickets) {
                        if(tickets.size()<=0) {
                            break;
                        }
                        System.out.println(Thread.currentThread().getName() + "销售票编号:" + tickets.remove(0));
                    }
                }
            }).start();
        }
    }
}
