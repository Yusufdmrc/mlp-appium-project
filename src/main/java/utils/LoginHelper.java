package utils;

public class LoginHelper {

    public static String getUserName(String usernameKey) {
        switch (usernameKey.toLowerCase()) {
            case "correcttcid":
                return ConfigReader.get("correct.tc.id");
            case "correctaccountno":
                return ConfigReader.get("correct.account.no");
            case "correctemail":
                return ConfigReader.get("correct.email");
            case "empty":
                return "";
            default:
                return usernameKey;
        }
    }

    public static String getPassword(String passwordKey) {
        switch (passwordKey.toLowerCase()) {
            case "correctpassword":
                return ConfigReader.get("correct.password");
            case "empty":
                return "";
            default:
                return passwordKey;
        }
    }
}
