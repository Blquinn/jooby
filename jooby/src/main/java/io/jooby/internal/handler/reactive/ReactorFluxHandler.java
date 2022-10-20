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
import reactor.core.publisher.Flux;

public class ReactorFluxHandler implements LinkedHandler {

  private final Route.Handler next;

  public ReactorFluxHandler(Route.Handler next) {
    this.next = next;
  }

  @NonNull @Override
  public Object apply(@NonNull Context ctx) {
    try {
      Flux result = (Flux) next.apply(ctx);
      result.subscribe(new ReactiveSubscriber(new ChunkedSubscriber(ctx)));
      return result;
    } catch (Throwable x) {
      ctx.sendError(x);
      return Flux.error(x);
    }
  }

  @Override
  public Route.Handler next() {
    return next;
  }
}
