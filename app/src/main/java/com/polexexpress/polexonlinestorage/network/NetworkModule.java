package com.polexexpress.polexonlinestorage.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.polexexpress.polexonlinestorage.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.polexexpress.polexonlinestorage.other.Const.BASE_URL;

public class NetworkModule {

    private static OkHttpClient generateOkHttpClient() {
        HttpLoggingInterceptorHeavyFilesSafe httpLoggingInterceptor = new HttpLoggingInterceptorHeavyFilesSafe();
        HttpLoggingInterceptorHeavyFilesSafe.Level logLevel = BuildConfig.DEBUG ?
                HttpLoggingInterceptorHeavyFilesSafe.Level.BODY : HttpLoggingInterceptorHeavyFilesSafe.Level.NONE;
        httpLoggingInterceptor.setLevel(logLevel);
        return new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();
    }

    private static Retrofit getRetrofit() {
        Gson gson = new GsonBuilder()
                .serializeNulls()
                .create();

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(generateOkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public static Backend getBackEndService() {
        return getRetrofit().create(Backend.class);
    }

}
