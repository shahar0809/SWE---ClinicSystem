package il.cshaifasweng.OCSFMediatorExample.requests;

import il.cshaifasweng.OCSFMediatorExample.handlers.Handler;

import java.io.Serializable;

public interface Request extends Serializable {
    String getType();
    Handler nextHandler = null;
}
