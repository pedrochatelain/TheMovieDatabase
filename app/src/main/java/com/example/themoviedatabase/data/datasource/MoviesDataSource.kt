package com.example.themoviedatabase.data.datasource

import com.example.themoviedatabase.data.dto.api.ActorsAPI
import com.example.themoviedatabase.data.dto.api.DetailsMovieAPI
import com.example.themoviedatabase.data.dto.api.MovieAPI
import retrofit2.Response

interface MoviesDataSource {

    suspend fun getMovies(titleMovie: String = ""): Response<MovieAPI>

    suspend fun getMoreMovies(page: Int): Response<MovieAPI>

    suspend fun searchMoreMovies(movieTitle: String, page: Int): Response<MovieAPI>

    suspend fun getMovieDetails(id: Int): Response<DetailsMovieAPI>

    suspend fun getActors(movieID: Int): Response<ActorsAPI>

}