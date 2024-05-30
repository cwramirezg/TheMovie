package com.github.cwramirezg.themovie.home.domain.video.usecase

import com.github.cwramirezg.themovie.core.util.resultOf
import com.github.cwramirezg.themovie.home.domain.repository.HomeRepository

class RequestVideosUseCase(
    private val repository: HomeRepository
) {
    suspend operator fun invoke() = resultOf {
        val isEmpty = repository.countVideos() == 0
        if (isEmpty) {
            repository.insertVideos(repository.getVideos())
        }
    }
}