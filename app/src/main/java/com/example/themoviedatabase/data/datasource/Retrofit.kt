package com.example.themoviedatabase.data.datasource

import com.example.themoviedatabase.data.dto.api.ActorsAPI
import com.example.themoviedatabase.data.dto.api.DetailsMovieAPI
import com.example.themoviedatabase.data.dto.api.MovieAPI
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

class Retrofit: MyDataSource {
    private val API_KEY: String = "7cfd73ddaed83f34b0dfc2d546213e40"
    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/movie/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private var service = retrofit.create(MovieService::class.java)

    override suspend fun getMovies(titleMovie: String): Response<MovieAPI> {
        if (titleMovie.isBlank()) {
            return service.getPopularMovies(API_KEY)
        } else {
            val service = searchService()
            return service.search(API_KEY, titleMovie)
        }
    }

    private fun searchService(): SearchService {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/search/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(SearchService::class.java)
    }

    override suspend fun getMoreMovies(page: Int): Response<MovieAPI> {
        val moreMoviesService = retrofit.create(MoreMoviesService::class.java)
        return moreMoviesService.getMoreMovies(API_KEY, page)
    }

    override suspend fun getMovieDetails(id: Int): Response<DetailsMovieAPI> {
        val detailsService = retrofit.create(DetailsMovieService::class.java)
        return detailsService.getDetailsMovie(id, API_KEY)
    }

    override suspend fun getActors(movieID: Int): Response<ActorsAPI> {
        val detailsService = retrofit.create(ActorsMovieService::class.java)
        return detailsService.getActorsMovie(movieID, API_KEY)
    }
}

interface MovieService {
    @GET("popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey: String): Response<MovieAPI>
}

interface SearchService {
    @GET("movie")
    suspend fun search(@Query("api_key") apiKey: String, @Query("query") query: String): Response<MovieAPI>
}

interface MoreMoviesService {
    @GET("popular")
    suspend fun getMoreMovies(@Query("api_key") apiKey: String, @Query("page") page: Int): Response<MovieAPI>
}

interface DetailsMovieService {
    @GET("{id}")
    suspend fun getDetailsMovie(@Path("id") id: Int, @Query("api_key") apiKey: String): Response<DetailsMovieAPI>
}

interface ActorsMovieService {
    @GET("{id}/credits")
    suspend fun getActorsMovie(@Path("id") id: Int, @Query("api_key") apiKey: String): Response<ActorsAPI>
}