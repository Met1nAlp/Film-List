package com.example.stajprojesi

import com.example.stajprojesi.Model.Api
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitIstemci
{
    private const val BASE_URL = "https://api.themoviedb.org/3/"

    val tmdbApi: Api by lazy()
    {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)
    }
}