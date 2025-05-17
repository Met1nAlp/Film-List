package com.example.stajprojesi.Model

import retrofit2.http.GET
import retrofit2.http.Query

interface Api
{
    @GET("movie/popular")
    suspend fun getPopularMovies
    (
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "tr-TR",
        @Query("page") page: Int = 1
    ): MovieResponse
}

data class MovieResponse
(
    val results: List<Film>
)