package il.cshaifasweng.OCSFMediatorExample.response;

import java.io.Serializable;

public abstract class Response implements Serializable {
    protected boolean isSuccessful;

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public void setSuccessful(boolean successful) {
        isSuccessful = successful;
    }

    public Response(boolean isSuccessful) {
        this.isSuccessful = isSuccessful;
    }

    public abstract String getType();
}
