package com.example.themoviedatabase.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.themoviedatabase.R
import com.example.themoviedatabase.data.dto.Movie

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardMovie(movie: Movie, onMovieClick: (movieID: Int) -> Unit) {
    ElevatedCard(onClick = { onMovieClick(movie.id) }, modifier = Modifier.padding(start = 5.dp, end = 5.dp, bottom = 10.dp)) {
        Column(modifier = Modifier.padding(bottom = 10.dp)) {
            ImagenPelicula(movie)
            TituloPelicula(movie)
            FechaLanzamiento(movie)
        }
    }
}

@Composable
private fun TituloPelicula(movie: Movie) {
    Text(
        modifier = Modifier.padding(start = 10.dp, top = 10.dp, end = 10.dp, bottom = 2.dp),
        fontSize = MaterialTheme.typography.bodyLarge.fontSize,
        text = movie.title,
        fontWeight = FontWeight.Bold,
    )
}

@Composable
private fun ImagenPelicula(movie: Movie) {
    Column {
        AsyncImage(
            modifier = Modifier.fillMaxSize().height(240.dp),
            error = painterResource(R.drawable.empty_actor_and_moviecard),
            contentScale = ContentScale.FillBounds,
            placeholder = painterResource(R.drawable.placeholder),
            model = ImageRequest.Builder(LocalContext.current)
                .data("https://image.tmdb.org/t/p/w500/${movie.image}")
                .crossfade(true)
                .build(),
            contentDescription = null,
        )
    }
}

@Composable
private fun FechaLanzamiento(movie: Movie) {
    if ( ! movie.fechaLanzamiento.isNullOrBlank()) {
        Text(
            modifier = Modifier.padding(start = 10.dp),
            fontSize = MaterialTheme.typography.bodySmall.fontSize,
            fontWeight = FontWeight.Light,
            text = movie.getAnioLanzamiento().toString()
        )
    }
}