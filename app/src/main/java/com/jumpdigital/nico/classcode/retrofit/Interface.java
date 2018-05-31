package com.jumpdigital.nico.classcode.retrofit;


import com.jumpdigital.nico.classcode.model.AttendanceResult;
import com.jumpdigital.nico.classcode.model.LoginResult;

public class Interface {
    public interface postLoginCallback {
        void onResponse(boolean isSuccess,
                        LoginResult response,
                        String message);
    }

    public interface postAttendanceCallback {
        void onResponse(boolean isSuccess,
                        AttendanceResult response,
                        String message);
    }
}

