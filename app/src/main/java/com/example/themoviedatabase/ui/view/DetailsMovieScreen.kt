package com.example.themoviedatabase.ui.view

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Clear
import androidx.compose.material.icons.sharp.DateRange
import androidx.compose.material.icons.sharp.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.themoviedatabase.R
import com.example.themoviedatabase.data.dto.Actor
import com.example.themoviedatabase.data.dto.DetailsMovie
import com.example.themoviedatabase.data.dto.Genero
import com.example.themoviedatabase.ui.viewmodel.DetailsMovieViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailsMovieScreen(idMovie: Int, viewModel: DetailsMovieViewModel = hiltViewModel()) {
    // initialize view
    LaunchedEffect(Unit) { viewModel.loadMovie(idMovie) }

    Scaffold {
        if (viewModel.isLoading) {
            LoadingScreen()
        } else {
            MovieDetails(viewModel.details)
        }
    }
}

@Composable
private fun MovieDetails(movie: DetailsMovie?) {
    if (movie == null) {
        EmptyDetails()
    } else {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(bottom = 20.dp)
        ) {
            PictureMovie(movie.image)
            TituloPelicula(movie.titulo, movie.titulo_original)
            Rating(movie.rating)
            FechaLanzamiento(movie.fecha_lanzamiento)
            Resumen(movie.resumen)
            Genres(movie.generos)
            Cast(movie.actores)
        }
    }
}

@Composable
private fun Resumen(resumen: String?) {
    if ( ! resumen.isNullOrBlank()) {
        Text(
            text = resumen,
            modifier = Modifier.padding(top = 20.dp, start = 20.dp, end = 20.dp, bottom = 10.dp),
            fontSize = 18.sp
        )
    }
}

@Composable
private fun TituloPelicula(titulo: String, tituloOriginal: String) {
    Text(
        text = tituloOriginal,
        fontSize = 26.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(start = 20.dp, end = 20.dp)
    )
    if (tituloOriginal != titulo) {
        Text(
            text = titulo,
            fontSize = 20.sp,
            fontWeight = FontWeight.Light,
            modifier = Modifier.padding(start = 20.dp, end = 20.dp)
        )
    }
}

@Composable
fun EmptyDetails() {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = stringResource(R.string.empty_details_movie), fontSize = MaterialTheme.typography.headlineMedium.fontSize)
        Icon(
            modifier = Modifier.size(50.dp),
            contentDescription = stringResource(R.string.content_description_icon_empty_details_movie),
            imageVector = Icons.Sharp.Clear,
            tint = MaterialTheme.colorScheme.error
        )
    }
}

@Composable
private fun FechaLanzamiento(fechaLanzamiento: String) {
    if (fechaLanzamiento.isNotBlank()) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(top = 5.dp)) {
            Icon(
                modifier = Modifier
                    .padding(start = 18.dp)
                    .size(19.dp),
                tint = MaterialTheme.colorScheme.primary,
                imageVector = Icons.Sharp.DateRange,
                contentDescription = stringResource(R.string.daterange_icon)
            )
            Text(
                text = fechaLanzamiento,
                modifier = Modifier.padding(start = 6.dp),
                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                fontWeight = FontWeight.Light
            )
        }
    }
}

@Composable
private fun Cast(actores: List<Actor>?) {
    if ( ! actores.isNullOrEmpty()) {
        Text(
            text = stringResource(R.string.cast_title),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
             .padding(top = 40.dp, bottom = 10.dp)
             .padding(start = 20.dp)
        )
        LazyRow(
          modifier = Modifier
            .padding(start = 20.dp)
        ){
            items(items = actores) { actor ->
                ActorCard(actor)
            }
        }
    }
}

@Composable
private fun PictureMovie(image: ImageBitmap?) {
    Box(modifier = Modifier.padding(bottom = 30.dp)){
        if (image != null) {
            Image(
                modifier = Modifier
                    .height(220.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.FillBounds,
                bitmap = image,
                contentDescription = stringResource(R.string.image_movie)
            )
        } else {
            Image(
                painter = painterResource(R.drawable.placeholder),
                stringResource(R.string.null_image_movie),
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
private fun Rating(rating: Double) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(top = 20.dp, bottom = 4.dp)
    ){
        Icon(
            modifier = Modifier
                .padding(start = 18.dp)
                .size(20.dp),
            tint = Color.hsv(47f, .87f, .96f),
            imageVector = Icons.Sharp.Star,
            contentDescription = stringResource(R.string.icon_rating)
        )
        Text(
            text = "$rating",
            modifier = Modifier.padding(start = 6.dp),
            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
            fontWeight = FontWeight.Light
        )
        Text(text = stringResource(R.string.rating), fontSize = MaterialTheme.typography.bodyLarge.fontSize, fontWeight = FontWeight.Thin)
    }
}

@Composable
@OptIn(ExperimentalLayoutApi::class)
private fun Genres(generos: List<Genero>) {
    FlowRow(modifier = Modifier.padding(start = 20.dp, top = 10.dp), content = {
        for (genero in generos) {
            Box(
                modifier = Modifier
                    .padding(top = 10.dp, end = 10.dp)
                    .border(
                        BorderStroke(1.dp, color = MaterialTheme.colorScheme.primary),
                        shape = RoundedCornerShape(20)
                    )
            ) {
                Text(
                    text = genero.toString(),
                    fontWeight = FontWeight.Light,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(4.dp)
                        .padding(start = 10.dp, end = 10.dp)
                )
            }
        }
    })
}

@Composable
@Preview
private fun LoadingScreen() {
    Column(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            CircularProgressIndicator()
        }
    }
}


@Composable
fun ActorCard(actor: Actor) {
    Column(modifier = Modifier
        .height(200.dp)
        .width(110.dp)
        .padding(end = 20.dp)
    ){
        AsyncImage(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(10.dp)),
            error = painterResource(R.drawable.placeholder),
            placeholder = painterResource(R.drawable.placeholder),
            model = "https://image.tmdb.org/t/p/w500/${actor.foto}",
            contentDescription = null,
        )
        Text(
            modifier = Modifier.padding(top = 5.dp),
            text = actor.nombre,
            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
        )
    }
}