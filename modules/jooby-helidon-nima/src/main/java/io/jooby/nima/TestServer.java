package io.jooby.nima;

import io.jooby.Jooby;

public class TestServer extends Jooby {
    {
        get("/", (ctx ->
            ctx.send(String.format("Hello nima, virtual = %s", Thread.currentThread().isVirtual()))
        ));
    }

    public static void main(String[] args) {
        Jooby.runApp(args, TestServer.class);
    }
}
