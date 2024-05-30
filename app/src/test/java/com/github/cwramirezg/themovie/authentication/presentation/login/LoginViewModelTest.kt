package com.github.cwramirezg.themovie.authentication.presentation.login

import com.github.cwramirezg.themovie.authentication.domain.login.usecase.GetLoginUseCase
import com.github.cwramirezg.themovie.authentication.domain.login.usecase.LoginUseCases
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verifyBlocking

class LoginViewModelTest {

    private lateinit var loginViewModel: LoginViewModel

    private val dispatcher = StandardTestDispatcher()
    private val scope = TestScope(dispatcher)

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Si el user y pass son correctos, se debe navegar a la pantalla principal`() =
        scope.runTest {
            val getLoginUseCase = mock<GetLoginUseCase> {
                onBlocking { invoke("Admin", "Password") } doReturn true
            }
            val loginUseCases = LoginUseCases(getLoginUseCase)
            loginViewModel = LoginViewModel(loginUseCases, dispatcher)
            loginViewModel.onEvent(LoginEvent.updateUsername(username = "Admin"))
            loginViewModel.onEvent(LoginEvent.updatePassword(password = "Password"))
            loginViewModel.onEvent(LoginEvent.onLogin)
            advanceUntilIdle()
            verifyBlocking(getLoginUseCase) { invoke("Admin", "Password") }
            val state = loginViewModel.state.value
            assertEquals(true, state.onLoginSuccess)
        }
}