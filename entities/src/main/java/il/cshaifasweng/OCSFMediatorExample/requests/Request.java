package il.cshaifasweng.OCSFMediatorExample.requests;

import java.io.Serializable;
import java.util.UUID;

public abstract class Request implements Serializable {
    protected UUID token;

    public UUID getToken() { return this.token; }
    public void setToken(UUID token) { this.token = token; }

    public abstract String getType();
}
