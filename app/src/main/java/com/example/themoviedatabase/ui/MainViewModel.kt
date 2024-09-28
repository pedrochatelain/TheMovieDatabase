package com.example.themoviedatabase.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themoviedatabase.data.Repository
import com.example.themoviedatabase.data.dto.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    var loading by mutableStateOf(false)
    var isDisplayingMovies: Boolean = false
    var movies = mutableStateListOf<Movie>()

    fun getMovies() {
        loading = true
        viewModelScope.launch {
            val response: List<Movie> = repository.getMovies()
            movies.addAll(response)
            loading = false
            isDisplayingMovies = true
        }
    }

}