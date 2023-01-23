/*
 * Jooby https://jooby.io
 * Apache License Version 2.0 https://jooby.io/LICENSE.txt
 * Copyright 2014 Edgar Espina
 */

import io.jooby.Server;
import io.jooby.nima.HelidonNimaServer;

module io.jooby.nima {
    exports io.jooby.nima;

    requires io.jooby;
    requires com.github.spotbugs.annotations;
    requires typesafe.config;
    requires org.slf4j;
    requires io.helidon.nima.webserver;

    provides Server with HelidonNimaServer;
}
