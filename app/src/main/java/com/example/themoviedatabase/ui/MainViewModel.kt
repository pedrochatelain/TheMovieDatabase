package com.example.themoviedatabase.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themoviedatabase.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    private var counter by mutableIntStateOf(0)
    private var loading by mutableStateOf(false)
    var result by mutableStateOf("")

    fun getNumberOfTaps(): Int {
        return counter;
    }

    fun isLoading(): Boolean {
        return loading
    }

    fun incrementCounter() {
        loading = true
        viewModelScope.launch {
            result = repository.fakeNetworkCall()
            loading = false
        }
        Log.i("incrementCounter", "incrementCounter clicked")
        counter++
    }

    fun persist() {
        repository.addNumber(counter)
        repository.printNumbers()
    }

}