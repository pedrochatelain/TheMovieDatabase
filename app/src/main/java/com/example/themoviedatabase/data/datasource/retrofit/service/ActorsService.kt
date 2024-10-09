package com.example.themoviedatabase.data.datasource.retrofit.service

import com.example.themoviedatabase.data.dto.api.ActorsAPI
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

class ActorsService {

    interface ActorsService {
        @GET("{id}/credits")
        suspend fun getActors(@Path("id") movieID: Int, @Query("api_key") apiKey: String): Response<ActorsAPI>
    }

    private val API_KEY: String = "7cfd73ddaed83f34b0dfc2d546213e40"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/movie/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(ActorsService::class.java)

    suspend fun getActors(movieID: Int): Response<ActorsAPI> {
        return service.getActors(movieID, API_KEY)
    }
}