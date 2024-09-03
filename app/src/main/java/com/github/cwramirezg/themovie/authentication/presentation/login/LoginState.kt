package com.github.cwramirezg.themovie.authentication.presentation.login

data class LoginState(
    val username: String = "",
    val password: String = "",
    val onLoginSuccess: Boolean = false,
    val isLoading: Boolean = false,
    val error: String = ""
)