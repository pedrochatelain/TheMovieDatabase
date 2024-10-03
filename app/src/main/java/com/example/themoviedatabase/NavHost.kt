package com.example.themoviedatabase

import com.example.themoviedatabase.ui.view.DetailsMovieScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import androidx.navigation.toRoute
import com.example.themoviedatabase.ui.view.MoviesScreen
import kotlinx.serialization.Serializable

@Composable
fun MyAppNavHost(
    navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = MoviesRoute
    ) {
        composable<MoviesRoute> {
            MoviesScreen(onMovieClick = { movieID ->
                navController.navigate(MovieDetailRoute(movieID),
                    navOptions = navOptions {
                        launchSingleTop = true
                    }
                )
            })
        }
        composable<MovieDetailRoute> { entry ->
            val movie = entry.toRoute<MovieDetailRoute>()
            DetailsMovieScreen(movie.id)
        }
    }
}

@Serializable
object MoviesRoute

@Serializable
data class MovieDetailRoute(val id: Int)