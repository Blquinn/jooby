/*
 * Jooby https://jooby.io
 * Apache License Version 2.0 https://jooby.io/LICENSE.txt
 * Copyright 2014 Edgar Espina
 */
package io.jooby.internal.handler;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.CompletionStage;

import edu.umd.cs.findbugs.annotations.NonNull;
import io.jooby.Context;
import io.jooby.Route;

public class CompletionStageHandler implements LinkedHandler {

  private final Route.Handler next;

  public CompletionStageHandler(Route.Handler next) {
    this.next = next;
  }

  @NonNull @Override
  public Object apply(@NonNull Context ctx) {
    try {
      Object result = next.apply(ctx);
      if (ctx.isResponseStarted()) {
        return result;
      }
      return ((CompletionStage) result)
          .whenComplete(
              (value, x) -> {
                try {
                  if (x != null) {
                    Throwable exception = (Throwable) x;
                    if (exception instanceof CompletionException) {
                      exception = Optional.ofNullable(exception.getCause()).orElse(exception);
                    }
                    ctx.sendError(exception);
                  } else {
                    ctx.render(value);
                  }
                } catch (Throwable cause) {
                  ctx.sendError(cause);
                }
              });
    } catch (Throwable x) {
      ctx.sendError(x);
      return CompletableFuture.completedFuture(x);
    }
  }

  @Override
  public Route.Handler next() {
    return next;
  }
}
