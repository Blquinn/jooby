/*
 * Jooby https://jooby.io
 * Apache License Version 2.0 https://jooby.io/LICENSE.txt
 * Copyright 2014 Edgar Espina
 */
package io.jooby.internal.nima;

import edu.umd.cs.findbugs.annotations.NonNull;
import io.helidon.nima.webserver.http.ServerResponse;
import io.jooby.Sender;

public class NimaSender implements Sender {
  private final NimaContext ctx;
  private final ServerResponse response;

  public NimaSender(NimaContext ctx, ServerResponse response) {
    this.ctx = ctx;
    this.response = response;
  }

  @Override @NonNull
  public Sender write(@NonNull byte[] data, @NonNull Callback callback) {
    try {
      response.send(data);
      callback.onComplete(ctx, null);
    } catch (Exception e) {
      callback.onComplete(ctx, e);
    }

    return this;
  }

  @Override
  public void close() {
    ctx.destroy(null);
  }
}
