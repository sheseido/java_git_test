package concurrent.demo14;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * 面试题：写一个固定容量同步容器，拥有Put和get方法，以及getCount方法
 * 能够支持两个生产者线程以及10个消费者线程的阻塞调用
 */
public class Container1<T> {
    LinkedList<T> list=new LinkedList();
    int MAX=10;

    public synchronized void put(T t){
        while (list.size()==MAX){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        list.add(t);
        this.notifyAll();
    }

    public synchronized T get(){
        while (list.size()==0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        T t= list.removeFirst();
        System.out.println(Thread.currentThread().getName()+" get ");
        this.notifyAll();
        return t;
    }

    public static void main(String[] args) {
        Container1<String> c=new Container1<>();

        for (int i=0;i<100;i++){
            new Thread(() ->{
                for (int j = 0; j < 5; j++) {
                    System.out.println(c.get());
                }
            },"c"+i).start();
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 2; i++) {
            new Thread(() ->{
                for (int j = 0; j < 25; j++) {
                    c.put(Thread.currentThread().getName()+" "+j);
                }
            },"p"+i).start();
        }
    }
}
