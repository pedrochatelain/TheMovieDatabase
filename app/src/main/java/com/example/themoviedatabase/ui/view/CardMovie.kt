package com.example.themoviedatabase.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
fun CardMovie(movie: Movie, onMovieClick: (movieID: Int) -> Unit) {
    ElevatedCard(onClick = { onMovieClick(movie.id) }, modifier = Modifier.padding(5.dp)) {
        ImagenPelicula(movie)
        TituloPelicula(movie)
        FechaLanzamiento(movie)
    }
}

@Composable
private fun TituloPelicula(movie: Movie) {
    Text(
        modifier = Modifier.padding(start = 10.dp, top = 10.dp, end = 10.dp, bottom = 2.dp),
        fontSize = 16.sp,
        text = movie.title,
        fontWeight = FontWeight.Bold,
    )
}

@Composable
private fun ImagenPelicula(movie: Movie) {
    Column {
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            error = painterResource(R.drawable.placeholder),
            placeholder = painterResource(R.drawable.placeholder),
            model = "https://image.tmdb.org/t/p/w500/${movie.image}",
            contentDescription = null,
        )
    }
}

@Composable
private fun FechaLanzamiento(movie: Movie) {
    if ( ! movie.fechaLanzamiento.isNullOrBlank()) {
        Text(
            modifier = Modifier.padding(start = 10.dp, bottom = 10.dp),
            fontSize = 14.sp,
            fontWeight = FontWeight.Light,
            text = movie.getAnioLanzamiento().toString()
        )
    }
}