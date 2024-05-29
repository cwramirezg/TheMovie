package com.github.cwramirezg.themovie.authentication.domain.repository

interface LoginRepository {
    fun login(username: String, password: String): Boolean
}