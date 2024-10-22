package com.example.themoviedatabase.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.themoviedatabase.MovieDetailRoute
import com.example.themoviedatabase.data.Repository
import com.example.themoviedatabase.data.dto.ResponseGetDetailsMovie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailsMovieViewModel @Inject constructor(
    private val repository: Repository,
    state: SavedStateHandle
): ViewModel() {

    var emptyDetails by mutableStateOf(false)
    var errorConnection by mutableStateOf(false)
    var successDetailsMovie by mutableStateOf(false)
    var isLoading by mutableStateOf(true)
    lateinit var details: ResponseGetDetailsMovie
    val movieDetailRoute: MovieDetailRoute = state.toRoute<MovieDetailRoute>()

    init {
        loadMovie(movieDetailRoute.id)
    }

    fun loadMovie(id: Int) {
        isLoading = true
        viewModelScope.launch {
            details = repository.getMovieDetails(id)
            updateScreen()
        }
    }

    private fun updateScreen() {
        if (details.isSuccessful) {
            successDetailsMovie = true
            errorConnection = false
        } else {
            if (details.errorConnection) {
                errorConnection = true
            } else {
                emptyDetails = true
                errorConnection = false
            }
        }
        isLoading = false
    }

}