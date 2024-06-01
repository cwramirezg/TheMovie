package com.github.cwramirezg.themovie.authentication.presentation.login

data class LoginState(
    val username: String = "Admin",
    val password: String = "Password*123.",
    val onLoginSuccess: Boolean = false,
    val isLoading: Boolean = false,
    val error: String = ""
)