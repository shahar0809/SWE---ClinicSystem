package il.cshaifasweng.OCSFMediatorExample.handlers;

import il.cshaifasweng.OCSFMediatorExample.RequestHandlerFactory;
import il.cshaifasweng.OCSFMediatorExample.requests.Request;
import il.cshaifasweng.OCSFMediatorExample.response.Response;

public abstract class Handler {
    public abstract Response handleRequest(Request request);

    protected RequestHandlerFactory factory = null;
}
