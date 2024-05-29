package com.github.cwramirezg.themovie.home.presentation.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import timber.log.Timber

@Composable
fun HomeScreen(

) {
    Timber.d("HomeScreen")
    Scaffold {
        Text(
            modifier = Modifier.padding(it),
            text = "Home"
        )
    }
}