/*
 * Jooby https://jooby.io
 * Apache License Version 2.0 https://jooby.io/LICENSE.txt
 * Copyright 2014 Edgar Espina
 */
package io.jooby;

/**
 * Router matching options. Specify whenever ignore case and trailing slash. Options:
 *
 * <ul>
 *   <li>IGNORE_CASE: Indicates whenever routing algorithm does case-sensitive matching on incoming
 *       request path. Default is <code>case sensitive</code>.
 *   <li>IGNORE_TRAILING_SLASH: Indicates whenever a trailing slash is ignored on incoming request
 *       path.
 *   <li>NORMALIZE_SLASH: Normalize incoming request path by removing consecutive <code>/</code>
 *       (slashes).
 *   <li>RESET_HEADERS_ON_ERROR: Indicates whenever response headers are clear/reset in case of
 *       exception.
 * </ul>
 *
 * @author edgar
 * @since 2.4.0
 */
public enum RouterOption {
  /**
   * Indicates whenever routing algorithm does case-sensitive matching on incoming request path.
   * Default is <code>case sensitive</code>.
   */
  IGNORE_CASE,

  /** Indicates whenever a trailing slash is ignored on incoming request path. */
  IGNORE_TRAILING_SLASH,

  /** Normalize incoming request path by removing multiple slash sequences. */
  NORMALIZE_SLASH,

  /** Indicates whenever response headers are clear/reset in case of exception. */
  RESET_HEADERS_ON_ERROR
}
