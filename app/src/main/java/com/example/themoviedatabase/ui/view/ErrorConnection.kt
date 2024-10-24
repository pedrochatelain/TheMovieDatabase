package com.example.themoviedatabase.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.themoviedatabase.R

@Composable
fun ErrorConnection(onTryAgain: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(R.drawable.wifi_off_24px),
            contentDescription = stringResource(R.string.icon_wifi_off),
            modifier = Modifier.size(60.dp),
            tint = MaterialTheme.colorScheme.error
        )
        Text(
            text = stringResource(R.string.no_internet_connection),
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
            modifier = Modifier.padding(top = 10.dp))
        Button(
            onClick = onTryAgain,
            modifier = Modifier.padding(top = 30.dp)
        ){
            Text(stringResource(R.string.button_try_again_internet_connection))
        }
    }
}