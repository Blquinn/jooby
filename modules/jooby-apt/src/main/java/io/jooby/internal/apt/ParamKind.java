/*
 * Jooby https://jooby.io
 * Apache License Version 2.0 https://jooby.io/LICENSE.txt
 * Copyright 2014 Edgar Espina
 */
package io.jooby.internal.apt;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Set;

import javax.lang.model.type.TypeMirror;

import io.jooby.Context;
import io.jooby.Formdata;
import io.jooby.ParamSource;
import io.jooby.apt.Annotations;
import io.jooby.internal.apt.asm.BodyWriter;
import io.jooby.internal.apt.asm.ContextParamWriter;
import io.jooby.internal.apt.asm.FileUploadWriter;
import io.jooby.internal.apt.asm.NamedParamWriter;
import io.jooby.internal.apt.asm.ObjectTypeWriter;
import io.jooby.internal.apt.asm.ParamLookupWriter;
import io.jooby.internal.apt.asm.ParamWriter;

public enum ParamKind {
  TYPE {
    @Override
    public Method valueObject(ParamDefinition param) {
      throw new UnsupportedOperationException(param.toString());
    }

    @Override
    public ParamWriter newWriter() {
      return new ObjectTypeWriter();
    }
  },

  FILE_UPLOAD {
    @Override
    public Method valueObject(ParamDefinition param) throws NoSuchMethodException {
      throw new UnsupportedOperationException(param.toString());
    }

    @Override
    public ParamWriter newWriter() {
      return new FileUploadWriter();
    }
  },

  PATH_PARAM {
    @Override
    public Set<String> annotations() {
      return Annotations.PATH_PARAMS;
    }

    @Override
    public Method valueObject(ParamDefinition param) throws NoSuchMethodException {
      return Context.class.getDeclaredMethod("path");
    }

    @Override
    public Method singleValue(ParamDefinition param) throws NoSuchMethodException {
      return Context.class.getDeclaredMethod("path", String.class);
    }

    @Override
    public ParamWriter newWriter() {
      return new NamedParamWriter();
    }
  },

