package concurrent.demo13;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 一道面试题：实现一个容器，提供两个方法，add，size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数
 * 当个数到5时，线程2给出提示并结束
 * （这个类也未实现，虽然volatile可以保证lists在线程间可见性,但是不能保证线程安全和原子性）
 */
public class Container2 {
    volatile List lists= new ArrayList();

    public void add(Object o){
        lists.add(o);
    }

    public int size(){ return lists.size(); }

    public static void main(String[] args) {
        Container2 c = new Container2();

        new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    c.add(new Object());
                    System.out.println("add" + i);

                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        new Thread(() -> {
            while (true) {
                System.out.println("t2 开始比较");
                if (c.size() == 5) {
                    break;
                }
            }
            System.out.println("t2 end ...");
        }, "t2").start();
    }
}
