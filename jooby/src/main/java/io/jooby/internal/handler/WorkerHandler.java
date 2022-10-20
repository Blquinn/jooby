/*
 * Jooby https://jooby.io
 * Apache License Version 2.0 https://jooby.io/LICENSE.txt
 * Copyright 2014 Edgar Espina
 */
package io.jooby.internal.handler;

import edu.umd.cs.findbugs.annotations.NonNull;
import io.jooby.Context;
import io.jooby.Route;

public class WorkerHandler implements LinkedHandler {
  private final Route.Handler next;

  public WorkerHandler(Route.Handler next) {
    this.next = next;
  }

  @NonNull @Override
  public Object apply(@NonNull Context ctx) {
    return ctx.dispatch(
        () -> {
          try {
            next.apply(ctx);
          } catch (Throwable x) {
            ctx.sendError(x);
          }
        });
  }

  @Override
  public Route.Handler next() {
    return next;
  }
}
