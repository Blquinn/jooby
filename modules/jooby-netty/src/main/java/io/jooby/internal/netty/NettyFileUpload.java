/*
 * Jooby https://jooby.io
 * Apache License Version 2.0 https://jooby.io/LICENSE.txt
 * Copyright 2014 Edgar Espina
 */
package io.jooby.internal.netty;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import edu.umd.cs.findbugs.annotations.NonNull;
import io.jooby.FileUpload;
import io.jooby.SneakyThrows;
import io.netty.buffer.ByteBufInputStream;
import io.netty.handler.codec.http.multipart.DiskFileUpload;

public class NettyFileUpload implements FileUpload {
  private final io.netty.handler.codec.http.multipart.FileUpload upload;
  private final Path basedir;
  private Path path;

  public NettyFileUpload(Path basedir, io.netty.handler.codec.http.multipart.FileUpload upload) {
    this.basedir = basedir;
    this.upload = upload;
  }

  @NonNull @Override
  public String getName() {
    return upload.getName();
  }

  @Override
  public byte[] bytes() {
    try {
      if (upload.isInMemory()) {
        return upload.get();
      }
      return Files.readAllBytes(path());
    } catch (IOException x) {
      throw SneakyThrows.propagate(x);
    }
  }

  @Override
  public InputStream stream() {
    try {
      if (upload.isInMemory()) {
        return new ByteBufInputStream(upload.content(), true);
      }
      return Files.newInputStream(path());
    } catch (IOException x) {
      throw SneakyThrows.propagate(x);
    }
  }

  @Override
  public String getFileName() {
    return upload.getFilename();
  }

  @Override
  public String getContentType() {
    return upload.getContentType();
  }

  @Override
  public long getFileSize() {
    return upload.length();
  }

  @Override
  public Path path() {
    try {
      if (path == null) {
        if (upload.isInMemory()) {
          path =
              basedir.resolve(DiskFileUpload.prefix + System.nanoTime() + DiskFileUpload.postfix);
          upload.renameTo(path.toFile());
          upload.release();
        } else {
          path = upload.getFile().toPath();
        }
      }
      return path;
    } catch (IOException x) {
      throw SneakyThrows.propagate(x);
    }
  }

  @Override
  public void close() {
    try {
      if (upload.refCnt() > 0) {
        upload.release();
      }
      if (path != null) {
        Files.deleteIfExists(path);
      }

    } catch (IOException x) {
      throw SneakyThrows.propagate(x);
    }
  }

  @Override
  public String toString() {
    return getFileName();
  }
}
