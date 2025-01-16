package ir.mostafa.semnani.hotel_management_system.config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class VirtualThreadPool {

    private static volatile ExecutorService executor;

    private VirtualThreadPool() {
    }

    public static ExecutorService getVirtualThreadPool() {
        if (executor == null) {
            synchronized (VirtualThreadPool.class) {
                if (executor == null) {
                    ThreadFactory virtualThreadFactory = Thread.ofVirtual().factory();
                    executor = Executors.newFixedThreadPool(1_000_000, virtualThreadFactory);
                }
            }
        }

        return executor;
    }

}
