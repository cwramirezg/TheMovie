package com.github.cwramirezg.themovie.authentication.di

import com.github.cwramirezg.themovie.authentication.data.repository.LoginRepositoryImpl
import com.github.cwramirezg.themovie.authentication.domain.login.usecase.GetLoginUseCase
import com.github.cwramirezg.themovie.authentication.domain.login.usecase.LoginUseCases
import com.github.cwramirezg.themovie.authentication.domain.repository.LoginRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LoginModule {

    @Singleton
    @Provides
    fun provideGetLoginUseCase(repository: LoginRepository): GetLoginUseCase {
        return GetLoginUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideLoginUseCases(getLoginUseCase: GetLoginUseCase): LoginUseCases {
        return LoginUseCases(getLoginUseCase)
    }

    @Singleton
    @Provides
    fun provideLoginRepository(): LoginRepository {
        return LoginRepositoryImpl()
    }

}