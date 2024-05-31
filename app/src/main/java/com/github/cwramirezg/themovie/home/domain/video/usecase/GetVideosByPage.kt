package com.github.cwramirezg.themovie.home.domain.video.usecase

import androidx.paging.PagingData
import com.github.cwramirezg.themovie.home.domain.model.Video
import com.github.cwramirezg.themovie.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow

class GetVideosByPage(
    private val repository: HomeRepository
) {
    operator fun invoke(): Flow<PagingData<Video>> {
        return repository.getVideosByPage()
    }
}