package com.example.themoviedatabase

import com.example.themoviedatabase.ui.view.ScreenDetailsMovie
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.example.themoviedatabase.ui.view.ScreenMovies
import kotlinx.serialization.Serializable

@Composable
fun MyAppNavHost(
    navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = MoviesRoute
    ) {
        composable<MoviesRoute> {
            ScreenMovies(onMovieClick = { movieID ->
                navController.navigate(MovieDetailRoute(movieID),
                    navOptions = navOptions {
                        launchSingleTop = true
                    }
                )
            })
        }
        composable<MovieDetailRoute> {
            ScreenDetailsMovie()
        }
    }
}

@Serializable
object MoviesRoute

@Serializable
data class MovieDetailRoute(val id: Int)