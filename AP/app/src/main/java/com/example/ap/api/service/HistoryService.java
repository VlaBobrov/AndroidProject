package com.example.ap.api.service;

import com.example.ap.api.models.Cake;
import com.example.ap.api.models.History;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface HistoryService {
    @GET("History")
    public Single<Response<List<History>>> GetHistories();
    @GET("History/{id}")
    public History GetHistoryById(int id);
    @POST("History")
    public Single<Response<History>> AddHistory(@Body History history);
    @PUT("History")
    public Single<Response<History>> UpdateHistory(@Body History history);
    @DELETE("History/{id}")
    public void DeleteHistory(int id);
}
