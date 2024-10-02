package com.example.themoviedatabase.ui.viewmodel

import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themoviedatabase.data.Repository
import com.example.themoviedatabase.data.dto.Actor
import com.example.themoviedatabase.data.dto.DetailsMovie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL
import javax.inject.Inject


@HiltViewModel
class DetailsMovieViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    var isDisplayingDetails: Boolean = false
    var loading by mutableStateOf(false)
    var actors = mutableStateListOf<Actor>()
    var details by mutableStateOf<DetailsMovie?>(null)
    var image by mutableStateOf(ImageBitmap(1, 1))

    private fun loadDetails(id: Int): Job {
        return viewModelScope.launch {
            details = repository.getMovieDetails(id)
        }
    }

    private fun loadActors(movieID: Int): Job {
        return viewModelScope.launch {
            val response: List<Actor> = repository.getActors(movieID)
            actors.addAll(response)
        }
    }

    private fun loadImage(poster: String): Job {
        return viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val url = URL("https://image.tmdb.org/t/p/w500/${poster}")
                val bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream())
                image = bitmap.asImageBitmap()
            }
        }
    }

    fun loadMovie(id: Int) {
        Log.i("loadMovie", "loadMovie")
        loading = true
        viewModelScope.launch {
            loadDetails(id).join()
            loadImage(details!!.portada).join()
            loadActors(id).join()
            details!!.actores = actors
            loading = false
            isDisplayingDetails = true
        }
    }

}