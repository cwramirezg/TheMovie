package com.github.cwramirezg.themovie.authentication.domain.repository

interface LoginRepository {
    suspend fun login(username: String, password: String): Boolean
}