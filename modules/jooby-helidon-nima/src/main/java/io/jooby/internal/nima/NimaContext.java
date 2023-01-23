package io.jooby.internal.nima;

import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;
import io.helidon.common.http.Http;
import io.helidon.common.http.ServerRequestHeaders;
import io.helidon.nima.webserver.http.ServerRequest;
import io.helidon.nima.webserver.http.ServerResponse;
import io.jooby.*;
import org.slf4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;
import java.security.cert.Certificate;
import java.util.*;
import java.util.concurrent.Executor;

//public class NimaContext implements Context {
//    private final ServerRequest request;
//    private final ServerResponse response;
//    private Route route;
//
//    public NimaContext(ServerRequest request, ServerResponse response) {
//        this.request = request;
//        this.response = response;
//    }
//
//    @NonNull
//    @Override
//    public Map<String, Object> getAttributes() {
//        return null;
//    }
//
//    @Nullable
//    @Override
//    public <T> T getAttribute(@NonNull String key) {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public Context setAttribute(@NonNull String key, Object value) {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public Router getRouter() {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public Object forward(@NonNull String path) {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public <T> T convert(@NonNull ValueNode value, @NonNull Class<T> type) {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public FlashMap flash() {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public Value flash(@NonNull String name) {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public Session session() {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public Value session(@NonNull String name) {
//        return null;
//    }
//
//    @Nullable
//    @Override
//    public Session sessionOrNull() {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public Value cookie(@NonNull String name) {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public Map<String, String> cookieMap() {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public String getMethod() {
//        return "GET";
//    }
//
//    @NonNull
//    @Override
//    public Context setMethod(@NonNull String method) {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public Route getRoute() {
//        return this.route;
//    }
//
//    @Override
//    public boolean matches(@NonNull String pattern) {
//        return false;
//    }
//
//    @NonNull
//    @Override
//    public Context setRoute(@NonNull Route route) {
//        this.route = route;
//        return this;
//    }
//
//    @NonNull
//    @Override
//    public String getRequestPath() {
//        return request.path().rawPath();
//    }
//
//    @NonNull
//    @Override
//    public Context setRequestPath(@NonNull String path) {
//        return null;
//    }
//
//    @NonNull @Override
//    public Value path(@NonNull String name) {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public <T> T path(@NonNull Class<T> type) {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public ValueNode path() {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public Map<String, String> pathMap() {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public Context setPathMap(@NonNull Map<String, String> pathMap) {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public QueryString query() {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public ValueNode query(@NonNull String name) {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public String queryString() {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public <T> T query(@NonNull Class<T> type) {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public Map<String, String> queryMap() {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public ValueNode header() {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public Value header(@NonNull String name) {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public Map<String, String> headerMap() {
//        return null;
//    }
//
//    @Override
//    public boolean accept(@NonNull MediaType contentType) {
//        return false;
//    }
//
//    @Nullable
//    @Override
//    public MediaType accept(@NonNull List<MediaType> produceTypes) {
//        return null;
//    }
//
//    @Nullable
//    @Override
//    public MediaType getRequestType() {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public MediaType getRequestType(MediaType defaults) {
//        return null;
//    }
//
//    @Override
//    public long getRequestLength() {
//        return 0;
//    }
//
//    @Nullable
//    @Override
//    public <T> T getUser() {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public Context setUser(@Nullable Object user) {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public String getRequestURL() {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public String getRequestURL(@NonNull String path) {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public String getRemoteAddress() {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public Context setRemoteAddress(@NonNull String remoteAddress) {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public String getHost() {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public Context setHost(@NonNull String host) {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public String getHostAndPort() {
//        return null;
//    }
//
//    @Override
//    public int getPort() {
//        return 0;
//    }
//
//    @NonNull
//    @Override
//    public Context setPort(int port) {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public String getProtocol() {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public List<Certificate> getClientCertificates() {
//        return null;
//    }
//
//    @Override
//    public int getServerPort() {
//        return 0;
//    }
//
//    @NonNull
//    @Override
//    public String getServerHost() {
//        return null;
//    }
//
//    @Override
//    public boolean isSecure() {
//        return false;
//    }
//
//    @NonNull
//    @Override
//    public String getScheme() {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public Context setScheme(@NonNull String scheme) {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public Formdata form() {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public ValueNode form(@NonNull String name) {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public <T> T form(@NonNull Class<T> type) {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public Map<String, String> formMap() {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public List<FileUpload> files() {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public List<FileUpload> files(@NonNull String name) {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public FileUpload file(@NonNull String name) {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public Body body() {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public <T> T body(@NonNull Class<T> type) {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public <T> T body(@NonNull Type type) {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public <T> T decode(@NonNull Type type, @NonNull MediaType contentType) {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public MessageDecoder decoder(@NonNull MediaType contentType) {
//        return null;
//    }
//
//    @Override
//    public boolean isInIoThread() {
//        return false;
//    }
//
//    @NonNull
//    @Override
//    public Context dispatch(@NonNull Runnable action) {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public Context dispatch(@NonNull Executor executor, @NonNull Runnable action) {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public Context detach(@NonNull Route.Handler next) throws Exception {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public Context upgrade(@NonNull WebSocket.Initializer handler) {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public Context upgrade(@NonNull ServerSentEmitter.Handler handler) {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public Context setResponseHeader(@NonNull String name, @NonNull Date value) {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public Context setResponseHeader(@NonNull String name, @NonNull Instant value) {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public Context setResponseHeader(@NonNull String name, @NonNull Object value) {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public Context setResponseHeader(@NonNull String name, @NonNull String value) {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public Context removeResponseHeader(@NonNull String name) {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public Context removeResponseHeaders() {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public Context setResponseLength(long length) {
//        return null;
//    }
//
//    @Nullable
//    @Override
//    public String getResponseHeader(@NonNull String name) {
//        return null;
//    }
//
//    @Override
//    public long getResponseLength() {
//        return 0;
//    }
//
//    @NonNull
//    @Override
//    public Context setResponseCookie(@NonNull Cookie cookie) {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public Context setResponseType(@NonNull String contentType) {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public Context setResponseType(@NonNull MediaType contentType) {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public Context setResponseType(@NonNull MediaType contentType, @Nullable Charset charset) {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public Context setDefaultResponseType(@NonNull MediaType contentType) {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public MediaType getResponseType() {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public Context setResponseCode(@NonNull StatusCode statusCode) {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public Context setResponseCode(int statusCode) {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public StatusCode getResponseCode() {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public Context render(@NonNull Object value) {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public OutputStream responseStream() {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public OutputStream responseStream(@NonNull MediaType contentType) {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public Context responseStream(@NonNull MediaType contentType, @NonNull SneakyThrows.Consumer<OutputStream> consumer) throws Exception {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public Context responseStream(@NonNull SneakyThrows.Consumer<OutputStream> consumer) throws Exception {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public Sender responseSender() {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public PrintWriter responseWriter() {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public PrintWriter responseWriter(@NonNull MediaType contentType) {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public PrintWriter responseWriter(@NonNull MediaType contentType, @Nullable Charset charset) {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public Context responseWriter(@NonNull SneakyThrows.Consumer<PrintWriter> consumer) throws Exception {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public Context responseWriter(@NonNull MediaType contentType, @NonNull SneakyThrows.Consumer<PrintWriter> consumer) throws Exception {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public Context responseWriter(@NonNull MediaType contentType, @Nullable Charset charset, @NonNull SneakyThrows.Consumer<PrintWriter> consumer) throws Exception {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public Context sendRedirect(@NonNull String location) {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public Context sendRedirect(@NonNull StatusCode redirect, @NonNull String location) {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public Context send(@NonNull String data) {
//        response.send(data);
//        return this;
//    }
//
//    @NonNull
//    @Override
//    public Context send(@NonNull String data, @NonNull Charset charset) {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public Context send(@NonNull byte[] data) {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public Context send(@NonNull ByteBuffer data) {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public Context send(@NonNull byte[]... data) {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public Context send(@NonNull ByteBuffer[] data) {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public Context send(@NonNull ReadableByteChannel channel) {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public Context send(@NonNull InputStream input) {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public Context send(@NonNull FileDownload file) {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public Context send(@NonNull Path file) {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public Context send(@NonNull FileChannel file) {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public Context send(@NonNull StatusCode statusCode) {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public Context sendError(@NonNull Throwable cause) {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public Context sendError(@NonNull Throwable cause, @NonNull StatusCode statusCode) {
//        return null;
//    }
//
//    @Override
//    public boolean isResponseStarted() {
//        return false;
//    }
//
//    @Override
//    public boolean getResetHeadersOnError() {
//        return false;
//    }
//
//    @NonNull
//    @Override
//    public Context setResetHeadersOnError(boolean value) {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public Context onComplete(@NonNull Route.Complete task) {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public <T> T require(@NonNull Class<T> type) throws RegistryException {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public <T> T require(@NonNull Class<T> type, @NonNull String name) throws RegistryException {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public <T> T require(@NonNull ServiceKey<T> key) throws RegistryException {
//        return null;
//    }
//}
public class NimaContext implements DefaultContext {

