package concurrent.demo13;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 一道面试题：实现一个容器，提供两个方法，add，size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数
 * 当个数到5时，线程2给出提示并结束
 * （这个类可以实现，t2的lock.wait()会释放锁,t2释放锁之后会一直阻塞,
 * t1拿到了锁,当t1把size加到5之后,调用lock.notify()和lock.wait()这样t1释放了锁
 * 注意notify()是随机唤醒,此时争用的线程只有t1和t2，那t2得到锁，
 * t2得到锁执行打印后又执行wait释放锁，让t1继续执行.所以t1的循环继续，知道循环结束
 * ）
 */
public class Container4 {
    volatile List lists= new ArrayList();

    public void add(Object o){
        lists.add(o);
    }

    public int size(){ return lists.size(); }

    public static void main(String[] args) {
        Container4 c = new Container4();
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
                lock.notify();
            }
        }, "t2").start();

        new Thread(() -> {
            synchronized (lock) {
                for (int i = 0; i < 10; i++) {
                    c.add(new Object());
                    System.out.println("add" + i);
                    if (c.size() == 5) {
                        lock.notify();
                        try {
                            lock.wait();//释放锁，t2才能得到锁，继续执行
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
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
