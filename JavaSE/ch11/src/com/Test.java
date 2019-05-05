package com;

import java.io.IOException;

public class Test {
    private static int foo(int n) throws IOException {
        if (n < 0) throw new IOException();
        else return n;
    }

    public static void main(String[] args) throws IOException {
        int result = foo(-1);
        System.out.println(result);
    }
}
