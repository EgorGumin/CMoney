package com.lymno.cmoney.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

public class RestClient {
    private static Api REST_CLIENT;
    private final static String ROOT = "http://hac2112.azurewebsites.net/";
    private final static String USER_ID = "1";

    static {
        setupRestClient();
    }

    private RestClient() {
    }

    public static Api get() {
        return REST_CLIENT;
    }

    private static void setupRestClient() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .excludeFieldsWithoutExposeAnnotation()
                .create();

        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setEndpoint(ROOT)
                .setConverter(new GsonConverter(gson))
                .setClient(new OkClient(new OkHttpClient()))
                .setLogLevel(RestAdapter.LogLevel.FULL);

        RestAdapter restAdapter = builder.build();
        REST_CLIENT = restAdapter.create(Api.class);
    }


    public static String getUserId() {
        return USER_ID;
    }

}

