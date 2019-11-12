package com.tigger;

public class ThreadExample {

    private static Thread thread=null;
    private  static boolean runing=true;
    /*public static void main(String[] args) throws InterruptedException {
        //System.out.println("-----------");
        traditional();

        Thread.sleep(100);
        runing=false;
    }

    public static void traditional(){
        //主线程如果没有sleep，runing会马上被设为false，while条件为false，循环不会执行，程序马上会结束
        //而主线程sleep了，在编译的时候，会对runing进行优化，runing不会马上置为false，进入到traditionnal方法后，
        //在traditionnal内部设置一个局部变量让他等于runing（因为running是个外部变量），可能会是这样
        // 此时runing是初始化是的true所以while为true，空轮询一致持续。
        // 如果while不是空轮询，那编译期就不会优化代码，因为这样可能会带来“方法溢出”
        //boolean temp=runing;
        //while (temp){
        //}
        thread=new Thread(){
            @Override
            public void run() {
                while (runing){
                    //如果while是个空轮询（循环里没有代码），且man函数的主线程有sleep，则该程序永远不会停止
                    // 如果循环里有代码则会停止，如果主线程没有sleep也会停止
                    // 这里会导致指令重排序
                    *//*try {
                        Thread.sleep(100000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }*//*
                    System.out.println("i am new thread...");
                }
            }
        };
        thread.start();
    }*/

    public static void main(String[] args) throws InterruptedException {
        System.out.println("main-----------");
        traditional();
        Thread.sleep(100);
        thread.interrupt();
        runing=false;
    }

    public static void  traditional(){
        thread= new Thread(){
            @Override
            public void run() {
                while (!this.isInterrupted()) {
                    try {
                        System.out.println("解阻塞...");
                        Thread.sleep(100000);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("我是个线程...");
                }
            }
        };
        thread.start();
    }
}
