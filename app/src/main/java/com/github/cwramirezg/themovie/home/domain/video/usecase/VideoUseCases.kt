package com.github.cwramirezg.themovie.home.domain.video.usecase

data class VideoUseCases(
    val requestVideos: RequestVideosUseCase,
    val getVideos: GetVideosUseCase,
)