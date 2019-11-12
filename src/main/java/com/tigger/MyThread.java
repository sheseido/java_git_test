package com.tigger;

public class MyThread {
    static {
        System.loadLibrary("TiggerThreadNative");
    }

    public static void main(String[] args) {
        MyThread myThread=new MyThread();
        myThread.start0();
    }

    private native void start0();
}
