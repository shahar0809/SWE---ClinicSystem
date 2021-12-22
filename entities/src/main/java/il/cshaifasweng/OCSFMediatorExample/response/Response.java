package il.cshaifasweng.OCSFMediatorExample.response;

import java.io.Serializable;

public interface Response extends Serializable {
    boolean hasSucceeded = true;
    Exception exception = null;
    String getType();
}
