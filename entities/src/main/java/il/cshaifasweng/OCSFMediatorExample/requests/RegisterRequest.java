package il.cshaifasweng.OCSFMediatorExample.requests;

import il.cshaifasweng.OCSFMediatorExample.entities.Clinic;

public class RegisterRequest extends Request {
    public String username;
    public String password;
    public int age;
    public String clinic;

    public RegisterRequest(String username, String password, int age, String clinic) {
        this.username = username;
        this.password = password;
        this.age = age;
        this.clinic = clinic;
    }

    @Override
    public String getType() {
        return "RegisterRequest";
    }
}
