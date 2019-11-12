package layout;
import org.openjdk.jol.info.ClassLayout;
import static java.lang.System.out;
public class JOLExample1 {
    static A a;

    public static void main(String[] args) throws InterruptedException {
        // -XX:+UseBiasedLocking 配置虚拟机默认开启偏向锁
        // 虚拟机启动后会在一段时间后才开启偏向锁，是因为有很多java线程，如gc线程存在竞争，
        // 如果马上开启偏向锁，对这些java线程来说，是资源浪费。 这里睡眠5秒，
        Thread.sleep(5000);
        a=new A();
        synchronized (a){
            System.out.println("1");
            out.println(ClassLayout.parseInstance(a).toPrintable());
        }

        Thread t1;
        new Thread(()->{
            synchronized (a){
                out.println("2");
                out.println(ClassLayout.parseInstance(a).toPrintable());
            }
        }).start();
    }
}
