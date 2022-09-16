/**
 * Jooby https://jooby.io
 * Apache License Version 2.0 https://jooby.io/LICENSE.txt
 * Copyright 2014 Edgar Espina
 */
package io.jooby;

import io.jooby.exception.UnsupportedMediaType;

import edu.umd.cs.findbugs.annotations.NonNull;
import java.lang.reflect.Type;

/**
 * Parse HTTP body into a target type.
 *
 * @since 2.0.0
 * @author edgar
 */
public interface MessageDecoder {

  /**
   * Resolve parsing as {@link StatusCode#UNSUPPORTED_MEDIA_TYPE}.
   */
  MessageDecoder UNSUPPORTED_MEDIA_TYPE = (ctx, type) -> {
    throw new UnsupportedMediaType(ctx.header("Content-Type").valueOrNull());
  };

  /**
   * Parse HTTP body into the given type.
   *
   * @param ctx Web context.
   * @param type Target/expected type.
   * @return An instance of the target type.
   * @throws Exception Is something goes wrong.
   */
  @NonNull Object decode(@NonNull Context ctx, @NonNull Type type) throws Exception;
}
