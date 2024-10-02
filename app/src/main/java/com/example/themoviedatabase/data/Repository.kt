package com.example.themoviedatabase.data

import com.example.themoviedatabase.data.datasource.MyDataSource
import com.example.themoviedatabase.data.dto.Actor
import com.example.themoviedatabase.data.dto.DetailsMovie
import com.example.themoviedatabase.data.dto.Movie
import com.example.themoviedatabase.data.dto.api.ActorsAPI
import com.example.themoviedatabase.data.dto.api.DetailsMovieAPI
import com.example.themoviedatabase.data.dto.api.MovieAPI
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(private val dataSource: MyDataSource)  {

    suspend fun getMovies(): List<Movie> {
        val response: Response<MovieAPI> = dataSource.getMovies()
        if (response.isSuccessful) {
            return response.body()!!.movies
        }
        return emptyList()
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