  CONTEXT_PARAM {
    @Override
    public Set<String> annotations() {
      return Annotations.CONTEXT_PARAMS;
    }

    @Override
    public Method valueObject(ParamDefinition param) throws NoSuchMethodException {
      return Context.class.getDeclaredMethod("getAttributes");
    }

    @Override
    public Method singleValue(ParamDefinition param) throws NoSuchMethodException {
      return Context.class.getDeclaredMethod("getAttribute", String.class);
    }

    @Override
    public ParamWriter newWriter() {
      return new ContextParamWriter();
    }
  },
  SESSION_ATTRIBUTE_PARAM {
    @Override
    public Set<String> annotations() {
      return Annotations.SESSION_PARAMS;
    }

    @Override
    public Method valueObject(ParamDefinition param) throws NoSuchMethodException {
      if (param.isOptional()) {
        return Context.class.getDeclaredMethod("sessionOrNull");
      }
      return Context.class.getDeclaredMethod("session");
    }

    @Override
    public Method singleValue(ParamDefinition param) throws NoSuchMethodException {
      return Context.class.getDeclaredMethod("session", String.class);
    }

    @Override
    public ParamWriter newWriter() {
      return new NamedParamWriter();
    }
  },
  QUERY_PARAM {
    @Override
    public Set<String> annotations() {
      return Annotations.QUERY_PARAMS;
    }

    @Override
    public Method valueObject(ParamDefinition param) throws NoSuchMethodException {
      return Context.class.getDeclaredMethod("query");
    }

    @Override
    public Method singleValue(ParamDefinition param) throws NoSuchMethodException {
      return Context.class.getDeclaredMethod("query", String.class);
    }

    @Override
    public ParamWriter newWriter() {
      return new NamedParamWriter();
    }
  },
  COOKIE_PARAM {
    @Override
    public Set<String> annotations() {
      return Annotations.COOKIE_PARAMS;
    }

    @Override
    public Method valueObject(ParamDefinition param) throws NoSuchMethodException {
      throw new UnsupportedOperationException();
    }

    @Override
    public Method singleValue(ParamDefinition param) throws NoSuchMethodException {
      return Context.class.getDeclaredMethod("cookie", String.class);
    }

    @Override
    public ParamWriter newWriter() {
      return new NamedParamWriter();
    }
  },
  HEADER_PARAM {
    @Override
    public Set<String> annotations() {
      return Annotations.HEADER_PARAMS;
    }

    @Override
    public Method valueObject(ParamDefinition param) throws NoSuchMethodException {
      throw new UnsupportedOperationException();
    }

    @Override
    public Method singleValue(ParamDefinition param) throws NoSuchMethodException {
      return Context.class.getDeclaredMethod("header", String.class);
    }

    @Override
    public ParamWriter newWriter() {
      return new NamedParamWriter();
    }
  },
  FLASH_PARAM {
    @Override
    public Set<String> annotations() {
      return Annotations.FLASH_PARAMS;
    }

    @Override
    public Method valueObject(ParamDefinition param) throws NoSuchMethodException {
      return Context.class.getDeclaredMethod("flash");
    }

    @Override
    public Method singleValue(ParamDefinition param) throws NoSuchMethodException {
      return Context.class.getDeclaredMethod("flash", String.class);
    }

    @Override
    public ParamWriter newWriter() {
      return new NamedParamWriter();
    }
  },
  FORM_PARAM {
    @Override
    public Set<String> annotations() {
      return Annotations.FORM_PARAMS;
    }

    @Override
    public Method valueObject(ParamDefinition param) throws NoSuchMethodException {
      return Context.class.getDeclaredMethod("form");
    }

    @Override
    public Method singleValue(ParamDefinition param) throws NoSuchMethodException {
      return Context.class.getDeclaredMethod("form", String.class);
    }

    @Override
    public ParamWriter newWriter() {
      return new NamedParamWriter();
    }
  },
  PARAM_LOOKUP {
    @Override
    public Set<String> annotations() {
      return Annotations.PARAM_LOOKUP;
    }

    @Override
    public Method singleValue(ParamDefinition param) throws NoSuchMethodException {
      return Context.class.getDeclaredMethod("lookup", String.class, ParamSource[].class);
    }

    @Override
    public ParamWriter newWriter() {
      return new ParamLookupWriter();
    }

    @Override
    public String httpNameMemberName() {
      return "name";
    }
  },

  ROUTE_PARAM {
    @Override
    public Set<String> annotations() {
      return Collections.emptySet();
    }

    @Override
    public Method valueObject(ParamDefinition param) throws NoSuchMethodException {
      return Context.class.getDeclaredMethod("getRoute");
    }
  },

  BODY_PARAM {
    @Override
    public Method valueObject(ParamDefinition param) throws NoSuchMethodException {
      return Context.class.getDeclaredMethod("body");
    }

    @Override
    public ParamWriter newWriter() {
      return new BodyWriter();
    }
  };

  public Set<String> annotations() {
    return Collections.emptySet();
  }

  public ParamWriter newWriter() {
    throw new UnsupportedOperationException();
  }

  public Method valueObject(ParamDefinition param) throws NoSuchMethodException {
    throw new UnsupportedOperationException("No value object method for: '" + param + "'");
  }

  public Method singleValue(ParamDefinition param) throws NoSuchMethodException {
    throw new UnsupportedOperationException("No single value method for: '" + param + "'");
  }

  public String httpNameMemberName() {
    return "value";
  }

  public static ParamKind forTypeInjection(ParamDefinition param) {
    TypeMirror type =
        param.isOptional()
            ? param.getType().getArguments().get(0).getRawType()
            : param.getType().getRawType();
    String rawType = type.toString().replace(Formdata.class.getName(), Formdata.class.getName());
    for (ParamKind value : values()) {
      try {
        if (value.valueObject(param).getReturnType().getName().equals(rawType)) {
          return value;
        }
      } catch (NoSuchMethodException | UnsupportedOperationException x) {
        // ignored it
      }
    }
    throw new UnsupportedOperationException("No type injection for: '" + param + "'");
  }
}
