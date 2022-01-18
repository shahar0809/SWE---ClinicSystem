package il.cshaifasweng.OCSFMediatorExample.response;

import il.cshaifasweng.OCSFMediatorExample.entities.User;

public class LoginResponse extends Response {
    public User user;

    public LoginResponse(User user, boolean isSuccessful) {
        super(isSuccessful, null);
        this.user = user;
    }

    public LoginResponse(String error, boolean isSuccessful) {
        super(isSuccessful, error);
        this.user = null;
    }

    @Override
    public String getType() {
        return "LoginResponse";
    }
}
