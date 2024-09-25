package com.example.themoviedatabase.data

import com.example.themoviedatabase.data.datasource.MyDataSource
import com.example.themoviedatabase.data.dto.Movie
import com.example.themoviedatabase.data.dto.MovieAPI
import kotlinx.coroutines.delay
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(private val dataSource: MyDataSource)  {

    fun addNumber(number: Int) {
        dataSource.addNumber(number)
    }

    fun printNumbers() {
        dataSource.printNumbers()
    }

    suspend fun fakeNetworkCall(): String {
        delay(1500)
        return "Network call finished"
    }

    suspend fun getMovies(): List<Movie> {
        val response: Response<MovieAPI> = Retrofit().getPopularMovies()
        if (response.isSuccessful) {
            return response.body()!!.movies
        }
        return emptyList()
    }

}