package com.github.cwramirezg.themovie.home.domain.detail.usecase

import com.github.cwramirezg.themovie.home.domain.repository.HomeRepository

class GetVideoByIdUseCase(
    private val repository: HomeRepository
) {

    suspend operator fun invoke(id: String) = repository.getVideoById(id)

}