/*
 * Jooby https://jooby.io
 * Apache License Version 2.0 https://jooby.io/LICENSE.txt
 * Copyright 2014 Edgar Espina
 */
package io.jooby.exception;

import java.util.Optional;

import edu.umd.cs.findbugs.annotations.Nullable;
import io.jooby.StatusCode;

/** Specific error for forbidden access. */
public class ForbiddenException extends StatusCodeException {
  /**
   * Creates a new forbidden exception.
   *
   * @param message Optional message.
   */
  public ForbiddenException(@Nullable String message) {
    super(StatusCode.FORBIDDEN, Optional.ofNullable(message).orElse(""));
  }

  /** Creates a new forbidden exception. */
  public ForbiddenException() {
    this(null);
  }
}
