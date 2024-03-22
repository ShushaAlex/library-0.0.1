package com.example.library001.example_exception;

public class Main {

    public static void main(String[] args) {

        try {
            System.out.println("Try");
        } catch (ArithmeticException | IllegalArgumentException a) {
            System.out.println("ArithmeticException");
        } finally {
            System.out.println("finally");
        }
    }
}
