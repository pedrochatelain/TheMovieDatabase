package com.example.themoviedatabase.data.datasource.retrofit

import com.example.themoviedatabase.data.datasource.MoviesDataSource
import com.example.themoviedatabase.data.dto.api.ActorsAPI
import com.example.themoviedatabase.data.dto.api.DetailsMovieAPI
import com.example.themoviedatabase.data.dto.api.MovieAPI
import com.example.themoviedatabase.data.datasource.retrofit.service.DetailsMovieService
import com.example.themoviedatabase.data.datasource.retrofit.service.MoviesService
import com.example.themoviedatabase.data.datasource.retrofit.service.SearchService
import com.example.themoviedatabase.data.datasource.retrofit.service.ActorsService
import retrofit2.Response
import javax.inject.Inject

class RetrofitDataSource @Inject constructor (
    private val moviesService: MoviesService,
    private val searchService: SearchService,
    private val detailsMovieService: DetailsMovieService,
    private val actorsMovieService: ActorsService
): MoviesDataSource {

    override suspend fun getMovies(titleMovie: String, page: Int): Response<MovieAPI> {
        return if (titleMovie.isBlank()) {
            moviesService.getMovies(page)
        } else {
            searchService.search(titleMovie, page)
        }
    }

    override suspend fun getMovieDetails(id: Int): Response<DetailsMovieAPI> {
        return detailsMovieService.getMovieDetails(id)
    }

    override suspend fun getActors(movieID: Int): Response<ActorsAPI> {
        return actorsMovieService.getActors(movieID)
    }
}