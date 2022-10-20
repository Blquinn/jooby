/*
 * Jooby https://jooby.io
 * Apache License Version 2.0 https://jooby.io/LICENSE.txt
 * Copyright 2014 Edgar Espina
 */
package source;

import io.jooby.Context;
import io.jooby.StatusCode;
import io.jooby.annotations.DELETE;
import io.jooby.annotations.POST;
import io.jooby.annotations.Path;

@Path("/1545")
@TopAnnotation(TopEnum.FOO)
public class Controller1545 {
  @DELETE
  public void voidDefault() {}

  @DELETE("/success")
  public void voidDeleteSuccess(Context ctx) {
    ctx.setResponseCode(StatusCode.OK);
  }

  @POST
  public void voidCreated(Context ctx) {
    ctx.setResponseCode(StatusCode.CREATED);
  }

  @POST("/novoid")
  public String novoidCreated(Context ctx) {
    ctx.setResponseCode(StatusCode.CREATED);
    return "OK";
  }
}
