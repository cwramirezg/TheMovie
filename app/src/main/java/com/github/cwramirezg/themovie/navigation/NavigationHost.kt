package com.github.cwramirezg.themovie.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.github.cwramirezg.themovie.authentication.presentation.login.LoginScreen
import com.github.cwramirezg.themovie.home.presentation.detail.DetailScreen
import com.github.cwramirezg.themovie.home.presentation.video.VideoScreen

@Composable
fun NavigationHost(
    navHostController: NavHostController,
    startDestination: Any
) {
    NavHost(
        navController = navHostController,
        startDestination = startDestination
    ) {
        composable<Login> {
            LoginScreen(
                onLoginSuccess = {
                    navHostController.navigate(Video)
                }
            )
        }
        composable<Video> {
            VideoScreen(
                onclickVideo = { id ->
                    navHostController.navigate(Detail(id))
                }
            )
        }
        composable<Detail> {
            DetailScreen(
                onClickBack = {
                    navHostController.popBackStack()
                }
            )
        }
    }
}
