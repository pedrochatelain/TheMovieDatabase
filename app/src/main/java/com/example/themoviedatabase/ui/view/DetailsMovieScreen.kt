package com.example.themoviedatabase.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.themoviedatabase.ui.viewmodel.DetailsMovieViewModel

@Composable
fun DetailsMovieScreen(idMovie: Int, viewModel: DetailsMovieViewModel = hiltViewModel()) {
    viewModel.getMovieDetails(idMovie)
    if (viewModel.details != null) {
        Column {
            Text(text = "Titulo", fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Text(viewModel.details!!.titulo)
            Text(text = "Resumen", fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Text(viewModel.details!!.resumen)
            Text(text = "Géneros", fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Text(viewModel.details!!.generos.toString())
        }
    } else {
        Loading()
    }

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