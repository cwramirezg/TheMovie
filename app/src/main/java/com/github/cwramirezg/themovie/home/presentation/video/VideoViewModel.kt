package com.github.cwramirezg.themovie.home.presentation.video

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.github.cwramirezg.themovie.core.di.IoDispatcher
import com.github.cwramirezg.themovie.home.domain.model.Video
import com.github.cwramirezg.themovie.home.domain.video.usecase.VideoUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class VideoViewModel @Inject constructor(
    private val videoUseCases: VideoUseCases,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    val videos = videoUseCases
        .getVideosByPage()
        .flowOn(dispatcher)
        .cachedIn(viewModelScope)

    init {
        Timber.d("VideoViewModel created!")
    }

}
