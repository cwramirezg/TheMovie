package com.github.cwramirezg.themovie.home.presentation.video

import com.github.cwramirezg.themovie.home.domain.model.Video

data class VideoState (
    val loading: Boolean = false,
    val videos: List<Video> = emptyList(),
    val error: String? = null
)