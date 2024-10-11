package com.example.themoviedatabase.ui.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Clear
import androidx.compose.material.icons.sharp.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.themoviedatabase.ui.keyboardAsState
import com.example.themoviedatabase.ui.viewmodel.MainViewModel

@Composable
fun BuscadorPeliculas(viewModel: MainViewModel = hiltViewModel()) {
    if (viewModel.isInitialized) {
        val focusManager = LocalFocusManager.current
        val isKeyboardOpen = keyboardAsState().value

        if (! isKeyboardOpen) {
            focusManager.clearFocus()
        }
        OutlinedTextField(keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            shape = MaterialTheme.shapes.extraLarge,
            modifier = Modifier
                .padding(bottom = 20.dp, start = 10.dp, end = 10.dp)
                .fillMaxWidth(),
            value = viewModel.searchedMovie,
            placeholder = { Text("Search movie") },
            onValueChange = {
                viewModel.cancelSearch()
                viewModel.searchedMovie = it
                viewModel.loadMovies()
            },
            leadingIcon = { Icon(Icons.Sharp.Search, "s") },
            trailingIcon = { ButtonClearText() }
        )
    }
}

@Composable
private fun ButtonClearText(viewModel: MainViewModel = hiltViewModel()) {
    if (viewModel.searchedMovie.isNotBlank()) {
        IconButton(
            modifier = Modifier.padding(end = 10.dp),
            content = { Icon(imageVector = Icons.Sharp.Clear, "") },
            onClick = {
                viewModel.searchedMovie = ""
                viewModel.loadMovies()
            }
        )
    }
}