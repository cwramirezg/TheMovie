package com.github.cwramirezg.themovie.authentication.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.cwramirezg.themovie.authentication.domain.login.usecase.LoginUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCases: LoginUseCases
) : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    fun onEvent(event: LoginEvent) {
        Timber.d("onEvent: $event")
        when (event) {
            LoginEvent.onLogin -> {
                _state.value = state.value.copy(
                    isLoading = true,
                    error = ""
                )
                viewModelScope.launch(Dispatchers.IO) {
                    val result =
                        loginUseCases.getLoginUseCase(state.value.username, state.value.password)
                    if (result) {
                        _state.value = state.value.copy(
                            onLoginSuccess = true,
                            isLoading = false,
                            error = ""
                        )
                    } else {
                        _state.value = state.value.copy(
                            onLoginSuccess = false,
                            isLoading = false,
                            error = "Login failed"
                        )
                    }
                }
            }

            is LoginEvent.updatePassword -> {
                _state.value = state.value.copy(
                    password = event.password,
                    error = ""
                )
            }

            is LoginEvent.updateUsername -> {
                _state.value = state.value.copy(
                    username = event.username,
                    error = ""
                )
            }
        }
    }
}