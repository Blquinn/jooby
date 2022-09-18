package io.jooby.junit;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import io.jooby.ExecutionMode;

import java.lang.reflect.Method;

public class ServerParameterResolver implements ParameterResolver {

  private final ServerProvider server;

  private final ExecutionMode executionMode;

  public ServerParameterResolver(ServerProvider server, ExecutionMode executionMode) {
    this.server = server;
    this.executionMode = executionMode;
  }

  @Override public boolean supportsParameter(ParameterContext parameterContext,
      ExtensionContext extensionContext) throws ParameterResolutionException {
    return parameterContext.getParameter().getType() == ServerTestRunner.class;
  }

  @Override public Object resolveParameter(ParameterContext parameterContext,
      ExtensionContext extensionContext) throws ParameterResolutionException {
    Method test = extensionContext.getRequiredTestMethod();
    return new ServerTestRunner(test.getDeclaringClass().getSimpleName() + "." + test.getName(), server, executionMode);
  }
}
