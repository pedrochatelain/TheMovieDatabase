package com.example.themoviedatabase.data

import com.example.themoviedatabase.data.dto.MovieAPI
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class Retrofit {

    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/movie/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    suspend fun getPopularMovies(): Response<MovieAPI> {
        val service = retrofit.create(MovieService::class.java)
        val response: Response<MovieAPI> = service.getPopularMovies("7cfd73ddaed83f34b0dfc2d546213e40")
        return response
    }

    interface MovieService {
        @GET("popular")
        suspend fun getPopularMovies(@Query("api_key") apiKey: String): Response<MovieAPI>
    }

}
