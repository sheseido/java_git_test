package concurrent2.demo3.saleoftickets;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

/**
 * 有10000张火车票，10个窗口同时对外售票
 * 请写一个程序模拟
 */
public class SaleOfTickets2 {
    private static Vector<Integer> tickets=new Vector<>();
    static{
        for (int i=0;i<10000;i++){
            tickets.add(i);
        }
    }

    public static void main(String[] args) {
        for (int i=0;i<10;i++){
            new Thread(()->{
                while (tickets.size()>0){
                    try {
                        TimeUnit.MILLISECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+"销售票编号:"+tickets.remove(0));
                }
            }).start();
        }
    }
}
