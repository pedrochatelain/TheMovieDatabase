package com.example.themoviedatabase.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themoviedatabase.data.Repository
import com.example.themoviedatabase.data.dto.Movie
import com.example.themoviedatabase.data.dto.ResponseGetPopularMovies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    var error by mutableStateOf(false)
    var moviesLoaded by mutableStateOf(false)
    var movies = mutableStateListOf<Movie>()
    var loading by mutableStateOf(true)

    fun loadMovies() {
        error = false
        loading = true
        viewModelScope.launch {
            val response: ResponseGetPopularMovies = repository.getMovies()
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

}