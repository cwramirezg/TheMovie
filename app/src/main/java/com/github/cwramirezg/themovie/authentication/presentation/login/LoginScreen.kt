package com.github.cwramirezg.themovie.authentication.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.cwramirezg.themovie.R
import com.github.cwramirezg.themovie.authentication.presentation.composable.PasswordOutlineTextField
import com.github.cwramirezg.themovie.authentication.presentation.composable.UsernameOutlineTextField
import com.github.cwramirezg.themovie.ui.theme.TheMovieTheme

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    onLoginSuccess: () -> Unit
) {
    TheMovieTheme {
        Surface(
            modifier = Modifier.fillMaxWidth()
        ) {
            val state by viewModel.state.collectAsState()
            Login(
                state = state,
                onEvent = { viewModel.onEvent(it) },
                onLoginSuccess = onLoginSuccess
            )
        }
    }

}

@Composable
fun Login(state: LoginState, onEvent: (LoginEvent) -> Unit, onLoginSuccess: () -> Unit) {
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
            Spacer(modifier = Modifier.padding(32.dp))
            Image(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "The Movie Logo"
            )
            Text(
                text = "Login",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            UsernameOutlineTextField(
                value = state.username,
                onValueChange = { onEvent(LoginEvent.updateUsername(it)) },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            PasswordOutlineTextField(
                value = state.password,
                onValueChange = { onEvent(LoginEvent.updatePassword(it)) },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Button(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                enabled = state.username.isNotEmpty() && state.password.isNotEmpty(),
                onClick = {
                    onEvent(LoginEvent.onLogin)
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

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    Login(LoginState(), {}, {})
}