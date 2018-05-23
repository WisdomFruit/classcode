package com.jumpdigital.nico.classcode.retrofit;

import android.content.Context;

import com.jumpdigital.nico.classcode.BuildConfig;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Factory {
    public static String BASE_URL = "http://192.168.0.92:3000";
    private ServiceAPI service;
    public boolean hasAuthorization = false;
    private Context _context;

    public ServiceAPI getInstance(final Context context) {
        _context = context;
        if (service == null) {

            OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
            builder.readTimeout(60, TimeUnit.SECONDS);
            builder.connectTimeout(60, TimeUnit.SECONDS);
            builder.writeTimeout(60, TimeUnit.SECONDS);

            //builder.certificatePinner(new CertificatePinner.Builder().add("*.androidadvance.com", "sha256/RqzElicVPA6LkKm9HblOvNOUqWmD+4zNXcRb+WjcaAE=")
            //    .add("*.xxxxxx.com", "sha256/8Rw90Ej3Ttt8RRkrg+WYDS9n7IS03bk5bjP/UXPtaY8=")
            //    .add("*.xxxxxxx.com", "sha256/Ko8tivDrEjiY90yGasP6ZpBU4jwXvHqVvQI0GS3GNdA=")
            //    .add("*.xxxxxxx.com", "sha256/VjLZe/p3W/PJnd6lL8JVNBCGQBZynFLdZSTIqcO0SJ8=")
            //    .build());

            if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                builder.addInterceptor(interceptor);
            }

            int cacheSize = 30 * 1024 * 1024; // 10 MiB
            Cache cache = new Cache(context.getCacheDir(), cacheSize);
            builder.cache(cache);

            builder.interceptors().add(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request();
                    // Request customization: add request headers
                    Request.Builder requestBuilder = request.newBuilder();
//                    requestBuilder.addHeader("Content-Type:", "application/json");
//                    if (isHasAuthorization()) {
//                        requestBuilder.addHeader("x-auth-token", sharedPref.getAUTHToken());
//                    }
                    request = requestBuilder.build();
                    return chain.proceed(request);
                }
            });

            Retrofit retrofit = new Retrofit.Builder().
                    client(builder.build()).
                    addConverterFactory(GsonConverterFactory.create()).
                    baseUrl(BASE_URL).build();
            service = retrofit.create(ServiceAPI.class);
            return service;
        } else {
            return service;
        }
    }

}

