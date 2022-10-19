package io.jooby.pebble;

import com.mitchellbosecke.pebble.PebbleEngine;
import com.typesafe.config.ConfigFactory;
import io.jooby.Environment;
import io.jooby.Jooby;
import io.jooby.test.MockContext;
import io.jooby.ModelAndView;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.JRE;

import java.nio.file.Paths;
import java.util.Collections;
import java.util.Locale;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;

// FIXME: required until https://github.com/PebbleTemplates/pebble/issues/487
@EnabledOnJre({JRE.JAVA_8, JRE.JAVA_9, JRE.JAVA_10, JRE.JAVA_11, JRE.JAVA_12})
public class PebbleModuleTest {
  public static class User {
    private String firstname;

    private String lastname;

    public User(String firstname, String lastname) {
      this.firstname = firstname;
      this.lastname = lastname;
    }

    public String getFirstname() {
      return firstname;
    }

    public String getLastname() {
      return lastname;
    }
  }

  @Test
  public void render() throws Exception {
    PebbleEngine.Builder builder = PebbleModule.create()
        .build(new Environment(getClass().getClassLoader(), ConfigFactory.empty()));
    PebbleTemplateEngine engine = new PebbleTemplateEngine(builder,
        Collections.singletonList(".peb"));
    MockContext ctx = new MockContext().setRouter(new Jooby().setLocales(singletonList(Locale.ENGLISH)));
    ctx.getAttributes().put("local", "var");
    String output = engine
        .render(ctx, new ModelAndView("index.peb")
            .put("user", new User("foo", "bar"))
            .put("sign", "!"));
    assertEquals("Hello foo bar var!", output);
  }

  @Test
  public void renderFileSystem() throws Exception {
    PebbleEngine.Builder builder = PebbleModule.create()
        .setTemplatesPath(Paths.get("src", "test", "resources", "views").toString())
        .build(new Environment(getClass().getClassLoader(), ConfigFactory.empty()));
    PebbleTemplateEngine engine = new PebbleTemplateEngine(builder,
        Collections.singletonList(".peb"));
    MockContext ctx = new MockContext().setRouter(new Jooby().setLocales(singletonList(Locale.ENGLISH)));
    ctx.getAttributes().put("local", "var");
    String output = engine
        .render(ctx, new ModelAndView("index.peb")
            .put("user", new User("foo", "bar"))
            .put("sign", "!"));
    assertEquals("Hello foo bar var!", output);
  }

  @Test
  public void renderWithLocale() throws Exception {
    PebbleEngine.Builder builder = PebbleModule.create()
        .build(new Environment(getClass().getClassLoader(), ConfigFactory.empty()));
    PebbleTemplateEngine engine = new PebbleTemplateEngine(builder,
        Collections.singletonList(".peb"));
    MockContext ctx = new MockContext().setRouter(new Jooby().setLocales(singletonList(Locale.ENGLISH)));

    assertEquals("Greetings!", engine.render(ctx, new ModelAndView("locales.peb")));

    assertEquals("Hi!", engine.render(ctx, new ModelAndView("locales.peb")
        .setLocale(new Locale("en", "GB"))));

    assertEquals("Grüß Gott!", engine.render(ctx, new ModelAndView("locales.peb")
        .setLocale(Locale.GERMAN)));

    assertEquals("Grüß Gott!", engine.render(ctx, new ModelAndView("locales.peb")
        .setLocale(Locale.GERMANY)));

    assertEquals("Servus!", engine.render(ctx, new ModelAndView("locales.peb")
        .setLocale(new Locale("de", "AT"))));
  }
}
