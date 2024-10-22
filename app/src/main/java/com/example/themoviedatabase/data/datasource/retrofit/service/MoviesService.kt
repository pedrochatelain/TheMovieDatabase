package com.example.themoviedatabase.data.datasource.retrofit.service

import com.example.themoviedatabase.data.datasource.API_KEY
import com.example.themoviedatabase.data.datasource.BASE_URL
import com.example.themoviedatabase.data.dto.api.MovieAPI
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Url
import java.util.Locale
import javax.inject.Inject

class MoviesService @Inject constructor(retrofit: Retrofit) {

    interface MoviesService {
        @GET
        suspend fun getMovies(@Url url: String): Response<MovieAPI>
    }

    private val service = retrofit.create(MoviesService::class.java)

    suspend fun getMovies(page: Int): Response<MovieAPI> {
        val language = Locale.getDefault().toLanguageTag()
        val url = "${BASE_URL}popular?api_key=$API_KEY&page=$page&language=$language"
        return service.getMovies(url)
    }

}