/*
 * Jooby https://jooby.io
 * Apache License Version 2.0 https://jooby.io/LICENSE.txt
 * Copyright 2014 Edgar Espina
 */
package io.jooby;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DefaultContextTest {
  private final DefaultContext ctx = mock(DefaultContext.class);

  @BeforeEach
  public void setUp() {
    when(ctx.getScheme()).thenReturn("https");
    when(ctx.getHost()).thenReturn("some-host");
    when(ctx.getPort()).thenReturn(443);
    when(ctx.getContextPath()).thenReturn("/");
    when(ctx.getRequestPath()).thenReturn("/path");
    when(ctx.queryString()).thenReturn("?query");

    when(ctx.getRequestURL()).thenCallRealMethod();
    when(ctx.getRequestURL(any())).thenCallRealMethod();
  }

  @Test
  public void getRequestURL() {
    assertEquals("https://some-host/path?query", ctx.getRequestURL());
  }

  @Test
  public void getRequestURL_withContextPath() {
    when(ctx.getContextPath()).thenReturn("/context");
    assertEquals("https://some-host/context/path?query", ctx.getRequestURL());
  }

  @Test
  public void getRequestURL_withNonStandardPort() {
    when(ctx.getPort()).thenReturn(999);
    assertEquals("https://some-host:999/path?query", ctx.getRequestURL());
  }

  @Test
  public void getRequestURL_withCustomPathWithoutQueryString() {
    assertEquals("https://some-host/my-path", ctx.getRequestURL("/my-path"));
  }
}
