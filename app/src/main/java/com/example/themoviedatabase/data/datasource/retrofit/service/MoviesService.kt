package com.example.themoviedatabase.data.datasource.retrofit.service

import com.example.themoviedatabase.data.dto.api.MovieAPI
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class MoviesService {

    interface MoreMoviesService {
        @GET("popular")
        suspend fun getMoreMovies(@Query("api_key") apiKey: String, @Query("page") page: Int): Response<MovieAPI>
    }

    private val API_KEY: String = "7cfd73ddaed83f34b0dfc2d546213e40"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/movie/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(MoreMoviesService::class.java)

    suspend fun getMovies(page: Int): Response<MovieAPI> {
        return service.getMoreMovies(API_KEY, page)
    }

}