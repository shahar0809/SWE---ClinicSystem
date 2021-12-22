package il.cshaifasweng.OCSFMediatorExample.handlers;

import il.cshaifasweng.OCSFMediatorExample.Authenticator;
import il.cshaifasweng.OCSFMediatorExample.RequestHandlerFactory;
import il.cshaifasweng.OCSFMediatorExample.entities.User;
import il.cshaifasweng.OCSFMediatorExample.requests.LoginRequest;
import il.cshaifasweng.OCSFMediatorExample.requests.Request;
import il.cshaifasweng.OCSFMediatorExample.response.LoginResponse;
import il.cshaifasweng.OCSFMediatorExample.response.Response;

public class LoginHandler extends Handler {
    protected Authenticator authenticator;

    public LoginHandler(RequestHandlerFactory factory, Authenticator authenticator) {
        this.factory = factory;
        this.authenticator = authenticator;
    }

    @Override
    public Response handleRequest(Request request) {
        LoginRequest loginRequest = null;
        try {
            loginRequest = (LoginRequest)request;
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        User user = this.authenticator.login(loginRequest.getUsername(), loginRequest.getPassword());
        return new LoginResponse(user);
    }
}
