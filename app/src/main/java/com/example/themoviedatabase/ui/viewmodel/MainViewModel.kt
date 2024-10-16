package com.example.themoviedatabase.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.focus.FocusRequester
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themoviedatabase.data.Repository
import com.example.themoviedatabase.data.dto.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    private var search: Job = Job()
    private var page: Int = 1
    var isInitialized by mutableStateOf(false)
    var moviesReady by mutableStateOf(false)
    var error by mutableStateOf(false)
    var movies = mutableStateListOf<Movie>()
    var loading by mutableStateOf(true)
    var searchedMovie by mutableStateOf("")
    var isLoadingMoreMovies by mutableStateOf(false)
    var errorLoadMoreMovies by mutableStateOf(false)
    var noResults by mutableStateOf(false)
    val focusRequester = FocusRequester()

    fun initialize() {
        viewModelScope.launch {
            loadMovies().join()
            isInitialized = true
        }
    }

    fun loadMovies(): Job {
        search = viewModelScope.launch {
            loading = true
            val response = repository.getMovies(searchedMovie)
            if (response.isSuccessful) {
                movies = SnapshotStateList()
                movies.addAll(response.movies)
                checkResults()
            }
            else {
                error = true
                moviesReady = false
            }
            loading = false
        }
        return search
    }

    private fun checkResults() {
        if (movies.size > 0) {
            moviesReady = true
            noResults = false
        }
        else {
            moviesReady = false
            noResults = true
        }
    }

    fun loadMoreMovies() {
        viewModelScope.launch {
            isLoadingMoreMovies = true
            val response = repository.getMoreMovies(searchedMovie, ++page)
            if (response.isSuccessful) {
                movies.addAll(response.movies)
                errorLoadMoreMovies = false
            } else {
                errorLoadMoreMovies = true
                --page
            }
            isLoadingMoreMovies = false
        }
    }

    fun cancelSearch() {
        if (search.isActive) search.cancel()
    }

    fun backToPopularMovies() {
        searchedMovie = ""
        loadMovies()
    }

    fun focusSearchBox() {
        focusRequester.requestFocus()
    }

}