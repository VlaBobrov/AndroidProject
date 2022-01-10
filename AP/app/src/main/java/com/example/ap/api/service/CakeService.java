package com.example.ap.api.service;


import com.example.ap.api.models.Cake;
import com.example.ap.api.models.Ingredients;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.Response;
import retrofit2.http.Path;

public interface CakeService {
    @GET("Cake")
    public Single<Response<ArrayList<Cake>>> GetCakes();
    @POST("Cake/filter")
    public Single<Response<ArrayList<Cake>>> GetAllCakesBy(@Body ArrayList<Ingredients> ingredients);
    @GET("Cake/{id}")
    public Single<Response<Cake>> GetCakeById(@Path("id") int id);
    @POST("Cake")
    public void AddCake(Cake cake);
    @PUT("Cake")
    public void UpdateCake(Cake cake);
    @DELETE("Cake/{id}")
    public void DeleteCake(int id);
}
