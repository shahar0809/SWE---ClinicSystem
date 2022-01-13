package il.cshaifasweng.OCSFMediatorExample.requests;

public class RegisterRequest implements Request {
    public String username;
    public String password;

    public RegisterRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String getType() {
        return "RegisterRequest";
    }
}
