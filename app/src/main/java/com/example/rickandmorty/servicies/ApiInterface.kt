package com.example.rickandmorty.servicies

import Characters
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("character")
    suspend fun getCharacters(@Query("page") page: Int): Characters
}