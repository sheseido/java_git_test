package layout;

import org.openjdk.jol.info.ClassLayout;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;
import static java.lang.System.setOut;

public class JOLExample2 {
    volatile static List<A> list=new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        List<A> list=new ArrayList<>();
        //生成40个A的实例
        for (int i = 0; i < 100; i++) {
            list.add(new A());
        }
        Thread t1= new Thread(){
            public void run() {
                for (int i = 0; i <100; i++) {
                    A a=list.get(i);
                    synchronized (a) {
                        if(i==1 || i==19) {
                            System.out.println("t1 lock " + i );
                            //打印对象头
                            out.println(ClassLayout.parseInstance(a).toPrintable());//偏向锁
                        }
                    }
                }
            }
        };
        t1.start();
        t1.join();
        new Thread().start();
        Thread t2=new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    A a=list.get(i);
                    synchronized (a) {
                        if(i==19) {
                            //System.out.println("t2 lock " + i);
                            out.println("t2 lock " +ClassLayout.parseInstance(a).toPrintable());
                        }
                    }
                }
            }
        };
        t2.start();
        t2.join();

        Thread t3 =new Thread(){
            @Override
            public void run() {
                for(int i=20;i<100;i++){
                    A a=list.get(i);
                    synchronized (a){
                        if(i==20){
                            out.println("t3 lock"+ClassLayout.parseInstance(a).toPrintable());
                        }
                    }
                }
            }
        };
        t3.start();
    }
}
