/*
 * Jooby https://jooby.io
 * Apache License Version 2.0 https://jooby.io/LICENSE.txt
 * Copyright 2014 Edgar Espina
 */
package io.jooby.openapi;

import static io.jooby.openapi.OpenAPITestUtil.withResponse;
import static io.jooby.openapi.OpenAPITestUtil.withSchema;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Collections;

import examples.OpenApiApp;
import io.jooby.MediaType;
import io.jooby.internal.openapi.ResponseExt;

public class SwaggerAnnotationTest {

  @OpenAPITest(value = OpenApiApp.class, ignoreArguments = true)
  public void shouldParseSwaggerAnnotations(RouteIterator iterator) {
    iterator
        .next(
            route -> {
              assertEquals("foo", route.getOperationId());
              assertEquals(Collections.singletonList("a"), route.getTags());
              assertEquals("description", route.getDescription());
              assertEquals("summary", route.getSummary());

              ResponseExt response = route.getDefaultResponse();
              assertEquals("200", response.getCode());
              assertEquals("Success", response.getDescription());
              assertEquals("java.util.List<examples.Person>", response.getJavaType());

              ResponseExt notfound = route.getResponse("400");
              assertEquals("Bad Request", notfound.getDescription());
              assertEquals(null, notfound.getJavaType());
            })
        .next(
            route -> {
              assertEquals("find", route.getOperationId());
              assertEquals(null, route.getDescription());
              assertEquals("Find Person by ID", route.getSummary());

              withResponse(
                  route,
                  response -> {
                    assertEquals("200", response.getCode());
                    assertEquals("Found person", response.getDescription());
                    assertEquals("examples.Person", response.getJavaType());
                    assertNotNull(response.getHeaders());
                    assertEquals(
                        "string", response.getHeaders().get("Token").getSchema().getType());

                    withSchema(
                        response,
                        MediaType.JSON,
                        schema -> {
                          assertNotNull(schema);
                          assertEquals("#/components/schemas/Person", schema.get$ref());
                        });
                  });
            })
        .verify();
  }
}
