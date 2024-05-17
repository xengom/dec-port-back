package com.decportback.kisApi.apiRequest;

import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

@Component
public class RetrofitUtils {
    public <T> T execute (Call<T> call) {
        try {
            Response<T> response = call.execute();
            if(response.isSuccessful()) {
                return response.body();
            } else {
                throw new RuntimeException(response.raw().toString());
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
