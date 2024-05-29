package com.github.cwramirezg.themovie.authentication.presentation.composable

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.github.cwramirezg.themovie.R
import java.util.regex.Pattern

@Composable
fun PasswordOutlineTextField(
    value: String,
    onValueChange: (text: String) -> Unit,
    modifier: Modifier = Modifier
) {
    val showPassword = rememberSaveable { mutableStateOf(false) }
    val passwordPattern =
        Pattern.compile("^[a-zA-Z0-9.*]+\$")

    OutlinedTextField(
        value = value,
        onValueChange = {
            if (passwordPattern.matcher(it).matches()) {
                onValueChange(it)
            }
        },
        label = { Text(text = "Password") },
        modifier = modifier,
        isError = !passwordPattern.matcher(value).matches(),
        maxLines = 1,
        singleLine = true,
        visualTransformation = if (showPassword.value) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            val (icon, iconColor) = if (showPassword.value) {
                Pair(
                    painterResource(id = R.drawable.baseline_visibility_24),
                    colorResource(id = R.color.black)
                )
            } else {
                Pair(
                    painterResource(id = R.drawable.baseline_visibility_off_24),
                    colorResource(id = R.color.black)
                )
            }

            IconButton(onClick = { showPassword.value = !showPassword.value }) {
                Icon(
                    icon,
                    contentDescription = "Visibility",
                    tint = iconColor
                )
            }
        }
    )
}

@Preview
@Composable
fun PasswordOutlineTextFieldPreview() {
    PasswordOutlineTextField(
        value = "as",
        onValueChange = {}
    )
}