package ir.mostafa.semnani.vt.hms.config;

import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

@Configuration
public class AppVirtualThreadPool {

    private static final ExecutorService executorService;

    static {
        ThreadFactory virtualThreadFactory =
                Thread.ofVirtual()
                        .name("vthread-", 0)
                        .factory();

        executorService = Executors.newFixedThreadPool(1_000_000, virtualThreadFactory);
    }

    public static ExecutorService getExecutorService() {
        return executorService;
    }

}
