package com.example.themoviedatabase.ui.viewmodel

import android.graphics.BitmapFactory
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themoviedatabase.data.Repository
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

    var isDetailsLoaded by mutableStateOf(false)
    var isImageLoaded by mutableStateOf(false)
    var details by mutableStateOf<DetailsMovie?>(null)
    var image by mutableStateOf(ImageBitmap(1, 1))

    private fun loadDetails(id: Int): Job {
        return viewModelScope.launch {
            details = repository.getMovieDetails(id)
            isDetailsLoaded = true
        }
    }

    private fun loadImage(poster: String): Job {
        return viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val url = URL("https://image.tmdb.org/t/p/w500/${poster}")
                val bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream())
                image = bitmap.asImageBitmap()
                isImageLoaded = true
            }
        }
    }

    fun loadMovie(id: Int) {
        viewModelScope.launch {
            loadDetails(id).join()
            loadImage(details!!.portada)
        }
    }

}