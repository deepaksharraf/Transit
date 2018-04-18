package com.example.deepak.transit;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {
    private static final String PREFS_NAME = "MyPreferences";
    private static final String PREF_Login_UserName = "login_username";
    private static final String PREF_Login_Password = "login_password";
    private static final String PREF_Current_Shift = "current_shift";
    private static final String PREF_Current_Stage = "current_stage";
    private static final String PREF_Device_Id = "device_id";
    private static final String PREF_Route_Number = "route_number";


    public static String getLoginUserName(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getString(PREF_Login_UserName, null);
    }

    public static void setLoginUserName(String userName, Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit();
        editor.putString(PREF_Login_UserName, userName);
        editor.apply();
    }

    public static String getLoginPassword(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getString(PREF_Login_Password, null);
    }

    public static void setLoginPassword(String password, Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit();
        editor.putString(PREF_Login_Password, password);
        editor.apply();
    }
}
