package com.example.themoviedatabase.ui.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Search
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.themoviedatabase.R
import com.example.themoviedatabase.ui.keyboardAsState
import com.example.themoviedatabase.ui.viewmodel.MainViewModel

@Composable
fun MoviesScreen(viewModel: MainViewModel = hiltViewModel(), onMovieClick: (movieID: Int) -> Unit) {
    if (! viewModel.moviesLoaded) {
        viewModel.loadMovies()
    }
    Scaffold(topBar = { TopBar() }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when {
                viewModel.loading -> CircularProgressIndicator()
                viewModel.moviesLoaded -> {
                    Column(
                      verticalArrangement = Arrangement.Top,
                      modifier = Modifier.fillMaxHeight()
                    ) {
                        BuscadorPeliculas(viewModel)
                        ListOfMovies(viewModel, onMovieClick)
                    }
                }
                viewModel.error -> ErrorScreen(viewModel)
            }
        }
    }
}

@Composable
private fun ErrorScreen(viewModel: MainViewModel) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            painter = painterResource(R.drawable.wifi_off_24px),
            contentDescription = "",
            modifier = Modifier.size(60.dp),
            tint = MaterialTheme.colorScheme.error
        )
        Text("No internet connection", fontSize = 20.sp, modifier = Modifier.padding(top = 10.dp))
        Button(
            onClick = { viewModel.loadMovies() },
            modifier = Modifier.padding(top = 50.dp)
        ) {
            Text("Try again")
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ListOfMovies(
    viewModel: MainViewModel,
    onMovieClick: (movieID: Int) -> Unit
) {
    if ( ! viewModel.isLoadingSearch) {
        CompositionLocalProvider(
            LocalOverscrollConfiguration provides null
        ) {
            LazyVerticalGrid(
                modifier = Modifier.padding(5.dp), columns = GridCells.Adaptive(minSize = 128.dp)
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
                        modifier = Modifier
                            .padding(10.dp)
                    ) {
                        if (viewModel.loadingMoreMovies) {
                            Row(modifier = Modifier.padding(bottom = 50.dp)) {
                                CircularProgressIndicator()
                            }
                        }
                        if (viewModel.errorLoadMoreMovies) {
                            ErrorLoadingMoreMovies(viewModel)
                        }
                    }
                }
            }
        }
    } else {
        SearchingScreen()
    }

}

@Composable
private fun BuscadorPeliculas(viewModel: MainViewModel) {
    val focusManager = LocalFocusManager.current
    val isKeyboardOpen = keyboardAsState().value

    if (! isKeyboardOpen) {
        focusManager.clearFocus()
    }
    OutlinedTextField(keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
        shape = MaterialTheme.shapes.extraLarge,
        modifier = Modifier
            .padding(top = 20.dp, bottom = 10.dp, start = 10.dp, end = 10.dp)
            .fillMaxWidth(),
        value = viewModel.searchedMovie,
        placeholder = { Text("Search movie") },
        onValueChange = {viewModel.searchMovie(it) },
        leadingIcon = { Icon(Icons.Sharp.Search, "s") })
}

@Composable
fun SearchingScreen() {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Buscando...")
        CircularProgressIndicator()
    }
}

@Composable
private fun ErrorLoadingMoreMovies(viewModel: MainViewModel) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(R.drawable.wifi_off_24px),
                contentDescription = "",
                modifier = Modifier
                    .size(35.dp)
                    .padding(end = 10.dp),
                tint = MaterialTheme.colorScheme.error
            )
            Text("No internet connection", fontSize = 18.sp)
        }
        Button(onClick = { viewModel.loadMoreMovies() }, modifier = Modifier.padding(5.dp)) {
            Text("Try again")
        }
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