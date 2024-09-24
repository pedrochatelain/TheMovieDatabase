package com.example.themoviedatabase.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    private var counter by mutableIntStateOf(0)

    fun getNumberOfTaps(): Int {
        return counter;
    }

    fun incrementCounter() {
        Log.i("incrementCounter", "incrementCounter clicked")
        counter++
    }

}