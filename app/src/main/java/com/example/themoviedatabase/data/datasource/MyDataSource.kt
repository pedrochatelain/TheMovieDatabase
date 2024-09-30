package com.example.themoviedatabase.data.datasource

import com.example.themoviedatabase.data.dto.api.DetailsMovieAPI
import com.example.themoviedatabase.data.dto.api.MovieAPI
import retrofit2.Response

interface MyDataSource {

    suspend fun getMovies(): Response<MovieAPI>

    suspend fun getMovieDetails(id: Int): Response<DetailsMovieAPI>

}
