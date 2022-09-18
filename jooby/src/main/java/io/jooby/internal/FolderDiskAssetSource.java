/**
 * Jooby https://jooby.io
 * Apache License Version 2.0 https://jooby.io/LICENSE.txt
 * Copyright 2014 Edgar Espina
 */
package io.jooby.internal;

import io.jooby.Asset;
import io.jooby.AssetSource;

import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;
import java.nio.file.Files;
import java.nio.file.Path;

public class FolderDiskAssetSource implements AssetSource {
  private Path location;

  public FolderDiskAssetSource(@NonNull Path location) {
    this.location = location.normalize().toAbsolutePath();
  }

  @Nullable @Override public Asset resolve(@NonNull String path) {
    Path resource = location.resolve(path).normalize().toAbsolutePath();
    if (resource.startsWith(location)) {
      if (Files.isRegularFile(resource)) {
        return Asset.create(resource);
      } else if (Files.isDirectory(resource)) {
        Path index = resource.resolve("index.html");
        if (Files.isRegularFile(index)) {
          return Asset.create(index);
        }
      }
    }
    return null;
  }

  @Override public String toString() {
    return location.toString();
  }
}
