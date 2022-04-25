/**
 * Jooby https://jooby.io
 * Apache License Version 2.0 https://jooby.io/LICENSE.txt
 * Copyright 2014 Edgar Espina
 */
package io.jooby.exception;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.Parameter;
import java.util.StringJoiner;
import java.util.stream.Stream;

/**
 * Provisioning exception, throws by MVC routes when parameter binding fails.
 *
 * @since 2.0.0
 * @author edgar
 */
public class ProvisioningException extends BadRequestException {

  /**
   * Creates a provisioning exception.
   *
   * @param parameter Failing parameter.
   * @param cause Cause. Nullable.
   */
  public ProvisioningException(@Nonnull Parameter parameter, @Nullable Throwable cause) {
    this("Unable to provision parameter: '" + toString(parameter) + "', require by: " + toString(
        parameter.getDeclaringExecutable()), cause);
  }

  /**
   * Creates a provisioning exception.
   *
   * @param message Error message.
   * @param cause Cause.
   */
  public ProvisioningException(@Nonnull String message, @Nullable Throwable cause) {
    super(message, cause);
  }

  /**
   * Creates a parameter description.
   *
   * @param parameter Parameter.
   * @return Description.
   */
  public static String toString(@Nonnull Parameter parameter) {
    return parameter.getName() + ": " + parameter.getParameterizedType();
  }

  /**
   * Creates a method description.
   *
   * @param method Parameter.
   * @return Description.
   */
  public static String toString(@Nonnull Executable method) {
    StringBuilder buff = new StringBuilder();
    if (method instanceof Constructor) {
      buff.append("constructor ");
      buff.append(method.getDeclaringClass().getCanonicalName());
    } else {
      buff.append("method ");
      buff.append(method.getDeclaringClass().getCanonicalName());
      buff.append(".");
      buff.append(method.getName());
    }
    buff.append("(");
    StringJoiner params = new StringJoiner(", ");
    Stream.of(method.getGenericParameterTypes()).forEach(type -> params.add(type.getTypeName()));
    buff.append(params);
    buff.append(")");
    return buff.toString();
  }
}
