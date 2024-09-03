package com.github.cwramirezg.themovie.home.presentation.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.github.cwramirezg.misrecetas.home.ui.components.TopAppBarView
import com.github.cwramirezg.themovie.R
import com.github.cwramirezg.themovie.home.domain.model.Video
import com.github.cwramirezg.themovie.home.domain.model.url500
import com.github.cwramirezg.themovie.ui.theme.TheMovieTheme
import timber.log.Timber

@Composable
fun DetailScreen(
    viewModel: DetailViewModel = hiltViewModel(),
    onClickBack: () -> Unit
) {
    TheMovieTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            val state by viewModel.state.collectAsState()
            Scaffold(
                topBar = {
                    TopAppBarView(
                        textTitle = state.video.nombre,
                        onClickHome = { onClickBack() },
                        imageVectorHome = Icons.AutoMirrored.Filled.ArrowBack
                    )
                }
            ) { padding ->
                if (state.video.id.isNotEmpty()) {
                    MyVideo(
                        video = state.video,
                        modifier = Modifier.padding(padding)
                    )
                }
            }
        }
    }
}

@Composable
fun MyVideo(
    video: Video,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState())
    ) {
        Timber.d("${video.poster.url500()}")
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(video.poster.url500())
                .diskCachePolicy(CachePolicy.ENABLED)
                .build(),
            error = painterResource(id = R.drawable.broken_image),
            contentDescription = video.nombre,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        Text(
            text = video.nombre,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier
                .fillMaxWidth()
        )
        Text(
            text = "(${video.fechaLanzamiento} - ${video.nota})",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Light,
            fontSize = 10.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )
        Text(
            text = video.resumen,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
    }
}