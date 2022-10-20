/*
 * Jooby https://jooby.io
 * Apache License Version 2.0 https://jooby.io/LICENSE.txt
 * Copyright 2014 Edgar Espina
 */
package io.jooby;

/**
 * Execution mode.
 *
 * @author edgar
 */
public enum ExecutionMode {
  /**
   * Execute route handler in the event loop thread (non-blocking). Handler must never block.
   *
   * <p>Examples:
   *
   * <pre>{@code
   * {
   *   mode(EVENT_LOOP);
   *
   *   get("/non-blocking", ctx -> "I'm running on event-loop thread (no blocking allowed)");
   *
   *   // Dispatch to worker thread, blocking routes
   *   dispatch(() -> {
   *
   *     get("/blocking", ctx -> {
   *       // remote call: service, database, etc..
   *       return "Safe to block";
   *     });
   *   });
   * }
   * }</pre>
   */
  EVENT_LOOP,

  /**
   * Execute handler in a worker/io thread (blocking). Handler is allowed to block.
   *
   * <p>Examples:
   *
   * <pre>{@code
   * {
   *
   *   mode(WORKER);
   *
   *   get("/worker", ctx -> {
   *     // remote call: another service, database, etc..
   *     return "Safe to block";
   *   });
   * }
   *
   * }</pre>
   */
  WORKER,

  /**
   * Default execution mode.
   *
   * <p>Automatically choose between {@link ExecutionMode#EVENT_LOOP} and {@link
   * ExecutionMode#WORKER}.
   *
   * <p>If route handler returns a `reactive` type, then Jooby run the route handler in the
   * event-loop thread. Otherwise, run the handler in the worker thread.
   *
   * <p>A reactive type is one of:
   *
   * <p>- {@link java.util.concurrent.CompletableFuture}. - A reactive stream Publisher - Rx types:
   * Observable, Flowable, Single, Maybe, etc.. - Reactor types: Flux and Mono.
   *
   * <p>Examples:
   *
   * <pre>{@code
   * {
   *
   *   get("/non-blocking", ctx -> {
   *     return CompletableFuture.supplyAsync(() -> {
   *       return "I'm non-blocking";
   *     });
   *   });
   *
   *   get("/blocking", ctx -> {
   *     return "I'm blocking";
   *   });
   * }
   * }</pre>
   */
  DEFAULT
}
