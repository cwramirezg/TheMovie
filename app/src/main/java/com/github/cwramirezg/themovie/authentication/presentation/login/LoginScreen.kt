package com.github.cwramirezg.themovie.authentication.presentation.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.cwramirezg.themovie.authentication.presentation.composable.PasswordOutlineTextField
import com.github.cwramirezg.themovie.authentication.presentation.composable.UsernameOutlineTextField

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    onLoginSuccess: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(state.onLoginSuccess) {
        if (state.onLoginSuccess) {
            onLoginSuccess()
        }
    }

    Scaffold {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Login",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            UsernameOutlineTextField(
                value = state.username,
                onValueChange = { viewModel.onEvent(LoginEvent.updateUsername(it)) },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            PasswordOutlineTextField(
                value = state.password,
                onValueChange = { viewModel.onEvent(LoginEvent.updatePassword(it)) },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Button(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                enabled = state.username.isNotEmpty() && state.password.isNotEmpty(),
                onClick = {
                    viewModel.onEvent(LoginEvent.onLogin)
                }
            ) {
                Text("Login")
            }
            if (state.error.isNotEmpty()) {
                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = state.error,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
    }

}