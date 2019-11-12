package concurrent.demo2;

import concurrent.demo8.Demo;

import java.util.concurrent.TimeUnit;

public class Demo2 {
    String s1="hello";
    String s2="hello";

    Integer count=0;

    public void test1(){
        // t2不会执行 虽然锁对象是s1和s2，但是他们是常量池里的同一个String变量，是同一个对象。
        // 注意sleep() 会让出cpu时间，进入休眠，t2线程可以执行，
        // 因为使用了synchronized锁，t2一直在等待t1释放锁，但是t1在死循环中永远没有释放锁，t1只是sleep，并不会释放锁
        // 等到t1重新分配到了cpu时间，又继续执行。
        synchronized (s1){
            System.out.println("t1 start...");
            try {
                while (true) {
                    count++;
                    System.out.println(count);
                    TimeUnit.MILLISECONDS.sleep(200);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t1 end ...");
        }
    }

    public void test2(){
        synchronized (s2){
            System.out.println("t2 start ...");
            while (true){
                count++;
                System.out.println(count);
                try {
                    TimeUnit.MILLISECONDS.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        Demo2 demo=new Demo2();
        new Thread(demo::test1,"test1").start();
        new Thread(demo::test2,"test2").start();
    }
}
