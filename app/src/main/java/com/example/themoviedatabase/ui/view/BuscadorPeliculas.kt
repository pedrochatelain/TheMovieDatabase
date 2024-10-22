package com.example.themoviedatabase.ui.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.themoviedatabase.R
import com.example.themoviedatabase.ui.keyboardAsState
import com.example.themoviedatabase.ui.viewmodel.MainViewModel

@Composable
fun BuscadorPeliculas(viewModel: MainViewModel = hiltViewModel()) {
    val focusManager = LocalFocusManager.current
    val isKeyboardOpen = keyboardAsState().value
    val focusRequester = remember { viewModel.focusRequester }

    if ( ! isKeyboardOpen) {
        focusManager.clearFocus()
    }

    if(viewModel.isInitialized) {
        OutlinedTextField(
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            shape = CircleShape,
            modifier = Modifier
                .height(70.dp)
                .fillMaxWidth()
                .focusRequester(focusRequester)
                .padding(10.dp),
            colors = TextFieldDefaults.colors(
                unfocusedIndicatorColor = Color.Transparent,
            ),
            value = TextFieldValue(
                text = viewModel.searchedMovie,
                selection = TextRange(viewModel.searchedMovie.length)
            ),
            placeholder = { Text(text = stringResource(R.string.placeholder_search_box), fontSize = MaterialTheme.typography.bodyMedium.fontSize) },
            onValueChange = { viewModel.search(it.text) },
            leadingIcon = { Icon(Icons.Sharp.Search, stringResource(R.string.content_description_icon_search) ) },
            trailingIcon = { ButtonClearText() }
        )
    }
}

@Composable
private fun ButtonClearText(viewModel: MainViewModel = hiltViewModel()) {
    if (viewModel.searchedMovie.isNotBlank()) {
        IconButton(
            modifier = Modifier.padding(end = 10.dp),
            content = { Icon(imageVector = Icons.Sharp.Clear, stringResource(R.string.content_description_icon_clear_searchbox)) },
            onClick = { viewModel.backToPopularMovies() }
        )
    }
}