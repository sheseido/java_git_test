package concurrent.demo8;

import java.util.concurrent.TimeUnit;

public class Demo {

    private Integer count=0;
    public synchronized void test(){
        System.out.println(Thread.currentThread().getName()+" start...");

        while (true) {
            count++;
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+" count="+count);
            if (count == 5) {
                int i = count / 0;
            }
        }
    }

    public static void main(String[] args) {
        Demo demo1=new Demo();
        Runnable r=()->demo1.test();
        Thread t1=new Thread(r,"t1");
        t1.start();

        Thread t2=new Thread(r,"t2");
        //如果t2是守护线程，那在抛出异常后，整个进程结束
        //如果t2不是守护线程，那么在抛出异常后，t2线程会继续执行
        t2.setDaemon(true);
        System.out.println(t2.isDaemon());
        t2.start();
    }
}
