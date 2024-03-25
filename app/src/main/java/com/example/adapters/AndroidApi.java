package com.example.adapters;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AndroidApi {
    @GET("cards")
    Call<List<Card>> getCards();
    @GET("/user")
    Call<User> getUserByEmail(@Query("email") String email);
}
