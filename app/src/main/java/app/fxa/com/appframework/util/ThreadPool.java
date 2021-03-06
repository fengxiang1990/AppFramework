package app.fxa.com.appframework.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool {

    private ThreadPool() {
    }

    private static ExecutorService fixedService = Executors
            .newSingleThreadExecutor();

    private static ExecutorService executorService = Executors
            .newSingleThreadExecutor();

    private static ExecutorService cacheService = Executors
            .newCachedThreadPool();

    public static ExecutorService getSingleThread() {

        return executorService;

    }

    public static ExecutorService getCacheService() {

        return cacheService;

    }

    public static ExecutorService getFixedService() {

        return fixedService;

    }
}
