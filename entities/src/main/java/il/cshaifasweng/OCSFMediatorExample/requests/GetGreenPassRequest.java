package il.cshaifasweng.OCSFMediatorExample.requests;

import il.cshaifasweng.OCSFMediatorExample.entities.User;

public class GetGreenPassRequest implements Request {
    public User user;

    public GetGreenPassRequest(User user) {
        this.user = user;
    }

    @Override
    public String getType() {
        return "GetGreenPassRequest";
    }
}

