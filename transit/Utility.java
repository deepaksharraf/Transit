package com.example.deepak.transit;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utility {
    private static Pattern pattern;
    private static Matcher matcher;

    private static final String USERNAME = "^[a-zA-Z]{3,15}";
    private static final String PASSWORD = "[a-zA-Z0-9]{3,15}";
    private static final String IP_ADDRESS = "((25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9])\\.(25[0-5]|2[0-4]"
            + "[0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1]"
            + "[0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}"
            + "|[1-9][0-9]|[0-9]))";

    private static final String PORT = "^(6553[0-5]|655[0-2]\\d|65[0-4]\\d\\d|6[0-4]\\d{3}|[1-5]\\d{4}|[2-9]\\d{3}|1[1-9]\\d{2}|10[3-9]\\d|102[4-9])$";
    private static final String PTO = "[0-9]{1,4}";

    private static final String DEVICE_ID = "[0-9]{8}";

    public static boolean validateIP(String ipAddress)
    {
        pattern = Pattern.compile(IP_ADDRESS);
        matcher = pattern.matcher(ipAddress);

        boolean value = matcher.matches();
        return value;
    }

    public static boolean validatePort(String port)
    {
        pattern = Pattern.compile(PORT);
        matcher = pattern.matcher(port);

        boolean value = matcher.matches();
        return value;
    }

    public static boolean validatePTO(String pto)
    {
        pattern = Pattern.compile(PTO);
        matcher = pattern.matcher(pto);

        boolean value = matcher.matches();
        return value;
    }

    public static boolean validatePassword(String password) {
        pattern = Pattern.compile(PASSWORD);
        matcher = pattern.matcher(password);

        boolean value = matcher.matches();
        return value;
    }


    public static boolean validateUsername(String username) {
        pattern = Pattern.compile(USERNAME);
        matcher = pattern.matcher(username);
        return matcher.matches();
    }


    public static boolean validateDeviceID(String deviveID) {
        pattern = Pattern.compile(DEVICE_ID);
        matcher = pattern.matcher(deviveID);
        return matcher.matches();
    }

    public static boolean isEmptyString(String str) {
        return str == null || (str.trim()).length() == 0;
    }
}