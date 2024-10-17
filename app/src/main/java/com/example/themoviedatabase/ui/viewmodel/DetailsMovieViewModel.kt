package com.example.themoviedatabase.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themoviedatabase.data.Repository
import com.example.themoviedatabase.data.dto.Actor
import com.example.themoviedatabase.data.dto.ResponseGetDetailsMovie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailsMovieViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    var emptyDetails by mutableStateOf(false)
    var errorConnection by mutableStateOf(false)
    var successDetailsMovie by mutableStateOf(false)
    private var isMovieSelected: Boolean = false
    var isLoading by mutableStateOf(true)
    lateinit var details: ResponseGetDetailsMovie

    private fun loadDetails(id: Int): Job {
        return viewModelScope.launch {
            details = repository.getMovieDetails(id)
        }
    }

    private fun loadActors(movieID: Int): Job {
        return viewModelScope.launch {
            val response: List<Actor> = repository.getActors(movieID)
            details.movie!!.actores.addAll(response)
        }
    }

    fun loadMovie(id: Int) {
        if ( ! isMovieSelected) { // flag to protect simultaneous taps on movies
            isMovieSelected = true
            isLoading = true
            viewModelScope.launch {
                loadDetails(id).join()
                if (details.isSuccessful) {
                    loadActors(id).join()
                    successDetailsMovie = true
                    errorConnection = false
                } else {
                    if (details.errorConnection) {
                        errorConnection = true
                    }
                    else {
                        emptyDetails = true
                        errorConnection = false
                    }
                }
                isLoading = false
                isMovieSelected = false
            }
        }
    }

}