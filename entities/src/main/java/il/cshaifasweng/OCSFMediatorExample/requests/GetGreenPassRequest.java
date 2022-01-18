package il.cshaifasweng.OCSFMediatorExample.requests;

import il.cshaifasweng.OCSFMediatorExample.entities.User;

public class GetGreenPassRequest extends Request {
    public User user;

    public GetGreenPassRequest(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String getType() {
        return "GetGreenPassRequest";
    }
}

