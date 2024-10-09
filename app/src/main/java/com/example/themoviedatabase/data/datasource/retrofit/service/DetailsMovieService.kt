package com.example.themoviedatabase.data.datasource.retrofit.service

import com.example.themoviedatabase.data.dto.api.DetailsMovieAPI
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

class DetailsMovieService {

    interface DetailsMovieService {
        @GET("{id}")
        suspend fun getDetailsMovie(@Path("id") id: Int, @Query("api_key") apiKey: String): Response<DetailsMovieAPI>
    }

    private val API_KEY: String = "7cfd73ddaed83f34b0dfc2d546213e40"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/movie/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(DetailsMovieService::class.java)

    suspend fun getMovieDetails(id: Int): Response<DetailsMovieAPI> {
        return service.getDetailsMovie(id, API_KEY)
    }
}