package com.example.movies_db.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    // Example endpoint for teammates to copy
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int = 1
    ): Map<String, Any> // They will replace Map with a real DTO class
}