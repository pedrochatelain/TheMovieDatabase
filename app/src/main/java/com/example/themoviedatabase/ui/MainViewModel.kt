package com.example.themoviedatabase.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(): ViewModel() {

    private var counter by mutableIntStateOf(0)

    fun getNumberOfTaps(): Int {
        return counter;
    }

    fun incrementCounter() {
        Log.i("incrementCounter", "incrementCounter clicked")
        counter++
    }

}