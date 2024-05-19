package org.example;

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        CodeTask1.runTask1();
//        CodeTask2.runTask2();

        CompletableFuture
                .supplyAsync(() -> {
                    System.out.println("Thread supplyAsync(): " + Thread.currentThread().getName());
                    return "Value from future";
                })
                .thenRunAsync(() -> System.out.println("Thread thenRunAsync(): " + Thread.currentThread().getName()))
                .get();

        System.out.println("Thread after async: " + Thread.currentThread().getName());
    }
}
