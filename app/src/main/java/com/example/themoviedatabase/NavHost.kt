package com.example.themoviedatabase

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.themoviedatabase.ui.MainScreen
import kotlinx.serialization.Serializable

@Composable
fun MyAppNavHost(
    navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = MainScreen
    ) {
        composable<MainScreen> {
            MainScreen(onMovieClick = { movieID -> navController.navigate(MovieDetail(movieID)) })
        }
        composable<MovieDetail> { entry ->
            val movie = entry.toRoute<MovieDetail>()
            Text(text = movie.title)
        }
    }
}


@Serializable
object MainScreen

@Serializable
data class MovieDetail(val title: String)