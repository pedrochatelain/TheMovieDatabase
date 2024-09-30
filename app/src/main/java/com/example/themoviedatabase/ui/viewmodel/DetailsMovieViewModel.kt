package com.example.themoviedatabase.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themoviedatabase.data.Repository
import com.example.themoviedatabase.data.dto.DetailsMovie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsMovieViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    var loading by mutableStateOf(false)
    var isDisplayingMovie: Boolean = false
    var details by mutableStateOf<DetailsMovie?>(null)

    fun getMovieDetails(id: Int) {
        loading = true
        viewModelScope.launch {
            details = repository.getMovieDetails(id)
            loading = false
            isDisplayingMovie = true
        }
    }

}