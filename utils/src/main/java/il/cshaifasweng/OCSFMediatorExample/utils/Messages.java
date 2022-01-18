package il.cshaifasweng.OCSFMediatorExample.utils;

/**
 * Static class to hold output messages to client
 */
public class Messages {
    public static String GENERAL_ERROR = "Error occurred";
    public static String LOGIN_SUCCESS = "Login successful!";
    public static String LOGIN_ALREADY_CONNECTED = "You're already connected";
    public static String LOGIN_WRONG_AUTH = "Wrong password!";
    public static String LOGIN_USER_NOT_FOUND = "User not Found!";

    public static String REGISTER_SUCCESS = "Registration successful!";
    public static String REGISTER_USERNAME_TAKEN = "Username is already taken!";

    public static String RESERVE_APPOINTMENT_SUCCESS = "Appointment was reserved successfully!";
    public static String RESERVE_APPOINTMENT_NOT_AVAILABLE = "Appointment is not available";

    public static String CANCEL_APPOINTMENT_SUCCESS = "Appointment was cancelled successfully!";
    public static String CANCEL_APPOINTMENT_NOT_RESERVED = "You didn't reserve this appointment!";

    public static String GREEN_PASS_SUCCESS = "Green pass - Success!";
    public static String GREEN_PASS_NOT_VACCINATED = "You're not eligible for green pass!";

    public static String COVID_TEST_NO_QUESTIONNAIRE = "You haven't filled the Covid Questionnaire!";
}
