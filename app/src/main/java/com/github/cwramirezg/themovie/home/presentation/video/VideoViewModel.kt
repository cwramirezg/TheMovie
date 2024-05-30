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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class VideoViewModel @Inject constructor(
    private val videoUseCases: VideoUseCases,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _state = MutableStateFlow(VideoState())
    val state = _state.asStateFlow()

    init {
        //  getVideos()
    }

    private fun getVideos() {
        viewModelScope.launch(dispatcher) {
            _state.value = VideoState(loading = true)
            videoUseCases.requestVideos()
                .onSuccess {
                    videoUseCases.getVideos().collect {
                        _state.value = VideoState(videos = it)
                    }
                }
                .onFailure {
                    Timber.d("Error: ${it.message}")
                    _state.value = VideoState(error = it.message)
                }
        }
    }

    fun getVideo(): Flow<PagingData<Video>> =
        videoUseCases.getVideosByPage()
            .cachedIn(viewModelScope)

}
