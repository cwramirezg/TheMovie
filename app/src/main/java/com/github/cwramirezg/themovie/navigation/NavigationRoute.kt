package com.github.cwramirezg.themovie.navigation

sealed class NavigationRoute(
    val route: String
) {
    object Login : NavigationRoute("login")
    object Home : NavigationRoute("home")
    object Profile : NavigationRoute("profile")
}