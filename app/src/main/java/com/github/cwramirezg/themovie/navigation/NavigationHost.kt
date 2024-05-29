package com.github.cwramirezg.themovie.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.github.cwramirezg.themovie.authentication.presentation.login.LoginScreen
import com.github.cwramirezg.themovie.home.presentation.home.HomeScreen

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
                    navHostController.navigate(NavigationRoute.Home.route)
                }
            )
        }
        composable(route = NavigationRoute.Home.route) {
            HomeScreen()
        }
    }
}