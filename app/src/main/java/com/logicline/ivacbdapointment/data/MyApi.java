package com.logicline.ivacbdapointment.data;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.intuit.sdp.BuildConfig;
import com.logicline.ivacbdapointment.R;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyApi {
    private static Retrofit retrofit = null;

    private MyApi() {
    }

    public static ApiInterface getInstance(Context context) {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        if (retrofit == null) {
            OkHttpClient.Builder client = new OkHttpClient.Builder();

            if (BuildConfig.DEBUG) {
                client.addInterceptor(interceptor).build();
            }

            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl(context.getString(R.string.api_base_url))
                    .addConverterFactory(GsonConverterFactory.create(gson));

            builder.client(client.build());
            retrofit = builder.build();
        }

        return retrofit.create(ApiInterface.class);
    }
}