    private Route route;
    private final ServerRequest request;
    private final ServerResponse response;
    private final Router router;
    private QueryString query;
    private Formdata formdata;
    private ValueNode headers;
    private Map pathMap = Collections.EMPTY_MAP;
    private Map<String, Object> attributes;
    Body body;
    private MediaType responseType;
    private Map<String, String> cookies;
    private HashMap<String, String> responseCookies;
    private long responseLength = -1;
    private Boolean resetHeadersOnError;
    private String method;
    private String requestPath;
    private String remoteAddress;
    private String host;
    private int port;

    public NimaContext(ServerRequest request, ServerResponse response, Router router) {
        this.request = request;
        this.response = response;
        this.router = router;
        this.method = "GET"; // TODO: Get method.
        this.requestPath = request.path().rawPath();
    }

    boolean isHttpGet() {
        return this.method.length() == 3
                && this.method.charAt(0) == 'G'
                && this.method.charAt(1) == 'E'
                && this.method.charAt(2) == 'T';
    }

    @NonNull @Override
    public Router getRouter() {
        return router;
    }

    @NonNull @Override
    public Body body() {
        return body == null ? Body.empty(this) : body;
    }

    @Override
    public @NonNull Map<String, String> cookieMap() {
        if (this.cookies == null) {
            this.cookies = Collections.emptyMap();
        }
        return cookies;
    }

