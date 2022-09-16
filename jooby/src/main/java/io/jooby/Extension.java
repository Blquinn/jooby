/**
 * Jooby https://jooby.io
 * Apache License Version 2.0 https://jooby.io/LICENSE.txt
 * Copyright 2014 Edgar Espina
 */
package io.jooby;

import edu.umd.cs.findbugs.annotations.NonNull;

/**
 * Simple extension contract for adding and reusing commons application infrastructure components
 * and/or integrate with external libraries.
 *
 * Extensions are expected to work via side-effects.
 *
 * @author edgar
 * @since 2.0.0
 */
public interface Extension {

  /**
   * True when extension needs to run while starting the application. Defaults is false, starts
   * immediately.
   *
   * @return True when extension needs to run while starting the application. Defaults is false,
   *     starts immediately.
   */
  default boolean lateinit() {
    return false;
  }

  /**
   * Install, configure additional features to a Jooby application.
   *
   * @param application Jooby application.
   * @throws Exception If something goes wrong.
   */
  void install(@NonNull Jooby application) throws Exception;
}
