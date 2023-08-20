package com.seungmoo.codingtest.test;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(50);

        List<CompletableFuture<String>> list = new LinkedList<>();

        for (int i = 0; i < 100; i++) {
            CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return Thread.currentThread() + " - hello";
            }, executorService);
            list.add(stringCompletableFuture);
        }

        for (CompletableFuture<String> future : list) {
            System.out.println(future.join());
        }

        executorService.shutdown();
        System.out.println("executorService.isShutdown() : " + executorService.isShutdown());
        System.out.println("executorService.isTerminated() : " + executorService.isTerminated());
    }
}
