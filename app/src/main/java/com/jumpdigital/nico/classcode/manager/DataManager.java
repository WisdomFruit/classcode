package com.jumpdigital.nico.classcode.manager;

import android.content.Context;

import com.jumpdigital.nico.classcode.model.AttendanceResult;
import com.jumpdigital.nico.classcode.model.LoginResult;
import com.jumpdigital.nico.classcode.retrofit.Factory;
import com.jumpdigital.nico.classcode.retrofit.Interface;
import com.jumpdigital.nico.classcode.retrofit.ServiceAPI;
import com.jumpdigital.nico.classcode.retrofit.Interface.postLoginCallback;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Nico on 15/05/2018.
 */

public class DataManager {

    private static DataManager mInstance;
    private Context context;
    private static Factory factory;
    private static ServiceAPI serviceAPI;
    private static String TAG;

    public static DataManager getmInstance(Context tcontext) {
        if (mInstance == null) {
            mInstance = new DataManager();
            mInstance.context = tcontext;
            factory = new Factory();
            serviceAPI = factory.getInstance(mInstance.context);
            TAG = tcontext.getClass().getSimpleName().toString();
        }
        return mInstance;
    }

    public void postLogin(HashMap<String, String> map, final postLoginCallback callback) {
        String sNum = map.get("studentnum");

        final Call<LoginResult> ccSignUp = serviceAPI.postJSONLogin(sNum);
        ccSignUp.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                LoginResult ccsur = response.body();
                if (response.isSuccessful() && (ccsur).getStatus().equals("Sucess")) {
                    callback.onResponse(true, response.body(), "Successful");
                }
                else {
                    try {
                        callback.onResponse(false, null, "Failed");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {
                callback.onResponse(false, null, t.getLocalizedMessage());
            }
        });
    }

    public void postAttendance(HashMap<String, String> map, final Interface.postAttendanceCallback callback) {
        String studentNumber = map.get("studentnum");
        String date = map.get("date");
        String attendance = map.get("attendance");

        final Call<AttendanceResult> ccSignUp = serviceAPI.postJSONAttendance(studentNumber,date , attendance);
        ccSignUp.enqueue(new Callback<AttendanceResult>() {
            @Override
            public void onResponse(Call<AttendanceResult> call, Response<AttendanceResult> response) {
                AttendanceResult ccAR = response.body();
                if (response.isSuccessful() && (ccAR).getStatus().equals("SUCCESS")) {
                    callback.onResponse(true, response.body(), "Successful");
                }
                else {
                    try {
                        callback.onResponse(false, null, "Failed");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<AttendanceResult> call, Throwable t) {
                callback.onResponse(false, null, t.getLocalizedMessage());
            }
        });

    }

}
