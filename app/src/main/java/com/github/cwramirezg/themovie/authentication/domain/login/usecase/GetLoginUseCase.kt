package com.github.cwramirezg.themovie.authentication.domain.login.usecase

import com.github.cwramirezg.themovie.authentication.domain.repository.LoginRepository

class GetLoginUseCase(
    private val repository: LoginRepository
) {
    suspend operator fun invoke(username: String, password: String): Boolean =
        repository.login(username, password)
}