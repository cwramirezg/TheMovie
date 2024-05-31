package com.github.cwramirezg.themovie.home.presentation.detail

import com.github.cwramirezg.themovie.home.domain.model.Video

data class DetailState(
    val video: Video = Video()
)