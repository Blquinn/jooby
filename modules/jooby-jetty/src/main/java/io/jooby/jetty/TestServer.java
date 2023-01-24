package io.jooby.jetty;

import io.jooby.Jooby;
import io.jooby.ServerOptions;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestServer extends Jooby {
    {
        setServerOptions(
                new ServerOptions()
                        .setServer("jetty")
                        .setUseVirtualThreads(true)
        );

        get("/", (ctx -> {
            try (ExecutorService e = Executors.newVirtualThreadPerTaskExecutor()) {
                for (int i = 1; i <= 50; i++) {
                    int ii = i;
                    e.submit(() -> {
                        try {
                            Thread.sleep(ii);
                        } catch (InterruptedException ex) {
                            Thread.currentThread().interrupt();
                            throw new RuntimeException(ex);
                        }
                    });
                }
            }
//            Thread.sleep(50);
            return ctx.send("Hello world");
        }));
    }

    public static void main(String[] args) {
        Jooby.runApp(args, TestServer.class);
    }
}
