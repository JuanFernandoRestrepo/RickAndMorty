package com.example.rickandmorty.servicies

import Characters
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("character")
    fun getCharacters(): Call<Characters>
}