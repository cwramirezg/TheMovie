package com.github.cwramirezg.themovie.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.github.cwramirezg.themovie.authentication.presentation.login.LoginScreen
import com.github.cwramirezg.themovie.home.presentation.detail.DetailScreen
import com.github.cwramirezg.themovie.home.presentation.video.VideoScreen

@Composable
fun NavigationHost(
    navHostController: NavHostController,
    startDestination: NavigationRoute
) {
    NavHost(
        navController = navHostController,
        startDestination = startDestination.route
    ) {
        composable(route = NavigationRoute.Login.route) {
            LoginScreen(
                onLoginSuccess = {
                    navHostController.navigate(NavigationRoute.Video.route)
                }
            )
        }
        composable(route = NavigationRoute.Video.route) {
            VideoScreen(
                onclickVideo = { id ->
                    navHostController.navigate("${NavigationRoute.Detail.route}?id=${id}")
                }
            )
        }
        composable(
            route = "${NavigationRoute.Detail.route}?id={id}",
            arguments = listOf(
                navArgument(name = "id") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = ""
                }
            )
        ) {
            DetailScreen(
                onClickBack = {
                    navHostController.popBackStack()
                }
            )
        }
    }
}
