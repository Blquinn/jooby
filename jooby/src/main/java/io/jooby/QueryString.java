/**
 * Jooby https://jooby.io
 * Apache License Version 2.0 https://jooby.io/LICENSE.txt
 * Copyright 2014 Edgar Espina
 */
package io.jooby;

import io.jooby.internal.UrlParser;

import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;

/**
 * Query string class for direct MVC parameter provisioning.
 *
 * @author edgar
 * @since 2.0.0
 */
public interface QueryString extends ValueNode {

  /**
   * Query string with the leading <code>?</code> or empty string.
   *
   * @return Query string with the leading <code>?</code> or empty string.
   */
  @NonNull String queryString();

  /**
   * Query string hash value.
   *
   * <pre>{@code q=foo&sort=name}</pre>
   *
   * Produces:
   *
   * <pre>{@code {q: foo, sort: name}}</pre>
   *
   * @param ctx Current context.
   * @param queryString Query string.
   * @return A query string.
   */
  static @NonNull QueryString create(@NonNull Context ctx, @Nullable String queryString) {
    return UrlParser.queryString(ctx, queryString);
  }
}
