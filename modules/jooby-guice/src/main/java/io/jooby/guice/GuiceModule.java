/*
 * Jooby https://jooby.io
 * Apache License Version 2.0 https://jooby.io/LICENSE.txt
 * Copyright 2014 Edgar Espina
 */
package io.jooby.guice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.Stage;
import edu.umd.cs.findbugs.annotations.NonNull;
import io.jooby.Environment;
import io.jooby.Extension;
import io.jooby.Jooby;

/**
 * Guice module: https://jooby.io/modules/guice.
 *
 * <p>Jooby integrates the {@link io.jooby.ServiceRegistry} into the Guice framework.
 *
 * <p>Usage:
 *
 * <pre>{@code
 * {
 *
 *
 *   install(new GuiceModule());
 *
 * }
 *
 * }</pre>
 *
 * Require calls are going to be resolved by Guice now.
 *
 * @author edgar
 * @since 2.0.0
 */
public class GuiceModule implements Extension {

  private Injector injector;

  private List<Module> modules = new ArrayList<>();

  /**
   * Creates a new guice module using the given injector.
   *
   * @param injector Injector to use.
   */
  public GuiceModule(@NonNull Injector injector) {
    this.injector = injector;
  }

  /**
   * Creates a new guice module.
   *
   * @param modules Module to add.
   */
  public GuiceModule(@NonNull Module... modules) {
    Stream.of(modules).forEach(this.modules::add);
  }

  @Override
  public boolean lateinit() {
    return true;
  }

  @Override
  public void install(@NonNull Jooby application) {
    if (injector == null) {
      Environment env = application.getEnvironment();
      modules.add(new JoobyModule(application));
      Stage stage = env.isActive("dev", "test") ? Stage.DEVELOPMENT : Stage.PRODUCTION;
      injector = Guice.createInjector(stage, modules);
    }
    application.registry(new GuiceRegistry(injector));
  }
}
