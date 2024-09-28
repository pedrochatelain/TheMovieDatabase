package com.example.themoviedatabase.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.themoviedatabase.data.Repository
import com.example.themoviedatabase.data.dto.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    private var loading by mutableStateOf(false)
    var movies = mutableStateListOf<Movie>()

    fun isLoading(): Boolean {
        return loading
    }

    fun getMovies() {
        loading = true
        viewModelScope.launch {
            val response: List<Movie> = repository.getMovies()
            movies.addAll(response)
            loading = false
        }
    }

}