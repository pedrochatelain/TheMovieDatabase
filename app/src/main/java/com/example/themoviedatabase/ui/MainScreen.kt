package com.example.themoviedatabase.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun MainScreen(mainViewModel: MainViewModel) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(Modifier.height(Dp(200F))) {
            if (mainViewModel.isLoading()) {
                CircularProgressIndicator()
            } else {
                LazyColumn {
                    items(items = mainViewModel.movies) { movie ->
                        Row { Text(text = movie.toString()) }
                    }
                }
            }
        }
        Spacer(Modifier.height(height = Dp(10F)))
        Button(onClick = {
            mainViewModel.getMovies()
        }) {
            Text("Get movies!")
        }
    }
}