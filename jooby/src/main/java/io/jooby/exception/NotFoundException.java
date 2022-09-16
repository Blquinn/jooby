/**
 * Jooby https://jooby.io
 * Apache License Version 2.0 https://jooby.io/LICENSE.txt
 * Copyright 2014 Edgar Espina
 */
package io.jooby.exception;

import io.jooby.StatusCode;

import edu.umd.cs.findbugs.annotations.NonNull;

/**
 * When a request doesn't match any of the available routes.
 *
 * @since 2.4.0
 * @author edgar
 */
public class NotFoundException extends StatusCodeException {

  /**
   * Creates a not found exception.
   *
   * @param path Requested path.
   */
  public NotFoundException(@NonNull String path) {
    super(StatusCode.NOT_FOUND, path);
  }

  /**
   * Requested path.
   *
   * @return Requested path.
   */
  public @NonNull String getRequestPath() {
    return getMessage();
  }
}
