package il.cshaifasweng.OCSFMediatorExample;

import il.cshaifasweng.OCSFMediatorExample.handlers.LoginHandler;

public class RequestHandlerFactory {
    protected Authenticator authenticator;
    protected AppointmentsManager appointmentsManager;
    protected ManagementOperations management;

    public RequestHandlerFactory() {
        authenticator = new Authenticator();
        appointmentsManager = new AppointmentsManager();
        management = new ManagementOperations();
    }

    public RequestHandlerFactory(Authenticator authenticator, AppointmentsManager appointmentsManager, ManagementOperations management) {
        this.authenticator = authenticator;
        this.appointmentsManager = appointmentsManager;
        this.management = management;
    }

    public LoginHandler createLoginRequestHandler() {
        return new LoginHandler(this, this.authenticator);
    }
}
