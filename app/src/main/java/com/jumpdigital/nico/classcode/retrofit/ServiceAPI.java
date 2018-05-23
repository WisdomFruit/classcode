package com.jumpdigital.nico.classcode.retrofit;

import com.jumpdigital.nico.classcode.model.LoginResult;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ServiceAPI {

    String postLogin = "/api/v1/login";

    @FormUrlEncoded
    @POST(postLogin)
    Call<LoginResult> postJSONLogin (@Field("studentnum") String studentNum);

}
