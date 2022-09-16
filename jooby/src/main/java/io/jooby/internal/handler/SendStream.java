/**
 * Jooby https://jooby.io
 * Apache License Version 2.0 https://jooby.io/LICENSE.txt
 * Copyright 2014 Edgar Espina
 */
package io.jooby.internal.handler;

import io.jooby.Context;
import io.jooby.Route;

import edu.umd.cs.findbugs.annotations.NonNull;
import java.io.InputStream;

public class SendStream implements LinkedHandler {
  private Route.Handler next;

  public SendStream(Route.Handler next) {
    this.next = next;
  }

  @NonNull @Override public Object apply(@NonNull Context ctx) throws Exception {
    try {
      Object result = next.apply(ctx);
      if (ctx.isResponseStarted()) {
        return result;
      }
      ctx.send((InputStream) result);
    } catch (Throwable x) {
      ctx.sendError(x);
    }
    return ctx;
  }

  @Override public Route.Handler next() {
    return next;
  }
}
