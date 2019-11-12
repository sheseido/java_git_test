package aqs;

import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class test1 {
    static ReentrantLock lock=new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
//        Thread t1=new Thread(){
//            @Override
//            public void run() {
//                System.out.println("11111");
//                LockSupport.park();
//                System.out.println("22222");
//            }
//        };
//        t1.start();
//
//        Thread.sleep(3000);
//        LockSupport.unpark(t1);


        try {
            lock.lock();
            lock.lockInterruptibly();
            logic();
        }
        catch (Exception ex){}
        finally {
            lock.unlock();
        }
    }

    public static void logic(){
        System.out.println("1111");
    }
}
