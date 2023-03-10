package com.farzin.aparat.webService;

import com.farzin.aparat.Utils.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient{

    public static Retrofit retrofit = null;

    public static Retrofit getClient(){

        if (retrofit == null){

            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