    @NonNull @Override
    public Map<String, Object> getAttributes() {
        if (attributes == null) {
            attributes = new HashMap<>();
        }
        return attributes;
    }

    @NonNull @Override
    public String getMethod() {
        return method;
    }

    @NonNull @Override
    public Context setMethod(@NonNull String method) {
        this.method = method.toUpperCase();
        return this;
    }

    @NonNull @Override
    public Route getRoute() {
        return route;
    }

    @NonNull @Override
    public Context setRoute(Route route) {
        this.route = route;
        return this;
    }

    @NonNull @Override
    public String getRequestPath() {
        return requestPath;
    }

    @NonNull @Override
    public Context setRequestPath(@NonNull String path) {
        this.requestPath = path;
        return this;
    }

    @NonNull @Override
    public Map<String, String> pathMap() {
        return pathMap;
    }

    @NonNull @Override
    public Context setPathMap(Map<String, String> pathMap) {
        this.pathMap = pathMap;
        return this;
    }

    @Override
    public boolean isInIoThread() {
        return false;
    }

    @NonNull @Override
    public String getHost() {
        return host == null ? DefaultContext.super.getHost() : host;
    }

    @NonNull @Override
    public Context setHost(@NonNull String host) {
        this.host = host;
        return this;
    }

    @NonNull @Override
    public String getRemoteAddress() {
        if (remoteAddress == null) {
            return Optional.ofNullable((InetSocketAddress) request.remotePeer().address())
                    .map(InetSocketAddress::getHostString)
                    .orElse("")
                    .trim();
        }
        return remoteAddress;
    }

    @NonNull @Override
    public Context setRemoteAddress(@NonNull String remoteAddress) {
        this.remoteAddress = remoteAddress;
        return this;
    }

    @NonNull @Override
    public String getProtocol() {
        // TODO: THis is wrong.
        return request.isSecure() ? "https" : "http";
    }

    @NonNull @Override
    public List<Certificate> getClientCertificates() {
        return List.of();
    }

    @NonNull @Override
    public String getScheme() {
        return getProtocol();
    }

    @NonNull @Override
    public Context setScheme(@NonNull String scheme) {
//        exchange.setRequestScheme(scheme);
        return this;
    }

    @Override
    public int getPort() {
        return port > 0 ? port : DefaultContext.super.getPort();
    }

    @NonNull @Override
    public Context setPort(int port) {
        this.port = port;
        return this;
    }

    @NonNull @Override
    public Value header(@NonNull String name) {
        var header = request.headers().get(Http.Header.create(name));
        return Value.create(this, name, header.value());
    }

    @NonNull @Override
    public ValueNode header() {
        ServerRequestHeaders map = request.headers();
        if (headers == null) {
            Map<String, Collection<String>> headerMap = new LinkedHashMap<>();
            for (Http.HeaderValue header : map) {
                headerMap.put(header.name(), header.allValues());
            }
            headers = Value.headers(this, headerMap);
        }
        return headers;
    }

