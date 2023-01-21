package io.jooby.internal.nima;

import io.helidon.common.http.DirectHandler;
import io.helidon.common.http.Http;
import io.helidon.common.http.ServerResponseHeaders;
import io.jooby.Jooby;
import io.jooby.Router;

public class RequestHandler implements DirectHandler {

    private final Jooby application;
    private final Router router;

    public RequestHandler(Jooby application, Router router) {
        this.application = application;
        this.router = router;
    }

    @Override
    public TransportResponse handle(TransportRequest transportRequest, EventType eventType, Http.Status status, ServerResponseHeaders serverResponseHeaders, String s) {
        NimaContext context = new NimaContext();
        router.match(context).execute(context);
        return TransportResponse.builder().build();
    }
}
