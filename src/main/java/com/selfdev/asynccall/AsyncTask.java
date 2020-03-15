package com.selfdev.asynccall;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

/**
 * Created by vovkv on 8/8/2019.
 */
public class AsyncTask implements Callable<String> {
    final String userId;
    final String defaultValue;
    final long timeout;
    final Consumer<String> callback;
    final AsyncCall.Webservice webservice;
    AtomicBoolean isCancelled = new AtomicBoolean(false);

    public AsyncTask(String userId, String defaultValue, long timeout, Consumer<String> callback, AsyncCall.Webservice webservice) {
        this.userId = userId;
        this.defaultValue = defaultValue;
        this.timeout = timeout;
        this.callback = callback;
        this.webservice = webservice;

    }


    public void defaultCallback() {
        System.out.println("Task is cancelled");
        isCancelled.set(true);
        callback.accept(defaultValue);
    }

    public boolean isCancelled(){
        return isCancelled.get();
    }

    @Override
    public String call() throws Exception {
        String result = defaultValue;
        try {
            result = webservice.request(userId);
        } finally {
            if (!isCancelled.get()) {
                isCancelled.set(true);
                callback.accept(result);
            }
            return null;
        }
    }
}
