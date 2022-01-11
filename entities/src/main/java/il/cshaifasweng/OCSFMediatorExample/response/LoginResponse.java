package il.cshaifasweng.OCSFMediatorExample.response;

import il.cshaifasweng.OCSFMediatorExample.entities.User;

public class LoginResponse implements Response {
    public User user;
    public String error;

    public LoginResponse(User user) {
        this.user = user;
        this.error = null;
    }

    public LoginResponse(String error) {
        this.user = null;
        this.error = error;
    }

    @Override
    public String getType() {
        return "LoginResponse";
    }
}
