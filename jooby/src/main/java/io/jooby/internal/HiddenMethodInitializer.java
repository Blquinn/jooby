/*
 * Jooby https://jooby.io
 * Apache License Version 2.0 https://jooby.io/LICENSE.txt
 * Copyright 2014 Edgar Espina
 */
package io.jooby.internal;

import java.util.Optional;
import java.util.function.Function;

import io.jooby.Context;

public class HiddenMethodInitializer implements ContextInitializer {
  private final Function<Context, Optional<String>> provider;

  public HiddenMethodInitializer(Function<Context, Optional<String>> provider) {
    this.provider = provider;
  }

  @Override
  public void apply(Context ctx) {
    provider.apply(ctx).ifPresent(ctx::setMethod);
  }
}
