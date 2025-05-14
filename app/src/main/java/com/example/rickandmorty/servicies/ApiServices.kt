package com.example.rickandmorty.servicies

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiServices {
    companion object {
        const val BASE_URL = "https://rickandmortyapi.com/api/"
        const val IMAGE_URL = "https://rickandmortyapi.com/api/character/avatar/"

        private var retrofit: Retrofit? = null

        fun getIntance(): Retrofit {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!
        }
    }
}