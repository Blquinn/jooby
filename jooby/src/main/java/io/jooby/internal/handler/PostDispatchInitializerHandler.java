/*
 * Jooby https://jooby.io
 * Apache License Version 2.0 https://jooby.io/LICENSE.txt
 * Copyright 2014 Edgar Espina
 */
package io.jooby.internal.handler;

import edu.umd.cs.findbugs.annotations.NonNull;
import io.jooby.Context;
import io.jooby.Route;
import io.jooby.internal.ContextInitializer;

public class PostDispatchInitializerHandler implements LinkedHandler {

  private final ContextInitializer initializer;
  private final Route.Handler next;

  public PostDispatchInitializerHandler(ContextInitializer initializer, Route.Handler next) {
    this.initializer = initializer;
    this.next = next;
  }

  @NonNull @Override
  public Object apply(@NonNull Context ctx) {
    try {
      initializer.apply(ctx);
      return next.apply(ctx);
    } catch (Throwable x) {
      ctx.sendError(x);
      return x;
    }
  }

  @Override
  public Route.Handler next() {
    return next;
  }
}
