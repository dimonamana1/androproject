package com.example.secondtask;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class ApiBuilder {

    static Api createApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(Api.class);
    }

}