    @NonNull @Override
    public QueryString query() {
        if (query == null) {
            query = QueryString.create(this, request.query().rawValue());
        }
        return query;
    }

    @NonNull @Override
    public Formdata form() {
//        if (formdata == null) {
//            formdata = Formdata.create(this);
//            formData(formdata, exchange.getAttachment(FORM_DATA));
//        }
        return formdata;
    }

    @NonNull @Override
    public Context dispatch(@NonNull Runnable action) {
        action.run();
        return this;
    }

    @NonNull @Override
    public Context dispatch(@NonNull Executor executor, @NonNull Runnable action) {
        action.run();
        return this;
    }

    @NonNull @Override
    public Context detach(@NonNull Route.Handler next) throws Exception {
        throw new UnsupportedOperationException();
    }

    @NonNull @Override
    public Context upgrade(@NonNull WebSocket.Initializer handler) {
        throw new UnsupportedOperationException();
    }

    @NonNull @Override
    public Context upgrade(@NonNull ServerSentEmitter.Handler handler) {
        throw new UnsupportedOperationException();
    }

    @NonNull @Override
    public StatusCode getResponseCode() {
        return StatusCode.valueOf(response.status().code());
    }

    @NonNull @Override
    public Context setResponseCode(int statusCode) {
        response.status(Http.Status.create(statusCode));
        return this;
    }

    @NonNull @Override
    public Context setResponseHeader(@NonNull String name, @NonNull String value) {
        response.header(name, value);
        return this;
    }

    @NonNull @Override
    public Context removeResponseHeader(@NonNull String name) {
        response.headers().remove(Http.Header.create(name));
        return this;
    }

    @NonNull @Override
    public Context removeResponseHeaders() {
        response.headers().clear();
        return this;
    }

    @NonNull @Override
    public MediaType getResponseType() {
        return responseType == null ? MediaType.text : responseType;
    }

    @NonNull @Override
    public Context setDefaultResponseType(@NonNull MediaType contentType) {
        if (responseType == null) {
            setResponseType(contentType, contentType.getCharset());
        }
        return this;
    }

    @NonNull @Override
    public Context setResponseType(@NonNull MediaType contentType, @Nullable Charset charset) {
        this.responseType = contentType;
        response.headers().set(Http.Header.CONTENT_TYPE, contentType.toContentTypeHeader(charset));
        return this;
    }

    @NonNull @Override
    public Context setResponseType(@NonNull String contentType) {
        this.responseType = MediaType.valueOf(contentType);
        response.headers().set(Http.Header.CONTENT_TYPE, contentType);
        return this;
    }

    @Nullable @Override
    public String getResponseHeader(@NonNull String name) {
        Http.HeaderValue header = response.headers().get(Http.Header.create(name));
        return header == null ? null : header.value();
    }

    @NonNull @Override
    public Context setResponseLength(long length) {
        responseLength = length;
        response.contentLength(length);
        return this;
    }

    @Override
    public long getResponseLength() {
//        if (responseLength == -1) {
//            return response.bytesWritten();
//        }
        return responseLength;
    }

    @NonNull public Context setResponseCookie(@NonNull Cookie cookie) {
        if (responseCookies == null) {
            responseCookies = new HashMap<>();
        }
        cookie.setPath(cookie.getPath(getContextPath()));
        responseCookies.put(cookie.getName(), cookie.toCookieString());
//        HeaderMap headers = exchange.getResponseHeaders();
//        headers.remove(SET_COOKIE);
//        for (String cookieString : responseCookies.values()) {
//            headers.add(SET_COOKIE, cookieString);
//        }
        return this;
    }

    @NonNull @Override
    public OutputStream responseStream() {
        ifSetChunked();

        return response.outputStream();
    }

    @NonNull @Override
    public io.jooby.Sender responseSender() {
        return new NimaSender(this, response);
    }

    @NonNull @Override
    public PrintWriter responseWriter(MediaType type, Charset charset) {
        setResponseType(type, charset);
        ifSetChunked();
        return new PrintWriter(new NimaWriter(response.outputStream(), charset));
    }

    @NonNull @Override
    public Context send(@NonNull byte[] data) {
        return send(ByteBuffer.wrap(data));
    }

