package concurrent.demo13;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 一道面试题：实现一个容器，提供两个方法，add，size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数
 * 当个数到5时，线程2给出提示并结束
 * （这个类未实现）
 */
public class Container1 {

    List lists= new ArrayList();

    public void add(Object o){
        lists.add(o);
    }

    public int size(){
        return lists.size();
    }

    public static void main(String[] args) {
        Container1 c=new Container1();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    c.add(new Object());
                    System.out.println("add"+i);

                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                };
            }
        },"t1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    if(c.size()==5){
                        break;
                    }
                }
                System.out.println("t2 end ...");
            }
        },"t2").start();
    }
}
