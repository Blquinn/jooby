/*
 * Jooby https://jooby.io
 * Apache License Version 2.0 https://jooby.io/LICENSE.txt
 * Copyright 2014 Edgar Espina
 */
package io.jooby.internal.asm;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.WeakHashMap;

import org.apache.commons.io.IOUtils;

import io.jooby.SneakyThrows;

public class ClassSource {
  private Map<String, Object> bytecode = new WeakHashMap<>();
  private ClassLoader loader;

  public ClassSource(ClassLoader loader) {
    this.loader = loader;
  }

  public ClassLoader getLoader() {
    return loader;
  }

  public byte[] byteCode(Class source) {
    return (byte[])
        bytecode.computeIfAbsent(
            source.getName(),
            k -> {
              try (InputStream in = loader.getResourceAsStream(k.replace(".", "/") + ".class")) {
                return IOUtils.toByteArray(in);
              } catch (IOException x) {
                throw SneakyThrows.propagate(x);
              }
            });
  }

  public void destroy() {
    bytecode.clear();
    bytecode = null;
    loader = null;
  }
}
