package com.example.themoviedatabase.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import com.example.themoviedatabase.ui.MainViewModel

@Composable
fun MainScreen(mainViewModel: MainViewModel) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(fontSize = TextUnit(22F, TextUnitType.Sp), text = "Taps on button: " + mainViewModel.getNumberOfTaps())
        Spacer(Modifier.height(height = Dp(10F)))
        Button(onClick = {
            mainViewModel.incrementCounter()
            mainViewModel.persist()
        }) {
            Text("Tap me!")
        }
    }
}