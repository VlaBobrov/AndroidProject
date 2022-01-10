package com.example.ap.api.service;

import com.example.ap.api.models.Cake;
import com.example.ap.api.models.Ingredients;

import java.util.ArrayList;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface IngredientService {
    @GET("Ingredient")
    public Single<Response<ArrayList<Ingredients>>> GetIngredients();
    @GET("Ingredient/{id}")
    public Ingredients GetIngredientById(int id);
    @POST("Ingredient")
    public void AddIngredient(Ingredients ingredient);
    @PUT("Ingredient")
    public void UpdateIngredient(Ingredients ingredient);
    @DELETE("Ingredient/{id}")
    public void DeleteIngredient(int id);
}
