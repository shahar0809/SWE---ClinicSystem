package il.cshaifasweng.OCSFMediatorExample.response;

import il.cshaifasweng.OCSFMediatorExample.entities.User;

public class RegisterResponse implements Response {
    public User user;
    public String error;

    public RegisterResponse(User user) {
        this.user = user;
        this.error = null;
    }

    public RegisterResponse(String error) {
        this.user = null;
        this.error = error;
    }

    @Override
    public String getType() {
        return "RegisterResponse";
    }
}
