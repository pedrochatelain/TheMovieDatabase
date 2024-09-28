package com.example.themoviedatabase.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.themoviedatabase.R
import com.example.themoviedatabase.data.dto.Movie

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardMovie(movie: Movie, onMovieClick: (movieID: String) -> Unit) {
    ElevatedCard(onClick = { onMovieClick(movie.originalTitle) }, modifier = Modifier.padding(5.dp)) {
        Column {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                placeholder = painterResource(R.drawable.placeholder),
                model = "https://image.tmdb.org/t/p/w500/${movie.image}",
                contentDescription = null,
            )
        }
        Text(
            modifier = Modifier.padding(start = 10.dp, top = 10.dp).width(230.dp),
            fontSize = 16.sp,
            text = movie.originalTitle,
            fontWeight = FontWeight.Bold,
        )
        Text(
            modifier = Modifier.padding(start = 10.dp, bottom = 10.dp),
            fontSize = 14.sp,
            fontWeight = FontWeight.Light,
            text = "original language: ${movie.originalLanguage}"
        )
    }
}