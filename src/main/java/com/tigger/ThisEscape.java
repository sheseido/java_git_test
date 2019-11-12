package com.tigger;

public class ThisEscape {
    private String id;

    public ThisEscape(String id) {
        new Thread(new EscapeRunnable()).start();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // ...其他代码
        this.id = id;
    }

    private class EscapeRunnable implements Runnable{

        @Override
        public void run() {
            System.out.println("id" + ThisEscape.this.id);
        }
    }

    public static void main(String[] args) {
        ThisEscape thisEscape=new ThisEscape("12");
    }
}
