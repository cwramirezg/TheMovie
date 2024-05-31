package com.github.cwramirezg.themovie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.github.cwramirezg.themovie.navigation.Login
import com.github.cwramirezg.themovie.navigation.NavigationHost
import com.github.cwramirezg.themovie.ui.theme.TheMovieTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TheMovieTheme {
                val navController = rememberNavController()
                NavigationHost(
                    navHostController = navController,
                    startDestination = getStartDestination()
                )
            }
        }
    }

    private fun getStartDestination(): Any {
        return Login
    }
}
