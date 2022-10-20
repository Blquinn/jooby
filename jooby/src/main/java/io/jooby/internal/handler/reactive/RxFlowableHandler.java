/*
 * Jooby https://jooby.io
 * Apache License Version 2.0 https://jooby.io/LICENSE.txt
 * Copyright 2014 Edgar Espina
 */
package io.jooby.internal.handler.reactive;

import edu.umd.cs.findbugs.annotations.NonNull;
import io.jooby.Context;
import io.jooby.Route;
import io.jooby.internal.handler.LinkedHandler;
import io.reactivex.Flowable;

public class RxFlowableHandler implements LinkedHandler {

  private final Route.Handler next;

  public RxFlowableHandler(Route.Handler next) {
    this.next = next;
  }

  @NonNull @Override
  public Object apply(@NonNull Context ctx) {
    try {
      Flowable result = (Flowable) next.apply(ctx);
      result.subscribe(new ReactiveSubscriber(new ChunkedSubscriber(ctx)));
      return result;
    } catch (Throwable x) {
      ctx.sendError(x);
      return Flowable.error(x);
    }
  }

  @Override
  public Route.Handler next() {
    return next;
  }
}
