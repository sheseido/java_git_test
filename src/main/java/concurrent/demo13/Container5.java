package concurrent.demo13;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 一道面试题：实现一个容器，提供两个方法，add，size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数
 * 当个数到5时，线程2给出提示并结束
 * latch来实现

 */
public class Container5 {
    volatile List lists= new ArrayList();

    public void add(Object o){
        lists.add(o);
    }

    public int size(){ return lists.size(); }

    public static void main(String[] args) {
        Container5 c = new Container5();
        CountDownLatch latch = new CountDownLatch(1);
        new Thread(() -> {
            System.out.println("t2 start ...");
            if (c.size() != 5) {
                try {
                    latch.await();//准备
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("t2 end ...");
        }, "t2").start();

        new Thread(() -> {
            System.out.println("t1 start...");
            for (int i = 0; i < 10; i++) {
                c.add(new Object());
                System.out.println("add" + i);
                if (c.size() == 5) {
                    latch.countDown();
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1").start();
    }
}
