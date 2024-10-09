package com.example.themoviedatabase.data.datasource.retrofit.service

import com.example.themoviedatabase.data.dto.api.MovieAPI
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class SearchService {

    private interface SearchService {
        @GET("movie")
        suspend fun search(
            @Query("api_key") apiKey: String,
            @Query("query") query: String,
            @Query("page") page: Int?
        ): Response<MovieAPI>
    }

    private val API_KEY: String = "7cfd73ddaed83f34b0dfc2d546213e40"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/search/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(SearchService::class.java)

    suspend fun search(titleMovie: String): Response<MovieAPI> {
        return service.search(API_KEY, titleMovie, null)
    }

    suspend fun search(titleMovie: String, page: Int): Response<MovieAPI> {
        return service.search(API_KEY, titleMovie, page)
    }

}