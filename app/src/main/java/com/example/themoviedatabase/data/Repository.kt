package com.example.themoviedatabase.data

import com.example.themoviedatabase.data.datasource.MoviesDataSource
import com.example.themoviedatabase.data.dto.Actor
import com.example.themoviedatabase.data.dto.DetailsMovie
import com.example.themoviedatabase.data.dto.ResponseGetDetailsMovie
import com.example.themoviedatabase.data.dto.ResponseGetMovies
import com.example.themoviedatabase.data.dto.api.ActorsAPI
import com.example.themoviedatabase.data.dto.api.DetailsMovieAPI
import com.example.themoviedatabase.data.dto.api.MovieAPI
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(private val dataSource: MoviesDataSource)  {

    suspend fun getMovies(title: String = "", page: Int = 1): ResponseGetMovies {
        try {
            val result: Response<MovieAPI> = dataSource.getMovies(title, page)
            if (result.isSuccessful) {
                return ResponseGetMovies(
                    movies = result.body()!!.movies,
                    httpCode = 200,
                    isSuccessful = true
                )
            }
        } catch (_: Throwable) {}
        return ResponseGetMovies(httpCode = 500, isSuccessful = false)
    }

    suspend fun getMovieDetails(id: Int): ResponseGetDetailsMovie {
        try {
            val result: Response<DetailsMovieAPI> = dataSource.getMovieDetails(id)
            if (result.isSuccessful) {
                val movie = DetailsMovie(result.body()!!)
                return ResponseGetDetailsMovie.success(movie)
            }
            return ResponseGetDetailsMovie.failure()
        } catch (error: Throwable) {
            return ResponseGetDetailsMovie.errorNetwork()
        }
    }

    suspend fun getActors(movieID: Int): List<Actor> {
        val response: Response<ActorsAPI> = dataSource.getActors(movieID)
        if (response.isSuccessful) {
            return response.body()!!.actores
        }
        return emptyList()
    }

}