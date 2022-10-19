package tests.i2417;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import io.jooby.test.MockContext;
import io.jooby.test.MockRouter;
import io.jooby.apt.MvcModuleCompilerRunner;

public class Issue2417 {
  @Test
  public void shouldNotIgnoreAnnotationOnParam() throws Exception {
    new MvcModuleCompilerRunner(new C2417())
        .module(app -> {
          MockRouter router = new MockRouter(app);
          assertEquals("2417", router.get("/2417", new MockContext()
              .setQueryString("?name=2417")).value());
        });
  }
}