    @NonNull @Override
    public Context send(@NonNull ReadableByteChannel channel) {
        ifSetChunked();
        ByteBuffer buf = ByteBuffer.allocate(12<<10);

        try {
            while (channel.read(buf) > 0) {
                response.send(buf.array());
                buf.clear();
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        return this;
    }

    @NonNull @Override
    public Context send(@NonNull String data, @NonNull Charset charset) {
        return send(ByteBuffer.wrap(data.getBytes(charset)));
    }

    @NonNull @Override
    public Context send(@NonNull ByteBuffer[] data) {
        if (!response.headers().contains(Http.Header.CONTENT_LENGTH)) {
            long len = 0;
            for (ByteBuffer b : data) {
                len += b.remaining();
            }
            response.header(Http.Header.create(Http.Header.CONTENT_LENGTH, len));
        }

        for (ByteBuffer buf : data) {
            response.send(buf.array());
        }

        return this;
    }

    @NonNull @Override
    public Context send(@NonNull ByteBuffer data) {
        response.header(Http.Header.create(Http.Header.CONTENT_LENGTH, data.remaining()));
        response.send(data.array());
        return this;
    }

    @NonNull @Override
    public Context send(StatusCode statusCode) {
        response.status(Http.Status.create(statusCode.value()));
        response.send();
        return this;
    }

    @NonNull @Override
    public Context send(@NonNull InputStream in) {
        throw new UnsupportedOperationException();
    }

    @NonNull @Override
    public Context send(@NonNull FileChannel file) {
         throw new UnsupportedOperationException();
//        try {
//            long len = file.size();
////            exchange.(len);
//            response.contentLength(len);
//            ByteRange range =
//                    ByteRange.parse(exchange.getRequestHeaders().getFirst(RANGE), len).apply(this);
//            file.position(range.getStart());
//            new UndertowChunkedStream(range.getEnd()).send(file, exchange, this);
//            return this;
//        } catch (IOException x) {
//            throw SneakyThrows.propagate(x);
//        }
    }

    @Override
    public boolean isResponseStarted() {
        return response.bytesWritten() > 0;
    }

    @Override
    public boolean getResetHeadersOnError() {
        return resetHeadersOnError == null
                ? getRouter().getRouterOptions().contains(RouterOption.RESET_HEADERS_ON_ERROR)
                : resetHeadersOnError;
    }

    @Override
    public Context setResetHeadersOnError(boolean value) {
        this.resetHeadersOnError = value;
        return this;
    }

//    @Override
//    public void onComplete(HttpServerExchange exchange, Sender sender) {
//        ifSaveSession();
//        this.exchange.endExchange();
//    }

    @NonNull @Override
    public Context onComplete(@NonNull Route.Complete task) {
        response.whenSent(() -> {
            try {
                task.apply(this);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return this;
    }
//
//    @Override
//    public void onException(HttpServerExchange exchange, Sender sender, IOException exception) {
//        destroy(exception);
//    }
//
//    @Override
//    public String toString() {
//        return getMethod() + " " + getRequestPath();
//    }
//
//    private void ifSaveSession() {
//        if (attributes != null) {
//            Session session = (Session) attributes.get(Session.NAME);
//            if (session != null && (session.isNew() || session.isModify())) {
//                SessionStore store = router.getSessionStore();
//                store.saveSession(this, session);
//            }
//        }
//    }
//
    void destroy(Exception cause) {
        try {
            if (cause != null) {
                Logger log = router.getLog();
                if (Server.connectionLost(cause)) {
                    log.debug(
                            "exception found while sending response {} {}", getMethod(), getRequestPath(), cause);
                } else {
                    log.error(
                            "exception found while sending response {} {}", getMethod(), getRequestPath(), cause);
                }
            }
        } finally {
            try {
                response.outputStream().close();
            } catch (IOException e) {
                router.getLog().error(
                        "exception found while closing output stream {} {}", getMethod(), getRequestPath(), e);
            }
        }
    }
//
//    private void formData(Formdata form, FormData data) {
//        if (data != null) {
//            Iterator<String> it = data.iterator();
//            while (it.hasNext()) {
//                String path = it.next();
//                Deque<FormData.FormValue> values = data.get(path);
//                for (FormData.FormValue value : values) {
//                    if (value.isFileItem()) {
//                        ((Formdata) form).put(path, new UndertowFileUpload(path, value));
//                    } else {
//                        form.put(path, value.getValue());
//                    }
//                }
//            }
//        }
//    }
//
    private void ifSetChunked() {
        if (response.headers().contains(Http.Header.CONTENT_LENGTH)) {
            response.header(Http.HeaderValues.TRANSFER_ENCODING_CHUNKED);
        }
    }
}
