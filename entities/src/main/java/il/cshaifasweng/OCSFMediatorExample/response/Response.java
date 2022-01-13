package il.cshaifasweng.OCSFMediatorExample.response;

import java.io.Serializable;

public abstract class Response implements Serializable {
    protected boolean isSuccessful;
    protected String error;

    public String getError() {
        return error;
    }

    public Response(boolean isSuccessful) {
        this.isSuccessful = isSuccessful;
        this.error = null;
    }

    public void setError(String error) {
        this.error = error;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public void setSuccessful(boolean successful) {
        isSuccessful = successful;
    }

    public Response(boolean isSuccessful, String error) {
        this.isSuccessful = isSuccessful;
        this.error = error;
    }

    public abstract String getType();
}
