package com.github.cwramirezg.themovie.authentication.presentation.login

sealed interface LoginEvent {
    data class updateUsername(val username: String) : LoginEvent
    data class updatePassword(val password: String) : LoginEvent
    data object onLogin : LoginEvent
}