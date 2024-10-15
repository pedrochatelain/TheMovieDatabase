package com.example.themoviedatabase.ui.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Search
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.themoviedatabase.R
import com.example.themoviedatabase.ui.viewmodel.MainViewModel

@Composable
fun MoviesScreen(viewModel: MainViewModel = hiltViewModel(), onMovieClick: (movieID: Int) -> Unit) {
    // init screen
    LaunchedEffect(Unit) {
        if ( ! viewModel.isInitialized) {
            viewModel.initialize()
        }
    }
    Scaffold(
        floatingActionButton = { SearchButton() },
        topBar = { BuscadorPeliculas() },
        content = { padding ->
            Column(Modifier.padding(padding)) {
                when {
                    viewModel.loading     -> LoadingScreen()
                    viewModel.moviesReady -> ListOfMovies(onMovieClick)
                    viewModel.noResults   -> NoResultsSearch()
                    viewModel.error       -> ErrorScreen()
                }
            }
        }
    )
}

@Composable
private fun SearchButton(viewModel: MainViewModel = hiltViewModel()) {
    if (viewModel.isInitialized) {
        FloatingActionButton(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            onClick = { viewModel.focusSearchBox() },
            content = { Icon(imageVector = Icons.Sharp.Search, "") }
        )
    }
}

@Composable
private fun NoResultsSearch(viewModel: MainViewModel = hiltViewModel()) {
    Column(modifier = Modifier.fillMaxSize().padding(top = 40.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Top) {
        Image(
            painter = painterResource(R.drawable.movie_no_results),
            "",
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground),
            modifier = Modifier.size(50.dp)
        )
        Text("No results found", fontSize = MaterialTheme.typography.titleLarge .fontSize, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 10.dp))
        Button(
            modifier = Modifier.padding(top = 30.dp),
            onClick = { viewModel.backToPopularMovies() },
            content = { Text("Back to popular movies") }
        )
    }
}

@Composable
private fun LoadingScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ListOfMovies(
    onMovieClick: (movieID: Int) -> Unit,
    viewModel: MainViewModel = hiltViewModel()
) {
    CompositionLocalProvider(
        LocalOverscrollConfiguration provides null
    ) {
        LazyVerticalGrid(
            modifier = Modifier.padding(start = 5.dp, end = 5.dp),
            columns = GridCells.Adaptive(150.dp),
        ) {
            items(items = viewModel.movies) { movie ->
                CardMovie(movie, onMovieClick)
            }
            item(span = { GridItemSpan(2) }) {
                // triggers when scroll to bottom of list
                LaunchedEffect(Unit) {
                    viewModel.loadMoreMovies()
                }
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.height(150.dp)
                ) {
                    if (viewModel.errorLoadMoreMovies) {
                        ErrorLoadingMoreMovies()
                    }
                    if (viewModel.isLoadingMoreMovies && viewModel.movies.size > 0) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}

@Composable
private fun ErrorScreen(viewModel: MainViewModel = hiltViewModel()) {
    Column(
      modifier = Modifier.fillMaxSize(),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(R.drawable.wifi_off_24px),
            contentDescription = "",
            modifier = Modifier.size(60.dp),
            tint = MaterialTheme.colorScheme.error
        )
        Text("No internet connection", fontSize = 20.sp, modifier = Modifier.padding(top = 10.dp))
        Button(
            onClick = { viewModel.loadMovies() },
            modifier = Modifier.padding(top = 30.dp)
        ) {
            Text("Try again")
        }
    }
}

@Composable
private fun ErrorLoadingMoreMovies(viewModel: MainViewModel = hiltViewModel()) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            painter = painterResource(R.drawable.wifi_off_24px),
            contentDescription = "",
            modifier = Modifier.size(35.dp),
            tint = MaterialTheme.colorScheme.error
        )
        Text(
            text = "No internet connection",
            fontSize = MaterialTheme.typography.titleMedium.fontSize
        )
        Button(
            onClick = {
                viewModel.errorLoadMoreMovies = false
                viewModel.loadMoreMovies()
            },
            modifier = Modifier.padding(5.dp)
        ) {
            Text("Try again")
        }
    }
}

@Preview
@Composable
private fun TopBar() {
    Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(20.dp)
    ){
        Text(
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            text = "My movies"
        )
    }
}