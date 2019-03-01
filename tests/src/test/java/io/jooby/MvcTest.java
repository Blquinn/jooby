package io.jooby;

import io.jooby.internal.mvc.InstanceRouter;
import io.jooby.internal.mvc.NoTopLevelPath;
import io.jooby.internal.mvc.NullInjection;
import io.jooby.internal.mvc.Provisioning;
import okhttp3.FormBody;
import okhttp3.MultipartBody;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MvcTest {

  @Test
  public void routerInstance() {
    new JoobyRunner(app -> {

      app.use(new InstanceRouter());

    }).ready(client -> {
      client.get("/", rsp -> {
        assertEquals("Got it!", rsp.body().string());
      });

      client.post("/", rsp -> {
        assertEquals("Got it!", rsp.body().string());
      });

      client.get("/subpath", rsp -> {
        assertEquals("OK", rsp.body().string());
      });

      client.get("/void", rsp -> {
        assertEquals("", rsp.body().string());
        assertEquals(204, rsp.code());
      });
    });
  }

  @Test
  public void noTopLevelPath() {
    new JoobyRunner(app -> {

      app.use(new NoTopLevelPath());

    }).ready(client -> {
      client.get("/", rsp -> {
        assertEquals("root", rsp.body().string());
      });

      client.get("/subpath", rsp -> {
        assertEquals("subpath", rsp.body().string());
      });
    });
  }

  @Test
  public void provisioning() {
    new JoobyRunner(app -> {

      app.use(new Provisioning());

    }).ready(client -> {
      client.get("/args/ctx", rsp -> {
        assertEquals("/args/ctx", rsp.body().string());
      });

      client.get("/args/sendStatusCode", rsp -> {
        assertEquals("", rsp.body().string());
        assertEquals(201, rsp.code());
      });

      client.get("/args/file/foo.txt", rsp -> {
        assertEquals("foo.txt", rsp.body().string());
      });

      client.get("/args/int/678", rsp -> {
        assertEquals("678", rsp.body().string());
      });

      client.get("/args/foo/678/9/3.14/6.66/true", rsp -> {
        assertEquals("/args/foo/678/9/3.14/6.66/true", rsp.body().string());
      });

      client.get("/args/long/678", rsp -> {
        assertEquals("678", rsp.body().string());
      });

      client.get("/args/float/67.8", rsp -> {
        assertEquals("67.8", rsp.body().string());
      });

      client.get("/args/double/67.8", rsp -> {
        assertEquals("67.8", rsp.body().string());
      });

      client.get("/args/bool/false", rsp -> {
        assertEquals("false", rsp.body().string());
      });
      client.get("/args/str/*", rsp -> {
        assertEquals("*", rsp.body().string());
      });
      client.get("/args/list/*", rsp -> {
        assertEquals("[*]", rsp.body().string());
      });
      client.get("/args/custom/3.14", rsp -> {
        assertEquals("3.14", rsp.body().string());
      });

      client.get("/args/search?q=*", rsp -> {
        assertEquals("*", rsp.body().string());
      });

      client.get("/args/querystring?q=*", rsp -> {
        assertEquals("{q=*}", rsp.body().string());
      });

      client.get("/args/search-opt", rsp -> {
        assertEquals("Optional.empty", rsp.body().string());
      });

      client.header("foo", "bar");
      client.get("/args/header", rsp -> {
        assertEquals("bar", rsp.body().string());
      });

      client.post("/args/form", new FormBody.Builder().add("foo", "ab").build(), rsp -> {
        assertEquals("ab", rsp.body().string());
      });

      client.post("/args/formdata", new FormBody.Builder().add("foo", "ab").build(), rsp -> {
        assertEquals("{foo=ab}", rsp.body().string());
      });

      client.post("/args/multipart", new FormBody.Builder().add("foo", "ab").build(), rsp -> {
        assertEquals("{foo=ab}", rsp.body().string());
      });

      client.post("/args/multipart",
          new MultipartBody.Builder().setType(MultipartBody.FORM).addFormDataPart("foo", "...")
              .build(), rsp -> {
            assertEquals("{foo=...}", rsp.body().string());
          });

      client.post("/args/form",
          new MultipartBody.Builder().setType(MultipartBody.FORM).addFormDataPart("foo", "...")
              .build(), rsp -> {
            assertEquals("...", rsp.body().string());
          });
    });
  }

  @Test
  public void nullinjection() {
    new JoobyRunner(app -> {

      app.use(new NullInjection());

      app.error(ErrorHandler.log(app.log()).then((ctx, cause, statusCode) -> {
        ctx.statusCode(statusCode)
            .sendString(cause.getMessage());
      }));

    }).ready(client -> {
      client.get("/nonnull", rsp -> {
        assertEquals("Unable to provision parameter: 'v: int'", rsp.body().string());
      });
      client.get("/nullok", rsp -> {
        assertEquals("null", rsp.body().string());
      });

      client.get("/nullbean", rsp -> {
        assertEquals("Unable to provision parameter: 'foo: int', require by: constructor io.jooby.internal.mvc.NullInjection.QParam(int, java.lang.Integer)", rsp.body().string());
      });

      client.get("/nullbean?foo=foo", rsp -> {
        assertEquals("Unable to provision parameter: 'foo: int', require by: constructor io.jooby.internal.mvc.NullInjection.QParam(int, java.lang.Integer)", rsp.body().string());
      });

      client.get("/nullbean?foo=0&baz=baz", rsp -> {
        assertEquals("Unable to provision parameter: 'baz: int', require by: method io.jooby.internal.mvc.NullInjection.QParam.setBaz(int)", rsp.body().string());
      });
    });
  }
}
