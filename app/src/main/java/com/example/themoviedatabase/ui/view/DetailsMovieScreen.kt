package com.example.themoviedatabase.ui.view

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
import androidx.compose.material.icons.sharp.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.themoviedatabase.R
import com.example.themoviedatabase.data.dto.Actor
import com.example.themoviedatabase.data.dto.DetailsMovie
import com.example.themoviedatabase.ui.viewmodel.DetailsMovieViewModel

@Composable
fun DetailsMovieScreen(idMovie: Int, viewModel: DetailsMovieViewModel = hiltViewModel()) {
    if (! viewModel.startedLoadingMovie) {
        viewModel.loadMovie(idMovie)
    }
    if (viewModel.isDisplayingDetails) {
        val movie = viewModel.details!!
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            PictureMovie(viewModel)
            Column {
                Text(
                    text = movie.titulo,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 20.dp, top = 20.dp, bottom = 10.dp)
                )
                Rating(movie)
                Text(
                    text = movie.resumen,
                    modifier = Modifier.padding(top = 20.dp, start = 20.dp, end = 20.dp, bottom = 10.dp),
                    fontSize = 18.sp
                )
                Genres(movie)
                Text(
                    text = "Cast",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(top = 40.dp, bottom = 10.dp)
                        .padding(start = 20.dp)
                )
                Cast(movie)
            }
        }
    } else {
        Loading()
    }
}

@Composable
private fun Cast(movie: DetailsMovie) {
    LazyRow(modifier = Modifier.padding(start = 20.dp, bottom = 20.dp)) {
        items(items = movie.actores) { actor ->
            ActorCard(actor)
        }
    }
}

@Composable
private fun PictureMovie(viewModel: DetailsMovieViewModel) {
    Box {
        Image(
            modifier = Modifier
                .height(250.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.FillBounds,
            bitmap = viewModel.image,
            contentDescription = ""
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomEnd)
                .height(10.dp)
                .drawWithCache {
                    onDrawWithContent {
                        drawContent()
                        drawRect(
                            Brush.verticalGradient(
                                0f to Color(0xFFFFFBFE).copy(alpha = 0F),
                                .5F to Color(0xFFFFFBFE)
                            )
                        )
                    }
                }
        ) {}
    }
}

@Composable
private fun Rating(movie: DetailsMovie) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            modifier = Modifier
                .padding(start = 18.dp)
                .size(24.dp),
            tint = Color.hsv(47f, .87f, .96f),
            imageVector = Icons.Sharp.Star,
            contentDescription = ""
        )
        Text(
            text = "${movie.rating}",
            modifier = Modifier.padding(start = 6.dp),
            fontSize = 18.sp
        )
        Text(text = " / 10", fontSize = 18.sp, fontWeight = FontWeight.Light)
    }
}

@Composable
@OptIn(ExperimentalLayoutApi::class)
private fun Genres(movie: DetailsMovie) {
    FlowRow(modifier = Modifier.padding(start = 20.dp, top = 10.dp), content = {
        for (genero in movie.generos) {
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
private fun Loading() {
    Column(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            CircularProgressIndicator()
        }
    }
}


@Composable
fun ActorCard(actor: Actor) {
    Column(modifier = Modifier
        .height(220.dp)
        .width(120.dp)
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
            modifier = Modifier
                .width(120.dp)
                .padding(top = 6.dp),
            text = actor.nombre,
            fontSize = 16.sp
        )
    }
}