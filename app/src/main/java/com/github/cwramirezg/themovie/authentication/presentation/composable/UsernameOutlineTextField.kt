package com.github.cwramirezg.themovie.authentication.presentation.composable

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun UsernameOutlineTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = {
            if (!it.contains(" ")) {
                onValueChange(it)
            }
        },
        label = { Text("Username") },
        modifier = modifier,
        isError = value.isEmpty(),
        maxLines = 1,
        singleLine = true
    )
}

@Preview(showBackground = true)
@Composable
fun UsernameOutlineTextFieldPreview() {
    UsernameOutlineTextField(
        value = "",
        onValueChange = {}
    )
}