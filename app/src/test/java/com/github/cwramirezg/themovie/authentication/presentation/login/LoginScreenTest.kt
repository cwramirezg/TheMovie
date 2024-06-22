package com.github.cwramirezg.themovie.authentication.presentation.login

import androidx.compose.material3.Text
import app.cash.paparazzi.Paparazzi
import org.junit.Rule
import org.junit.Test

class LoginScreenTest {
    @get:Rule
    val paparazzi = Paparazzi()

    @Test
    fun loginScreen() {
        paparazzi.snapshot {
            Text(text = "Login")
        }
    }
}