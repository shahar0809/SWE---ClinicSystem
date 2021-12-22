package il.cshaifasweng.OCSFMediatorExample.requests;

public class LoginRequest implements Request {
    protected String username, password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getType() {
        return null;
    }
}
