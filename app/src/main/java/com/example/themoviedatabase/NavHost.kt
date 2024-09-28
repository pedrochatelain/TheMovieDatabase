package com.example.themoviedatabase

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.themoviedatabase.ui.MoviesScreen
import kotlinx.serialization.Serializable

@Composable
fun MyAppNavHost(
    navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = MoviesRoute
    ) {
        composable<MoviesRoute> {
            MoviesScreen(onMovieClick = { movieID -> navController.navigate(MovieDetailRoute(movieID)) })
        }
        composable<MovieDetailRoute> { entry ->
            val movie = entry.toRoute<MovieDetailRoute>()
            Text(text = movie.title)
        }
    }
}


@Serializable
object MoviesRoute

@Serializable
data class MovieDetailRoute(val title: String)