package com.example.themoviedatabase.data

import com.example.themoviedatabase.data.datasource.MoviesDataSource
import com.example.themoviedatabase.data.dto.Actor
import com.example.themoviedatabase.data.dto.DetailsMovie
import com.example.themoviedatabase.data.dto.ResponseGetMovies
import com.example.themoviedatabase.data.dto.api.ActorsAPI
import com.example.themoviedatabase.data.dto.api.DetailsMovieAPI
import com.example.themoviedatabase.data.dto.api.MovieAPI
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(private val dataSource: MoviesDataSource)  {

    suspend fun getMovies(title: String = ""): ResponseGetMovies {
        try {
            val result: Response<MovieAPI> = dataSource.getMovies(title)
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

    suspend fun getMoreMovies(movieTitle: String, page: Int): ResponseGetMovies {
        if (movieTitle.isNotBlank()) {
            return searchMoreMovies(movieTitle, page)
        } else {
            try {
                val result: Response<MovieAPI> = dataSource.getMoreMovies(page)
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
    }

    suspend fun searchMoreMovies(movieTitle: String, page: Int): ResponseGetMovies {
        try {
            val result: Response<MovieAPI> = dataSource.searchMoreMovies(movieTitle, page)
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