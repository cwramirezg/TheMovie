package com.github.cwramirezg.themovie.authentication.data.repository

import com.github.cwramirezg.themovie.authentication.domain.repository.LoginRepository

class LoginRepositoryImpl(

) : LoginRepository {
    override fun login(username: String, password: String): Boolean {
        return username == "Admin" && password == "Password*123."
    }
}