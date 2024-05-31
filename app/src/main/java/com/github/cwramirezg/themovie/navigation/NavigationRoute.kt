package com.github.cwramirezg.themovie.navigation

sealed class NavigationRoute(
    val route: String
) {
    object Login : NavigationRoute("login")
    object Video : NavigationRoute("video")
    object Detail : NavigationRoute("detail")
}