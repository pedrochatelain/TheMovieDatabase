package com.example.themoviedatabase.data.datasource.retrofit.service

import com.example.themoviedatabase.data.datasource.API_KEY
import com.example.themoviedatabase.data.datasource.BASE_URL
import com.example.themoviedatabase.data.dto.api.ActorsAPI
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Url
import javax.inject.Inject

class ActorsService @Inject constructor(retrofit: Retrofit) {

    interface ActorsService {
        @GET
        suspend fun getActors(@Url url: String): Response<ActorsAPI>
    }

    private val service = retrofit.create(ActorsService::class.java)

    suspend fun getActors(movieID: Int): Response<ActorsAPI> {
        val url = "$BASE_URL$movieID/credits?api_key=$API_KEY"
        return service.getActors(url)
    }
}