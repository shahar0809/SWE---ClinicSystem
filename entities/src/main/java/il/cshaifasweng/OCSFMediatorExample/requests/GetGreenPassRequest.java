package il.cshaifasweng.OCSFMediatorExample.requests;

import il.cshaifasweng.OCSFMediatorExample.entities.User;

public class GetGreenPassRequest implements Request {
    public String username;

    public GetGreenPassRequest(String username) {
        this.username = username;
    }

    @Override
    public String getType() {
        return "GetGreenPassRequest";
    }
}

