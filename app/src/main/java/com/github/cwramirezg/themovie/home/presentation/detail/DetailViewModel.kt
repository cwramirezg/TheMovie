package com.github.cwramirezg.themovie.home.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.github.cwramirezg.themovie.core.di.IoDispatcher
import com.github.cwramirezg.themovie.home.domain.detail.usecase.DetailUseCases
import com.github.cwramirezg.themovie.navigation.Detail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val detailUseCases: DetailUseCases,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _state = MutableStateFlow(DetailState())
    val state = _state.asStateFlow()

    init {
        val detail = savedStateHandle.toRoute<Detail>()
        if (detail.id.isNotEmpty()) {
            viewModelScope.launch(dispatcher) {
                val video = detailUseCases.getVideoByIdUseCase(detail.id)
                _state.value = _state.value.copy(video = video)
            }
        }
    }
}