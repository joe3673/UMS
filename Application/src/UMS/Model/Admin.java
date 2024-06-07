package UMS.Model;

import java.time.ZonedDateTime;
import java.util.HashMap;

public class Admin {

    private String userName;

    private String password;

    private String accessCode = "12345";

    private HashMap<String, ZonedDateTime> loginTimes;


    public Admin (String userName, String password){

        this.userName = userName;
        this.password = password;

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccessCode() {
        return accessCode;
    }

    public void setAccessCode(String accessCode) {
        this.accessCode = accessCode;
    }

    public HashMap<String, ZonedDateTime> getLoginTimes() {
        return loginTimes;
    }

    public void setLoginTimes(HashMap<String, ZonedDateTime> loginTimes) {
        this.loginTimes = loginTimes;
    }
}
