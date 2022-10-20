/*
 * Jooby https://jooby.io
 * Apache License Version 2.0 https://jooby.io/LICENSE.txt
 * Copyright 2014 Edgar Espina
 */
package io.jooby.internal.pac4j;

import java.util.List;

import org.pac4j.core.authorization.authorizer.Authorizer;
import org.pac4j.core.context.WebContext;

import io.jooby.Registry;

public class ForwardingAuthorizer implements Authorizer {
  private Registry registry;

  private Class authorizerType;

  public ForwardingAuthorizer(Class authorizerType) {
    this.authorizerType = authorizerType;
  }

  @Override
  public boolean isAuthorized(WebContext context, List profiles) {
    return authorizer(authorizerType).isAuthorized(context, profiles);
  }

  private Authorizer authorizer(Class authorizerType) {
    return (Authorizer) registry.require(authorizerType);
  }

  public void setRegistry(Registry registry) {
    this.registry = registry;
  }
}
