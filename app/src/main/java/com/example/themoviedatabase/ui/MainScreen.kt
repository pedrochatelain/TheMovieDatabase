package com.example.themoviedatabase.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.themoviedatabase.R

@Composable
fun MainScreen(mainViewModel: MainViewModel) {
    Scaffold(topBar = {TopBar()}) {

        Column(
            modifier = Modifier.fillMaxSize().padding(it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box {
                if (mainViewModel.isLoading()) {
                    CircularProgressIndicator()
                } else {
                    LazyRow(modifier = Modifier.padding(all = 10.dp)) {
                        items(items = mainViewModel.movies) { movie ->
                            MyCard(movie.originalTitle, movie.originalLanguage, movie.image)
                        }
                    }
                }
            }
            Spacer(Modifier.height(height = 5.dp))
            Button(onClick = {
                mainViewModel.getMovies()
            }) {
                Text("Get movies!")
            }
        }

    }

}

@Preview
@Composable
private fun TopBar() {
    Row(modifier = Modifier.background(color = MaterialTheme.colorScheme.primary).fillMaxWidth().padding(14.dp)) {
        Text(
            color = MaterialTheme.colorScheme.onPrimary,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            text = "My movies")
    }
}

@Composable
fun MyCard(title: String, language: String, image: String) {
    ElevatedCard(Modifier.padding(10.dp)) {
        Column(modifier = Modifier.height(370.dp).width(248.dp)) {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                placeholder = painterResource(R.drawable.placeholder),
                model = "https://image.tmdb.org/t/p/w500/${image}",
                contentDescription = null,
            )
        }
        Text(
            modifier = Modifier.padding(start = 10.dp, top = 10.dp),
            fontSize = 16.sp,
            text = title,
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = Modifier.padding(start = 10.dp, bottom = 10.dp),
            fontSize = 14.sp,
            fontWeight = FontWeight.Light,
            text = "original language: $language"
        )
    }
}