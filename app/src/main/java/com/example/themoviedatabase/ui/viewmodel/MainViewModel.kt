package com.example.themoviedatabase.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themoviedatabase.data.Repository
import com.example.themoviedatabase.data.dto.Movie
import com.example.themoviedatabase.data.dto.ResponseGetMovies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    private var search: Job = Job()
    var isLoadingSearch by mutableStateOf(false)
    var errorLoadMoreMovies by mutableStateOf(false)
    private var page: Int = 1
    var loadingMoreMovies by mutableStateOf(true)
    var error by mutableStateOf(false)
    var moviesLoaded by mutableStateOf(false)
    var movies = mutableStateListOf<Movie>()
    var loading by mutableStateOf(true)
    var searchedMovie by mutableStateOf("")

    fun loadMovies() {
        error = false
        loading = true
        viewModelScope.launch {
            val response: ResponseGetMovies = repository.getMovies()
            if (response.isSuccessful) {
                movies.addAll(response.movies)
                moviesLoaded = true
            }
            else {
                error = true
            }
            loading = false
        }
    }

    fun searchMovie(titleMovie: String) {
        if (search.isActive) {
            search.cancel()
        }
        isLoadingSearch = true
        searchedMovie = titleMovie
        search = viewModelScope.launch {
            val response: ResponseGetMovies = repository.getMovies(titleMovie)
            if (response.isSuccessful) {
                movies = SnapshotStateList()
                movies.addAll(response.movies)
                page = 1
            }
            else {
                error = true
            }
            isLoadingSearch = false
        }
    }

    fun loadMoreMovies() {
        loadingMoreMovies = true
        errorLoadMoreMovies = false
        lateinit var response: ResponseGetMovies
        viewModelScope.launch {
            if (searchedMovie.isNotBlank()) {
                response = repository.searchMoreMovies(searchedMovie, ++page)
            } else {
                response = repository.getMoreMovies(++page)
            }
            if (response.isSuccessful) {
                movies.addAll(response.movies)
            } else {
                errorLoadMoreMovies = true
                --page
            }
            loadingMoreMovies = false
        }
    }
}