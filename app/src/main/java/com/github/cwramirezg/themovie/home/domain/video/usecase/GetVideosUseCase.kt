package com.github.cwramirezg.themovie.home.domain.video.usecase

import com.github.cwramirezg.themovie.home.domain.model.Video
import com.github.cwramirezg.themovie.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow

class GetVideosUseCase(
    private val repository: HomeRepository
) {
    operator fun invoke(): Flow<List<Video>> {
        return repository.getAllVideos()
    }
}