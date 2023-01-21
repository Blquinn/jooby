package io.jooby.nima;

import edu.umd.cs.findbugs.annotations.NonNull;
import io.helidon.nima.webserver.WebServer;
import io.jooby.Jooby;
import io.jooby.Server;
import io.jooby.ServerOptions;
import io.jooby.SneakyThrows;
import io.jooby.internal.nima.RequestHandler;
import io.jooby.internal.nima.NoopRouting;

import java.net.BindException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HelidonNimaServer extends Server.Base {

    private ServerOptions options =
            new ServerOptions().setIoThreads(ServerOptions.IO_THREADS).setServer("nima");

    private List<Jooby> applications = new ArrayList<>();

    private WebServer server = null;

    ExecutorService worker = Executors.newThreadPerTaskExecutor(Thread.ofVirtual()
            .allowSetThreadLocals(true)
            // Make this configurable
            .inheritInheritableThreadLocals(true)
            .factory());

    @NonNull
    @Override
    public Server setOptions(@NonNull ServerOptions options) {
        this.options = options.setIoThreads(options.getIoThreads());
        return this;
    }

    @NonNull
    @Override
    public ServerOptions getOptions() {
        return options;
    }

    @NonNull
    @Override
    public Server start(@NonNull Jooby application) {
        try {
            applications.add(application);

            RequestHandler h = new RequestHandler(application, router);

            addShutdownHook();

            WebServer server = WebServer.builder()
                    .host(options.getHost())
                    .port(options.getPort())
                    // This seems bad because it throws an Exception before every request.
                    // Implement an empty router somehow?
                    .addRouting(new NoopRouting())
                    .directHandler(h)
                    .build();

            server.start();

            // TODO: configuration

//            HttpHandler handler =
//                    new UndertowHandler(
//                            applications.get(0),
//                            options.getBufferSize(),
//                            options.getMaxRequestSize(),
//                            options.getDefaultHeaders());
//
//            if (options.getCompressionLevel() != null) {
//                int compressionLevel = options.getCompressionLevel();
//                handler =
//                        new EncodingHandler(
//                                handler,
//                                new ContentEncodingRepository()
//                                        .addEncodingHandler("gzip", new GzipEncodingProvider(compressionLevel), _100)
//                                        .addEncodingHandler(
//                                                "deflate", new DeflateEncodingProvider(compressionLevel), _10));
//            }
//
//            if (options.isExpectContinue() == Boolean.TRUE) {
//                handler = new HttpContinueReadHandler(handler);
//            }
//
//            Undertow.Builder builder =
//                    Undertow.builder()
//                            .setBufferSize(options.getBufferSize())
//                            /** Socket : */
//                            .setSocketOption(Options.BACKLOG, BACKLOG)
//                            /** Server: */
//                            // HTTP/1.1 is keep-alive by default, turn this option off
//                            .setServerOption(UndertowOptions.ALWAYS_SET_KEEP_ALIVE, false)
//                            .setServerOption(UndertowOptions.ALLOW_EQUALS_IN_COOKIE_VALUE, true)
//                            .setServerOption(UndertowOptions.ALWAYS_SET_DATE, options.getDefaultHeaders())
//                            .setServerOption(UndertowOptions.RECORD_REQUEST_START_TIME, false)
//                            .setServerOption(UndertowOptions.DECODE_URL, false)
//                            /** Worker: */
//                            .setIoThreads(options.getIoThreads())
//                            .setWorkerOption(Options.WORKER_NAME, "worker")
//                            .setWorkerThreads(options.getWorkerThreads())
//                            .setHandler(handler);
//
//            if (!options.isHttpsOnly()) {
//                builder.addHttpListener(options.getPort(), options.getHost());
//            }
//
//            // HTTP @
//            builder.setServerOption(ENABLE_HTTP2, options.isHttp2() == Boolean.TRUE);
//
//            SSLContext sslContext = options.getSSLContext(application.getEnvironment().getClassLoader());
//            if (sslContext != null) {
//                builder.addHttpsListener(options.getSecurePort(), options.getHost(), sslContext);
//                SslOptions ssl = options.getSsl();
//                builder.setSocketOption(Options.SSL_ENABLED_PROTOCOLS, Sequence.of(ssl.getProtocol()));
//                Optional.ofNullable(options.getSsl())
//                        .map(SslOptions::getClientAuth)
//                        .map(this::toSslClientAuthMode)
//                        .ifPresent(
//                                clientAuth -> builder.setSocketOption(Options.SSL_CLIENT_AUTH_MODE, clientAuth));
//            } else if (options.isHttpsOnly()) {
//                throw new IllegalArgumentException(
//                        "Server configured for httpsOnly, but ssl options not set");
//            }
//
//            server = builder.build();
//            server.start();
            // NOT IDEAL, but we need to fire onStart after server.start to get access to Worker
            fireStart(applications, worker);

            fireReady(Collections.singletonList(application));

            return this;
        } catch (RuntimeException x) {
            Throwable sourceException = x;
            Throwable cause = Optional.ofNullable(x.getCause()).orElse(x);
            if (Server.isAddressInUse(cause)) {
                sourceException = new BindException("Address already in use: " + options.getPort());
            }
            throw SneakyThrows.propagate(sourceException);
        }
    }

    @NonNull
    @Override
    public Server stop() {
        try {
            fireStop(applications);
            applications = null;
        } catch (Exception x) {
            throw SneakyThrows.propagate(x);
        } finally {
            shutdownServer();
        }
        return this;
    }

    private void shutdownServer() {
        if (server != null) {
            try {
                server.stop();
            } finally {
                server = null;
            }
        }
    }
}
