package com.example.themoviedatabase.data.datasource

import com.example.themoviedatabase.data.dto.MovieAPI
import retrofit2.Response

interface MyDataSource {

    suspend fun getMovies(): Response<MovieAPI>

}
