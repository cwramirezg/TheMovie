package com.github.cwramirezg.themovie.home.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.cwramirezg.themovie.core.di.IoDispatcher
import com.github.cwramirezg.themovie.home.domain.detail.usecase.DetailUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val detailUseCases: DetailUseCases,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _state = MutableStateFlow(DetailState())
    val state = _state.asStateFlow()

    init {
        val id = savedStateHandle.get<String>("id") ?: ""
        if (id.isNotEmpty()) {
            viewModelScope.launch(dispatcher) {
                val video = detailUseCases.getVideoByIdUseCase(id)
                _state.value = _state.value.copy(video = video)
            }
        }
    }
}