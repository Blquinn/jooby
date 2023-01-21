package io.jooby.internal.nima;

import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;
import io.jooby.*;
import io.jooby.exception.RegistryException;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.security.cert.Certificate;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

public class NimaContext implements Context {
    @NonNull
    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }

    @Nullable
    @Override
    public <T> T getAttribute(@NonNull String key) {
        return null;
    }

    @NonNull
    @Override
    public Context setAttribute(@NonNull String key, Object value) {
        return null;
    }

    @NonNull
    @Override
    public Router getRouter() {
        return null;
    }

    @NonNull
    @Override
    public Object forward(@NonNull String path) {
        return null;
    }

    @NonNull
    @Override
    public <T> T convert(@NonNull ValueNode value, @NonNull Class<T> type) {
        return null;
    }

    @NonNull
    @Override
    public FlashMap flash() {
        return null;
    }

    @NonNull
    @Override
    public Value flash(@NonNull String name) {
        return null;
    }

    @NonNull
    @Override
    public Session session() {
        return null;
    }

    @NonNull
    @Override
    public Value session(@NonNull String name) {
        return null;
    }

    @Nullable
    @Override
    public Session sessionOrNull() {
        return null;
    }

    @NonNull
    @Override
    public Value cookie(@NonNull String name) {
        return null;
    }

    @NonNull
    @Override
    public Map<String, String> cookieMap() {
        return null;
    }

    @NonNull
    @Override
    public String getMethod() {
        return null;
    }

    @NonNull
    @Override
    public Context setMethod(@NonNull String method) {
        return null;
    }

    @NonNull
    @Override
    public Route getRoute() {
        return null;
    }

    @Override
    public boolean matches(@NonNull String pattern) {
        return false;
    }

    @NonNull
    @Override
    public Context setRoute(@NonNull Route route) {
        return null;
    }

    @NonNull
    @Override
    public String getRequestPath() {
        return null;
    }

    @NonNull
    @Override
    public Context setRequestPath(@NonNull String path) {
        return null;
    }

    @NonNull
    @Override
    public Value path(@NonNull String name) {
        return null;
    }

    @NonNull
    @Override
    public <T> T path(@NonNull Class<T> type) {
        return null;
    }

    @NonNull
    @Override
    public ValueNode path() {
        return null;
    }

    @NonNull
    @Override
    public Map<String, String> pathMap() {
        return null;
    }

    @NonNull
    @Override
    public Context setPathMap(@NonNull Map<String, String> pathMap) {
        return null;
    }

    @NonNull
    @Override
    public QueryString query() {
        return null;
    }

    @NonNull
    @Override
    public ValueNode query(@NonNull String name) {
        return null;
    }

    @NonNull
    @Override
    public String queryString() {
        return null;
    }

    @NonNull
    @Override
    public <T> T query(@NonNull Class<T> type) {
        return null;
    }

    @NonNull
    @Override
    public Map<String, String> queryMap() {
        return null;
    }

    @NonNull
    @Override
    public ValueNode header() {
        return null;
    }

    @NonNull
    @Override
    public Value header(@NonNull String name) {
        return null;
    }

    @NonNull
    @Override
    public Map<String, String> headerMap() {
        return null;
    }

    @Override
    public boolean accept(@NonNull MediaType contentType) {
        return false;
    }

    @Nullable
    @Override
    public MediaType accept(@NonNull List<MediaType> produceTypes) {
        return null;
    }

    @Nullable
    @Override
    public MediaType getRequestType() {
        return null;
    }

    @NonNull
    @Override
    public MediaType getRequestType(MediaType defaults) {
        return null;
    }

    @Override
    public long getRequestLength() {
        return 0;
    }

    @Nullable
    @Override
    public <T> T getUser() {
        return null;
    }

    @NonNull
    @Override
    public Context setUser(@Nullable Object user) {
        return null;
    }

    @NonNull
    @Override
    public String getRequestURL() {
        return null;
    }

    @NonNull
    @Override
    public String getRequestURL(@NonNull String path) {
        return null;
    }

    @NonNull
    @Override
    public String getRemoteAddress() {
        return null;
    }

    @NonNull
    @Override
    public Context setRemoteAddress(@NonNull String remoteAddress) {
        return null;
    }

    @NonNull
    @Override
    public String getHost() {
        return null;
    }

    @NonNull
    @Override
    public Context setHost(@NonNull String host) {
        return null;
    }

    @NonNull
    @Override
    public String getHostAndPort() {
        return null;
    }

    @Override
    public int getPort() {
        return 0;
    }

    @NonNull
    @Override
    public Context setPort(int port) {
        return null;
    }

    @NonNull
    @Override
    public String getProtocol() {
        return null;
    }

    @NonNull
    @Override
    public List<Certificate> getClientCertificates() {
        return null;
    }

    @Override
    public int getServerPort() {
        return 0;
    }

    @NonNull
    @Override
    public String getServerHost() {
        return null;
    }

    @Override
    public boolean isSecure() {
        return false;
    }

    @NonNull
    @Override
    public String getScheme() {
        return null;
    }

    @NonNull
    @Override
    public Context setScheme(@NonNull String scheme) {
        return null;
    }

    @NonNull
    @Override
    public Formdata form() {
        return null;
    }

    @NonNull
    @Override
    public ValueNode form(@NonNull String name) {
        return null;
    }

    @NonNull
    @Override
    public <T> T form(@NonNull Class<T> type) {
        return null;
    }

    @NonNull
    @Override
    public Map<String, String> formMap() {
        return null;
    }

    @NonNull
    @Override
    public List<FileUpload> files() {
        return null;
    }

    @NonNull
    @Override
    public List<FileUpload> files(@NonNull String name) {
        return null;
    }

    @NonNull
    @Override
    public FileUpload file(@NonNull String name) {
        return null;
    }

    @NonNull
    @Override
    public Body body() {
        return null;
    }

    @NonNull
    @Override
    public <T> T body(@NonNull Class<T> type) {
        return null;
    }

    @NonNull
    @Override
    public <T> T body(@NonNull Type type) {
        return null;
    }

    @NonNull
    @Override
    public <T> T decode(@NonNull Type type, @NonNull MediaType contentType) {
        return null;
    }

    @NonNull
    @Override
    public MessageDecoder decoder(@NonNull MediaType contentType) {
        return null;
    }

    @Override
    public boolean isInIoThread() {
        return false;
    }

    @NonNull
    @Override
    public Context dispatch(@NonNull Runnable action) {
        return null;
    }

    @NonNull
    @Override
    public Context dispatch(@NonNull Executor executor, @NonNull Runnable action) {
        return null;
    }

    @NonNull
    @Override
    public Context detach(@NonNull Route.Handler next) throws Exception {
        return null;
    }

    @NonNull
    @Override
    public Context upgrade(@NonNull WebSocket.Initializer handler) {
        return null;
    }

    @NonNull
    @Override
    public Context upgrade(@NonNull ServerSentEmitter.Handler handler) {
        return null;
    }

    @NonNull
    @Override
    public Context setResponseHeader(@NonNull String name, @NonNull Date value) {
        return null;
    }

    @NonNull
    @Override
    public Context setResponseHeader(@NonNull String name, @NonNull Instant value) {
        return null;
    }

    @NonNull
    @Override
    public Context setResponseHeader(@NonNull String name, @NonNull Object value) {
        return null;
    }

    @NonNull
    @Override
    public Context setResponseHeader(@NonNull String name, @NonNull String value) {
        return null;
    }

    @NonNull
    @Override
    public Context removeResponseHeader(@NonNull String name) {
        return null;
    }

    @NonNull
    @Override
    public Context removeResponseHeaders() {
        return null;
    }

    @NonNull
    @Override
    public Context setResponseLength(long length) {
        return null;
    }

    @Nullable
    @Override
    public String getResponseHeader(@NonNull String name) {
        return null;
    }

    @Override
    public long getResponseLength() {
        return 0;
    }

    @NonNull
    @Override
    public Context setResponseCookie(@NonNull Cookie cookie) {
        return null;
    }

    @NonNull
    @Override
    public Context setResponseType(@NonNull String contentType) {
        return null;
    }

    @NonNull
    @Override
    public Context setResponseType(@NonNull MediaType contentType) {
        return null;
    }

    @NonNull
    @Override
    public Context setResponseType(@NonNull MediaType contentType, @Nullable Charset charset) {
        return null;
    }

    @NonNull
    @Override
    public Context setDefaultResponseType(@NonNull MediaType contentType) {
        return null;
    }

    @NonNull
    @Override
    public MediaType getResponseType() {
        return null;
    }

    @NonNull
    @Override
    public Context setResponseCode(@NonNull StatusCode statusCode) {
        return null;
    }

    @NonNull
    @Override
    public Context setResponseCode(int statusCode) {
        return null;
    }

    @NonNull
    @Override
    public StatusCode getResponseCode() {
        return null;
    }

    @NonNull
    @Override
    public Context render(@NonNull Object value) {
        return null;
    }

    @NonNull
    @Override
    public OutputStream responseStream() {
        return null;
    }

    @NonNull
    @Override
    public OutputStream responseStream(@NonNull MediaType contentType) {
        return null;
    }

    @NonNull
    @Override
    public Context responseStream(@NonNull MediaType contentType, @NonNull SneakyThrows.Consumer<OutputStream> consumer) throws Exception {
        return null;
    }

    @NonNull
    @Override
    public Context responseStream(@NonNull SneakyThrows.Consumer<OutputStream> consumer) throws Exception {
        return null;
    }

    @NonNull
    @Override
    public Sender responseSender() {
        return null;
    }

    @NonNull
    @Override
    public PrintWriter responseWriter() {
        return null;
    }

    @NonNull
    @Override
    public PrintWriter responseWriter(@NonNull MediaType contentType) {
        return null;
    }

    @NonNull
    @Override
    public PrintWriter responseWriter(@NonNull MediaType contentType, @Nullable Charset charset) {
        return null;
    }

    @NonNull
    @Override
    public Context responseWriter(@NonNull SneakyThrows.Consumer<PrintWriter> consumer) throws Exception {
        return null;
    }

    @NonNull
    @Override
    public Context responseWriter(@NonNull MediaType contentType, @NonNull SneakyThrows.Consumer<PrintWriter> consumer) throws Exception {
        return null;
    }

    @NonNull
    @Override
    public Context responseWriter(@NonNull MediaType contentType, @Nullable Charset charset, @NonNull SneakyThrows.Consumer<PrintWriter> consumer) throws Exception {
        return null;
    }

    @NonNull
    @Override
    public Context sendRedirect(@NonNull String location) {
        return null;
    }

    @NonNull
    @Override
    public Context sendRedirect(@NonNull StatusCode redirect, @NonNull String location) {
        return null;
    }

    @NonNull
    @Override
    public Context send(@NonNull String data) {
        return null;
    }

    @NonNull
    @Override
    public Context send(@NonNull String data, @NonNull Charset charset) {
        return null;
    }

    @NonNull
    @Override
    public Context send(@NonNull byte[] data) {
        return null;
    }

    @NonNull
    @Override
    public Context send(@NonNull ByteBuffer data) {
        return null;
    }

    @NonNull
    @Override
    public Context send(@NonNull byte[]... data) {
        return null;
    }

    @NonNull
    @Override
    public Context send(@NonNull ByteBuffer[] data) {
        return null;
    }

    @NonNull
    @Override
    public Context send(@NonNull ReadableByteChannel channel) {
        return null;
    }

    @NonNull
    @Override
    public Context send(@NonNull InputStream input) {
        return null;
    }

    @NonNull
    @Override
    public Context send(@NonNull FileDownload file) {
        return null;
    }

    @NonNull
    @Override
    public Context send(@NonNull Path file) {
        return null;
    }

    @NonNull
    @Override
    public Context send(@NonNull FileChannel file) {
        return null;
    }

    @NonNull
    @Override
    public Context send(@NonNull StatusCode statusCode) {
        return null;
    }

    @NonNull
    @Override
    public Context sendError(@NonNull Throwable cause) {
        return null;
    }

    @NonNull
    @Override
    public Context sendError(@NonNull Throwable cause, @NonNull StatusCode statusCode) {
        return null;
    }

    @Override
    public boolean isResponseStarted() {
        return false;
    }

    @Override
    public boolean getResetHeadersOnError() {
        return false;
    }

    @NonNull
    @Override
    public Context setResetHeadersOnError(boolean value) {
        return null;
    }

    @NonNull
    @Override
    public Context onComplete(@NonNull Route.Complete task) {
        return null;
    }

    @NonNull
    @Override
    public <T> T require(@NonNull Class<T> type) throws RegistryException {
        return null;
    }

    @NonNull
    @Override
    public <T> T require(@NonNull Class<T> type, @NonNull String name) throws RegistryException {
        return null;
    }

    @NonNull
    @Override
    public <T> T require(@NonNull ServiceKey<T> key) throws RegistryException {
        return null;
    }
}
