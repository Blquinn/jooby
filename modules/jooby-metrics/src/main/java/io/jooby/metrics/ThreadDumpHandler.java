/**
 * Jooby https://jooby.io
 * Apache License Version 2.0 https://jooby.io/LICENSE.txt
 * Copyright 2014 Edgar Espina
 */
package io.jooby.metrics;

import com.codahale.metrics.jvm.ThreadDump;
import io.jooby.Context;
import io.jooby.MediaType;
import io.jooby.Route;
import io.jooby.StatusCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.umd.cs.findbugs.annotations.NonNull;
import java.io.ByteArrayOutputStream;
import java.lang.management.ManagementFactory;

public class ThreadDumpHandler implements Route.Handler {

  private final Logger log = LoggerFactory.getLogger(getClass());

  private ThreadDump threadDump;

  {
    try {
      // Some PaaS like Google App Engine blacklist java.lang.management
      this.threadDump = new ThreadDump(ManagementFactory.getThreadMXBean());
    } catch (Exception ex) {
      log.warn("Thread dump isn't available", ex);
    }
  }

  @NonNull
  @Override
  public Object apply(@NonNull Context ctx) {
    Object data;
    if (threadDump == null) {
      data = "Sorry your runtime environment does not allow to dump threads.";
      ctx.setResponseCode(StatusCode.NOT_IMPLEMENTED);
    } else {
      final ByteArrayOutputStream output = new ByteArrayOutputStream();
      threadDump.dump(output);
      data = output.toByteArray();
    }

    ctx.setResponseType(MediaType.text);
    ctx.setResponseHeader(MetricsModule.CACHE_HEADER_NAME, MetricsModule.CACHE_HEADER_VALUE);

    return data;
  }
}
