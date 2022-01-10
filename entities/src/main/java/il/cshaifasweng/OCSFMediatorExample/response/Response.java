package il.cshaifasweng.OCSFMediatorExample.response;

import java.io.Serializable;

public abstract class Response implements Serializable {
    abstract public String getType();
}
