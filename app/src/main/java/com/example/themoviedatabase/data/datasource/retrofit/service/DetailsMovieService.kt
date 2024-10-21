package com.example.themoviedatabase.data.datasource.retrofit.service

import com.example.themoviedatabase.data.datasource.API_KEY
import com.example.themoviedatabase.data.datasource.BASE_URL
import com.example.themoviedatabase.data.dto.api.DetailsMovieAPI
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Url
import javax.inject.Inject

class DetailsMovieService @Inject constructor(retrofit: Retrofit) {

    interface DetailsMovieService {
        @GET
        suspend fun getDetailsMovie(@Url url: String): Response<DetailsMovieAPI>
    }

    private val service = retrofit.create(DetailsMovieService::class.java)

    suspend fun getMovieDetails(id: Int): Response<DetailsMovieAPI> {
        val url = "$BASE_URL$id?api_key=$API_KEY"
        return service.getDetailsMovie(url)
    }
}