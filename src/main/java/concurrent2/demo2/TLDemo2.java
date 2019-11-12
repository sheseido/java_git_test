package concurrent2.demo2;

import java.util.concurrent.TimeUnit;

public class TLDemo2 {
    private static ThreadLocal<String> threadLocal=new ThreadLocal<>();

    public static void main(String[] args) {
        new Thread(() ->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            threadLocal.set("A");
        }).start();

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            threadLocal.set("B");
            //threadLocal.remove();
            System.out.println(threadLocal.get());
        }).start();
    }
}
