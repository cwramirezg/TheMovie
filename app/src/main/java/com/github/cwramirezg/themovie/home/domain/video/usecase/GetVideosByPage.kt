package com.github.cwramirezg.themovie.home.domain.video.usecase

import com.github.cwramirezg.themovie.home.domain.repository.HomeRepository

class GetVideosByPage(
    private val repository: HomeRepository
) {
    operator fun invoke() = repository.getVideosByPage()
}