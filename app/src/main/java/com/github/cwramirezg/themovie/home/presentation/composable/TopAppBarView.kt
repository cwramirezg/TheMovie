package com.github.cwramirezg.misrecetas.home.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarView(
    textTitle: String,
    onClickVideo: () -> Unit,
    imageVectorVideo: ImageVector
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = textTitle,
                color = Color.White
            )
        },
        navigationIcon = {
            IconButton(onClick = { onClickVideo() }) {
                Icon(
                    imageVector = imageVectorVideo,
                    contentDescription = "Icon navigation",
                    tint = Color.White
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    )
}

@Preview(showBackground = true)
@Composable
fun TopAppBarViewPreview() {
    TopAppBarView(
        textTitle = "Mis Videos",
        onClickVideo = {},
        imageVectorVideo = Icons.Default.Home
    )
}