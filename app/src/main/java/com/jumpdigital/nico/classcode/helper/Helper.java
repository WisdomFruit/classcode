package com.jumpdigital.nico.classcode.helper;

import android.content.Context;
import android.content.SharedPreferences;

public class Helper {

    SharedPreferences preferences;

    SharedPreferences.Editor editor;
    Context _context;

    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "ClassCode";

    private static final String KEY_IS_LOGGEDIN = "isLoggedIn";
    private static final String StudentName = "Full Name";

    public Helper (Context context){
        this._context = context;
        preferences = _context.getSharedPreferences(PREF_NAME,PRIVATE_MODE);
        editor = preferences.edit();
    }
    public void setLogin(boolean isLoggedIn){
        editor.putBoolean(KEY_IS_LOGGEDIN,isLoggedIn);
        editor.commit();
    }

    public boolean isLoggedIn(){
        return preferences.getBoolean(KEY_IS_LOGGEDIN,false);
    }

    public String getStundentName(){
        return preferences.getString(StudentName, "uname");
    }

    public void setStundentName(String uname){
        editor.putString(StudentName, uname);
        editor.commit();
    }
}
