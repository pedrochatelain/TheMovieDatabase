package com.example.themoviedatabase.data.datasource

import com.example.themoviedatabase.data.dto.MovieAPI
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class Retrofit: MyDataSource {
    private val API_KEY: String = "7cfd73ddaed83f34b0dfc2d546213e40"
    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/movie/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private var service = retrofit.create(MovieService::class.java)

    override suspend fun getMovies(): Response<MovieAPI> {
        return service.getPopularMovies(API_KEY)
    }
}

interface MovieService {
    @GET("popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey: String): Response<MovieAPI>
}
