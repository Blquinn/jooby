package apps;

import io.jooby.App;
import io.jooby.Filters;
import io.jooby.Mode;
import io.jooby.netty.Netty;
import io.jooby.utow.Utow;

public class BenchApp extends App {

  private static final String MESSAGE = "Hello World!";

  static class Message {
    public final String message;

    public Message(String message) {
      this.message = message;
    }
  }

  {
    filter(Filters.defaultHeaders());

    get("/", ctx -> ctx.sendText(MESSAGE));

    get("/json", ctx -> Thread.currentThread().getName());

    get("/fortune", ctx -> Thread.currentThread().getName());
  }

  public static void main(String[] args) {
    new Netty()
        .deploy(new BenchApp().mode(Mode.IO))
        .start()
        .join();
  }
}