/*
 * Jooby https://jooby.io
 * Apache License Version 2.0 https://jooby.io/LICENSE.txt
 * Copyright 2014 Edgar Espina
 */
package source;

import java.util.Objects;

public class Bean {
  private final int id;

  public Bean(int id) {
    this.id = id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Bean bean = (Bean) o;
    return id == bean.id;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
