package com.example.themoviedatabase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.example.themoviedatabase.ui.MainViewModel
import com.example.themoviedatabase.ui.MainScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val myViewModel = ViewModelProvider(this)[MainViewModel::class.java]


        setContent { MainScreen(mainViewModel = myViewModel) }
    }

}