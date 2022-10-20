/*
 * Jooby https://jooby.io
 * Apache License Version 2.0 https://jooby.io/LICENSE.txt
 * Copyright 2014 Edgar Espina
 */
package io.jooby.internal.converter;

import java.util.TimeZone;

import io.jooby.Value;
import io.jooby.ValueConverter;

public class TimeZoneConverter implements ValueConverter {
  @Override
  public boolean supports(Class type) {
    return type == TimeZone.class;
  }

  @Override
  public Object convert(Value value, Class type) {
    return TimeZone.getTimeZone(value.value());
  }
}
