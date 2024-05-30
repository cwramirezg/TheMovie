package com.github.cwramirezg.themovie.home.presentation.video

sealed interface VideoEvent {
    data class OnClickVideo(val id: Int) : VideoEvent
}