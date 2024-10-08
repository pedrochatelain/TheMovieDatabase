package com.example.themoviedatabase.data

import com.example.themoviedatabase.data.datasource.MyDataSource
import com.example.themoviedatabase.data.dto.Actor
import com.example.themoviedatabase.data.dto.DetailsMovie
import com.example.themoviedatabase.data.dto.ResponseGetPopularMovies
import com.example.themoviedatabase.data.dto.api.ActorsAPI
import com.example.themoviedatabase.data.dto.api.DetailsMovieAPI
import com.example.themoviedatabase.data.dto.api.MovieAPI
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(private val dataSource: MyDataSource)  {

    suspend fun getMovies(): ResponseGetPopularMovies {
        try {
            val result: Response<MovieAPI> = dataSource.getMovies()
            if (result.isSuccessful) {
                return ResponseGetPopularMovies(
                    movies = result.body()!!.movies,
                    httpCode = 200,
                    isSuccessful = true
                )
            }
        } catch (_: Throwable) {}
        return ResponseGetPopularMovies(httpCode = 500, isSuccessful = false)
    }

    suspend fun searchMovies(titleMovie: String): ResponseGetPopularMovies {
        try {
            val result: Response<MovieAPI> = dataSource.searchMovies(titleMovie)
            if (result.isSuccessful) {
                return ResponseGetPopularMovies(
                    movies = result.body()!!.movies,
                    httpCode = 200,
                    isSuccessful = true
                )
            }
        } catch (_: Throwable) {}
        return ResponseGetPopularMovies(httpCode = 500, isSuccessful = false)
    }

    suspend fun getMoreMovies(page: Int): ResponseGetPopularMovies {
        try {
            val result: Response<MovieAPI> = dataSource.getMoreMovies(page)
            if (result.isSuccessful) {
                return ResponseGetPopularMovies(
                    movies = result.body()!!.movies,
                    httpCode = 200,
                    isSuccessful = true
                )
            }
        } catch (_: Throwable) {}
        return ResponseGetPopularMovies(httpCode = 500, isSuccessful = false)
    }

    suspend fun getMovieDetails(id: Int): DetailsMovie? {
        val response: Response<DetailsMovieAPI> = dataSource.getMovieDetails(id)
        if (response.isSuccessful) {
            return DetailsMovie(response.body()!!)
        }
        return null
    }

    suspend fun getActors(movieID: Int): List<Actor> {
        val response: Response<ActorsAPI> = dataSource.getActors(movieID)
        if (response.isSuccessful) {
            return response.body()!!.actores
        }
        return emptyList()
    }

}