package com.chirag.sonic.data.network;

import com.chirag.sonic.data.model.NBATeam;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Chirag on 4/1/2019 at 19:43.
 * Project - NBATeamViewer
 */
public class RetrofitClient {

    private static ApiService mApiService;
    private static final String BASE_URL = "https://api.myjson.com/bins/";

    public static ApiService getApiService() {

        if (mApiService == null) {

            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(NBATeam.class, new NBADeserializer())
                    .create();

            Retrofit retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();

            mApiService = retrofit.create(ApiService.class);
        }
        return mApiService;
    }
}
