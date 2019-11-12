package concurrent.demo13;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 一道面试题：实现一个容器，提供两个方法，add，size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数
 * 当个数到5时，线程2给出提示并结束
 * （这个类也未实现，t2的lock.wait()会释放锁,t2释放锁之后,t1拿到了锁,当t1把size加到5之后,调用lock.notify()
 * 虽然t1能唤醒t2,但是t2得不到cpu，他会一直等到t1释放锁，这样t1继续执行直到循环结束
 * ）
 */
public class Container3 {
    volatile List lists= new ArrayList();

    public void add(Object o){
        lists.add(o);
    }

    public int size(){ return lists.size(); }

    public static void main(String[] args) {
        Container3 c = new Container3();
        Object lock=new Object();
        new Thread(() -> {
            System.out.println("t2 start ...");
            synchronized (lock){
                if(c.size()!=5){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("t2 end ...");
            }
        }, "t2").start();

        new Thread(() -> {
            synchronized (lock) {
                for (int i = 0; i < 10; i++) {
                    c.add(new Object());
                    System.out.println("add" + i);
                    if (c.size() == 5) {
                        lock.notify();
                    }
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "t1").start();
    }
}
