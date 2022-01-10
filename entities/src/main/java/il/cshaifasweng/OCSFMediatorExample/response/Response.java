package il.cshaifasweng.OCSFMediatorExample.response;

import java.io.Serializable;

public abstract class Response implements Serializable {
    public boolean succeed;

    public Response(boolean succeed) {
        this.succeed = succeed;
    }

    public abstract String getType();
}
