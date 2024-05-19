package com.decportback.common.kisApi.apiRequest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

@Configuration
public class KISRestRegistry{

    private static final HttpLoggingInterceptor loggingInterceptor
            = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

    private final Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    @Bean
    KISRestAPI getJsonPlaceHolderAPI(){
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .build();
        return new Retrofit.Builder()
                .baseUrl("https://openapi.koreainvestment.com:9443")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build()
                .create(KISRestAPI.class);
    }
}