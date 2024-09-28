package com.example.themoviedatabase.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.themoviedatabase.R
import com.example.themoviedatabase.data.dto.Movie

@Composable
fun MainScreen(viewModel: MainViewModel = hiltViewModel(), onMovieClick: (movieID: String) -> Unit) {
    Scaffold(topBar = {TopBar()}) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box {
                if (viewModel.isLoading()) {
                    CircularProgressIndicator()
                } else {
                    LazyRow(modifier = Modifier.padding(all = 10.dp)) {
                        items(items = viewModel.movies) { movie ->
                            MyCard(movie, onMovieClick)
                        }
                    }
                }
            }
            Spacer(Modifier.height(height = 5.dp))
            Button(onClick = {
                viewModel.getMovies()
            }) {
                Text("Get movies!")
            }
        }

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCard(movie: Movie, onMovieClick: (movieID: String) -> Unit) {
    ElevatedCard(onClick = { onMovieClick(movie.originalTitle) }, modifier = Modifier.padding(10.dp)) {
//    ElevatedCard(modifier = Modifier.padding(10.dp)) {
        Column(modifier = Modifier
            .height(370.dp)
            .width(248.dp)) {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                placeholder = painterResource(R.drawable.placeholder),
                model = "https://image.tmdb.org/t/p/w500/${movie.image}",
                contentDescription = null,
            )
        }
        Text(
            modifier = Modifier.padding(start = 10.dp, top = 10.dp),
            fontSize = 16.sp,
            text = movie.originalTitle,
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = Modifier.padding(start = 10.dp, bottom = 10.dp),
            fontSize = 14.sp,
            fontWeight = FontWeight.Light,
            text = "original language: ${movie.originalLanguage}"
        )
    }
}

@Preview
@Composable
private fun TopBar() {
    Row(modifier = Modifier
        .background(color = MaterialTheme.colorScheme.primary)
        .fillMaxWidth()
        .padding(14.dp)) {
        Text(
            color = MaterialTheme.colorScheme.onPrimary,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            text = "My movies")
    }
}