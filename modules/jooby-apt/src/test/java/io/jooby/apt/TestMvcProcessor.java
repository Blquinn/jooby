/*
 * Jooby https://jooby.io
 * Apache License Version 2.0 https://jooby.io/LICENSE.txt
 * Copyright 2014 Edgar Espina
 */
package io.jooby.apt;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.util.ASMifier;
import org.objectweb.asm.util.Printer;
import org.objectweb.asm.util.TraceClassVisitor;

import io.jooby.SneakyThrows;

public class TestMvcProcessor extends JoobyProcessor {
  private Map<String, Object> classes = new HashMap<>();
  private Map<String, Object> resources = new HashMap<>();

  @Override
  protected void onClass(String className, byte[] bytecode) {
    classes.put(className, bytecode);
  }

  @Override
  protected void onResource(String location, String content) {
    resources.put(location, content);
  }

  public ClassLoader getModuleClassLoader(boolean debug) {
    return new ClassLoader(getClass().getClassLoader()) {
      @Override
      protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] bytes = (byte[]) classes.get(name);
        if (bytes != null) {
          if (debug) {
            System.out.println(TestMvcProcessor.toString(bytes));
          }
          return defineClass(name, bytes, 0, bytes.length);
        }
        return super.findClass(name);
      }

      @Override
      protected URL findResource(String name) {
        try {
          Object content = resources.get(name);
          if (content != null) {
            Path resource =
                Paths.get(
                    "target", Long.toHexString(UUID.randomUUID().getMostSignificantBits()), name);
            if (Files.exists(resource)) {
              return resource.toUri().toURL();
            }
            Path parent = resource.getParent();
            if (!Files.exists(parent)) {
              Files.createDirectories(parent);
            }
            Files.write(resource, content.toString().getBytes(StandardCharsets.UTF_8));
            return resource.toUri().toURL();
          }
          return super.findResource(name);
        } catch (Exception x) {
          throw SneakyThrows.propagate(x);
        }
      }
    };
  }

  private static String toString(byte[] bytes) {
    try {
      ClassReader reader = new ClassReader(bytes);
      ByteArrayOutputStream buff = new ByteArrayOutputStream();
      Printer printer = new ASMifier();
      TraceClassVisitor traceClassVisitor =
          new TraceClassVisitor(null, printer, new PrintWriter(buff));

      reader.accept(traceClassVisitor, ClassReader.SKIP_DEBUG);

      return new String(buff.toByteArray());
    } catch (Exception x) {
      throw SneakyThrows.propagate(x);
    }
  }
}
