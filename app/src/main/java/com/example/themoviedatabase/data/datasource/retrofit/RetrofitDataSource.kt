package com.example.themoviedatabase.data.datasource.retrofit

import com.example.themoviedatabase.data.datasource.MoviesDataSource
import com.example.themoviedatabase.data.dto.api.ActorsAPI
import com.example.themoviedatabase.data.dto.api.DetailsMovieAPI
import com.example.themoviedatabase.data.dto.api.MovieAPI
import com.example.themoviedatabase.data.datasource.retrofit.service.DetailsMovieService
import com.example.themoviedatabase.data.datasource.retrofit.service.MoviesService
import com.example.themoviedatabase.data.datasource.retrofit.service.PopularMoviesService
import com.example.themoviedatabase.data.datasource.retrofit.service.SearchService
import com.example.themoviedatabase.data.datasource.retrofit.service.ActorsService
import retrofit2.Response

class Retrofit(
    private val popularMoviesService: PopularMoviesService = PopularMoviesService(),
    private val moviesService: MoviesService = MoviesService(),
    private val searchService: SearchService = SearchService(),
    private val detailsMovieService: DetailsMovieService = DetailsMovieService(),
    private val actorsMovieService: ActorsService = ActorsService()
): MoviesDataSource {

    override suspend fun getMovies(titleMovie: String): Response<MovieAPI> {
        return if (titleMovie.isBlank()) {
            popularMoviesService.getPopularMovies()
        } else {
            searchService.search(titleMovie)
        }
    }

    override suspend fun getMoreMovies(page: Int): Response<MovieAPI> {
        return moviesService.getMovies(page)
    }

    override suspend fun getMovieDetails(id: Int): Response<DetailsMovieAPI> {
        return detailsMovieService.getMovieDetails(id)
    }

    override suspend fun getActors(movieID: Int): Response<ActorsAPI> {
        return actorsMovieService.getActors(movieID)
    }
}