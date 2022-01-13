package il.cshaifasweng.OCSFMediatorExample.requests;

public class LoginRequest implements Request {
    public String username;
    public String password;

    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String getType() {
        return "LoginRequest";
    }
}
