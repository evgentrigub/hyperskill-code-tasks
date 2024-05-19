package org.example.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CodeTask1 {
    public static void runTask1() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = getFutureObject();
        var value = future.getNow("default");
        var isDone = future.isDone();
        System.out.println("Is CompletableFuture done: " + isDone);
        System.out.println("Value from CompletableFuture: "  + value);
    }

    public static CompletableFuture<String> getFutureObject() {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        completableFuture.thenRun(CodeTask1::finishSomeHardWork);
        // modify code here
//        completableFuture.complete("Task is done");
        return completableFuture;
    }

    public static void finishSomeHardWork() {
        try {
            int ONE_SECOND = 1000;
            Thread.sleep(ONE_SECOND);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
