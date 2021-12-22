package il.cshaifasweng.OCSFMediatorExample.response;

import il.cshaifasweng.OCSFMediatorExample.entities.User;

public class LoginResponse implements Response {
    protected User user;

    public User getUser() {
        return user;
    }

    public LoginResponse(User user) {
        this.user = user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String getType() {
        return null;
    }
}
