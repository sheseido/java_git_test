package concurrent.demo9;

/**
 * 变量在各个线程间的可见性，线程为了快速访问变量，会从主内存拷贝一个副本在线程的工作内存中
 * 当一个线程改变了变量的值后，另一个线程中值还是原来的值，这就是线程间变量的不可见行。
 * 使用volitaile可以使变量在线程中可见（一致）
 */
public class Demo {
    boolean running=true;

    public void test(){
        System.out.println("test start ...");
        while (running){

        }
        System.out.println("test end ...");
    }

    public static void main(String[] args) {
        Demo demo=new Demo();
        new Thread(demo::test,"t1").start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        demo.running=false;
    }
}
