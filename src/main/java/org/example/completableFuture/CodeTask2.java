package org.example.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CodeTask2 {
    public static void runTask2() throws ExecutionException, InterruptedException {
        var firstAssetFuture = getFirstInfo();
        System.out.println(firstAssetFuture.get());
        System.out.println("Is first asset is done: " + firstAssetFuture.isDone());
    }

    public static String requestInfo(String asset) {
        var  time = switch (asset) {
            case "AAPL" -> 500;
            case "MSFT" -> 1000;
            case "GOOG" -> 1500;
            default -> 0;
        };

        try {
            Thread.sleep(time);
            return "Info about: " + asset;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static CompletableFuture<Object> getFirstInfo(){
        var apple = CompletableFuture.supplyAsync(() -> requestInfo("AAPL"));
        var microsoft = CompletableFuture.supplyAsync(() -> requestInfo("MSFT"));
        var google = CompletableFuture.supplyAsync(() -> requestInfo("GOOG"));

        // edit code above
//        CompletableFuture<Object> firstAssetFuture =
        CompletableFuture<Object> firstAssetFuture = CompletableFuture.anyOf(apple, microsoft, google);
        return firstAssetFuture;
    }
}
