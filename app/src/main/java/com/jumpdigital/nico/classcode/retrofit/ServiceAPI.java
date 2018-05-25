package com.jumpdigital.nico.classcode.retrofit;

import com.jumpdigital.nico.classcode.model.LoginResult;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ServiceAPI {

    String postLogin = "/api/v1/login";
    String postAttendance = "/api/v1/attendances";

    @FormUrlEncoded
    @POST(postLogin)
    Call<LoginResult> postJSONLogin (@Field("studentnum") String studentNum);

    @FormUrlEncoded
    @POST(postAttendances)
    Call<AttendanceResult> postJSONAttendance (@Field("date") String date,
                                              @Field("attendance") String attendance);

}
