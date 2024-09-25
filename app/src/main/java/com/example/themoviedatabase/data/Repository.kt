package com.example.themoviedatabase.data

import com.example.themoviedatabase.data.datasource.MyDataSource
import com.example.themoviedatabase.data.dto.Movie
import com.example.themoviedatabase.data.dto.MovieAPI
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(private val dataSource: MyDataSource)  {

    suspend fun getMovies(): List<Movie> {
        val response: Response<MovieAPI> = Retrofit().getPopularMovies()
        if (response.isSuccessful) {
            return response.body()!!.movies
        }
        return emptyList()
    }

}