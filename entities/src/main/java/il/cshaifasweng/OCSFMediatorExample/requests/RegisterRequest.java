package il.cshaifasweng.OCSFMediatorExample.requests;

public class RegisterRequest extends Request {
    public String username;
    public String password;
    public int age;

    public RegisterRequest(String username, String password, int age) {
        this.username = username;
        this.password = password;
        this.age = age;
    }

    @Override
    public String getType() {
        return "RegisterRequest";
    }
}
