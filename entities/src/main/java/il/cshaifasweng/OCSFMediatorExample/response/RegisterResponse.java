package il.cshaifasweng.OCSFMediatorExample.response;

import il.cshaifasweng.OCSFMediatorExample.entities.User;

public class RegisterResponse extends Response {
    public User user;
    public String error;

    public RegisterResponse(User user, boolean isSuccessful) {
        super(isSuccessful);
        this.user = user;
        this.error = null;
    }

    public RegisterResponse(String error, boolean isSuccessful) {
        super(isSuccessful);
        this.user = null;
        this.error = error;
    }

    @Override
    public String getType() {
        return "RegisterResponse";
    }
}
