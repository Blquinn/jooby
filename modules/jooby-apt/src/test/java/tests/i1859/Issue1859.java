package tests.i1859;

import io.jooby.test.MockContext;
import io.jooby.test.MockRouter;
import io.jooby.apt.MvcModuleCompilerRunner;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Issue1859 {

  @Test
  public void shouldGetNullOnMissingBody() throws Exception {
    new MvcModuleCompilerRunner(new C1859())
        .example(Expected1859.class)
        .module(app -> {
          MockRouter router = new MockRouter(app);
          MockContext ctx = new MockContext();
          ctx.setBody(new byte[0]);
          assertEquals("empty", router.post("/c/i1859", ctx).value().toString());
        });
  }

}
