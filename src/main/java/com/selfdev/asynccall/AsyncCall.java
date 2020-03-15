package com.selfdev.asynccall;

import java.util.concurrent.*;
import java.util.function.Consumer;

/**
 * Created by vovkv on 8/8/2019.
 */
public class AsyncCall {

    ExecutorService executorService = Executors.newFixedThreadPool(10);
    Webservice webservice;

    // i don't like this map
    // ???????? ?? ????? ?? ????? ???? ?????????????? ??????????? ?????? ??? ???, ? ????? ??? ????????? ????? ??????????
    ConcurrentHashMap<Long, AsyncTask> timerMap = new ConcurrentHashMap<>();
    BlockingQueue<AsyncTask> queue = new ArrayBlockingQueue<AsyncTask>(100);

    public AsyncCall(Webservice webservice) {
        this.webservice = webservice;
        Runnable timer = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        //https://stackoverflow.com/questions/2275443/how-to-timeout-a-thread
                        //??? ???? ?????? ? ?????????? ??? ?? ????????, ???????? ?? ????? ???-- ?????????? ????? ??????-?? ??????? ?????
                        //??????? ?? ???? ??? ????? ???? ?? ???????????? ????? DelayQueue?
                        Thread.sleep(50);
                        timerMap.entrySet().stream().forEach((entry) -> {
                            long key = entry.getKey();
                            if (key < System.currentTimeMillis()) {
                                AsyncTask task = entry.getValue();
                                if (!task.isCancelled()) {
                                    System.out.println("Timer triggered");
                                    task.defaultCallback();
                                }
                                timerMap.remove(key);
                            }
                        });

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        executorService.submit(timer);
    }

    public void getUser(final String userId,
                        final String defaultValue,
                        final long timeout,
                        final Consumer<String> callback) {
        AsyncTask asyncTask = new AsyncTask(userId, defaultValue, timeout, callback, webservice);
        Future<String> f = executorService.submit(asyncTask);
        try {
            f.get(timeout, TimeUnit.SECONDS);
        } catch (Exception e) {
            f.cancel(true);
            asyncTask.defaultCallback();
        }
    }

    @FunctionalInterface
    interface Webservice {
        String request(String userId);
    }

    public static void main(String[] args) {
        Webservice w1 = (String userId) -> {
            return "user1";
        };

        Webservice w2 = (String userId) -> {
            try {
                Thread.sleep(10000);
            } finally {
                return "user2";
            }
        };

        Webservice w3 = (String userId) -> {
            throw new RuntimeException();
        };


        AsyncCall asyncCallSuccess = new AsyncCall(w1);
        AsyncCall asyncCallSleep = new AsyncCall(w2);
        AsyncCall asyncCallRuntimeException = new AsyncCall(w3);
        Consumer<String> callback = (String result) -> {
            System.out.println(result);
        };

        asyncCallSuccess.getUser("1", "defaultValue1", 1000l, callback);
        asyncCallSleep.getUser("2", "defaultValue2", 5000, callback);
        asyncCallRuntimeException.getUser("3", "defaultValue3", 1000l, callback);

    }
}
