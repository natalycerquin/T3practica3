package com.example.practica;

import com.example.practica.model.classPokemon;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface service {

    @GET("N00019639")
    Call<List<classPokemon>> getListPokemones();

    @POST("N00019639/crear")
    Call<Void> postCreatePokemon(@Body classPokemon pokemon);

}
