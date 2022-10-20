/*
 * Jooby https://jooby.io
 * Apache License Version 2.0 https://jooby.io/LICENSE.txt
 * Copyright 2014 Edgar Espina
 */
package io.jooby.openapi;

import java.util.function.Consumer;

import io.jooby.internal.openapi.OperationExt;
import io.jooby.internal.openapi.ResponseExt;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.Schema;

public class OpenAPITestUtil {

  public static void withResponse(OperationExt operation, Consumer<ResponseExt> consumer) {
    consumer.accept(operation.getDefaultResponse());
  }

  public static void withContent(ResponseExt response, Consumer<Content> consumer) {
    consumer.accept(response.getContent());
  }

  public static void withSchema(ResponseExt response, String mediaType, Consumer<Schema> consumer) {
    withSchema(response.getContent(), mediaType, consumer);
  }

  public static void withSchema(Content content, String mediaType, Consumer<Schema> consumer) {
    consumer.accept(content.get(mediaType).getSchema());
  }
}
