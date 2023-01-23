package io.jooby.nima;

import io.jooby.Jooby;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestServer extends Jooby {
    {
        get("/", (ctx -> {
            ExecutorService worker = Executors.newThreadPerTaskExecutor(Thread.ofVirtual()
                    .allowSetThreadLocals(true)
                    // Make this configurable
                    .inheritInheritableThreadLocals(true)
                    .factory());

            // Spawn 100 sleepy threads.
            for (int i = 0; i < 100; i++) {
                int finalI = i;
                worker.execute(() -> {
                    try {
                        Thread.sleep(finalI);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        throw new RuntimeException(e);
                    }
                });
            }

            // Wait for them to close.
            worker.close();

            return ctx.send(String.format("Hello nima, virtual = %s", Thread.currentThread().isVirtual()));
        }));
    }

    public static void main(String[] args) {
        Jooby.runApp(args, TestServer.class);
    }
}
