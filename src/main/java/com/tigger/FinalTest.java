package com.tigger;

import javax.print.DocFlavor;
import java.util.Arrays;
import java.util.HashSet;

public class FinalTest {
    String a = "hello2";
    private final String b = "hello";
    private String c = "hello";

    public static void main(String[] args) {
        String a = "hello2";
        final String b = "hello";
        String c = "hello";

        System.out.println(a==(b+2));
        System.out.println(a==(c+2));
        System.out.println("------------------------------");

        FinalTest test=new FinalTest();
        System.out.println(test.a==(test.b+2));
        System.out.println(test.a==(test.c+2));



    }
}
