package io.jooby.internal.nima;

import io.helidon.nima.webserver.http.ServerRequest;
import io.helidon.nima.webserver.http.ServerResponse;
import io.jooby.Router;

public class RequestHandler {

    private final Router router;

    public RequestHandler(Router router) {
        this.router = router;
    }

    public void handle(ServerRequest request, ServerResponse response) {
        NimaContext context = new NimaContext(request, response, router);
        router.match(context).execute(context);
    }
}
