package com.example.themoviedatabase.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.themoviedatabase.R

@Composable
fun MainScreen(mainViewModel: MainViewModel) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box {
            if (mainViewModel.isLoading()) {
                CircularProgressIndicator()
            } else {
                LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
                    items(items = mainViewModel.movies) { movie ->
                        MyCard(movie.originalTitle, movie.originalLanguage)
                        Spacer(modifier = Modifier.height(Dp(40F)))
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

//@Preview
@Composable
fun MyCard(title: String, language: String) {
//fun MyCard() {
    ElevatedCard {
        Image(modifier = Modifier.width(300.dp).height(200.dp), painter = painterResource(R.drawable.emptymovie), contentDescription = "jaja")
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