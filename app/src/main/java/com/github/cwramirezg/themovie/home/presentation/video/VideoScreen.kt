package com.github.cwramirezg.themovie.home.presentation.video

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.github.cwramirezg.misrecetas.home.ui.components.SearchView
import com.github.cwramirezg.misrecetas.home.ui.components.TopAppBarView
import com.github.cwramirezg.themovie.R
import com.github.cwramirezg.themovie.home.domain.model.Video
import com.github.cwramirezg.themovie.home.domain.model.url

@Composable
fun VideoScreen(
    viewModel: VideoViewModel = hiltViewModel(),
    onclickVideo: (String) -> Unit
) {
    val state by viewModel.state.collectAsState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            topBar = {
                TopAppBarView(
                    textTitle = "Videos",
                    onClickVideo = { },
                    imageVectorVideo = Icons.Default.Home
                )
            }
        ) { padding ->
            if (state.error != null) {
                Box(
                    modifier = Modifier
                        .padding(padding)
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = state.error!!,
                        textAlign = TextAlign.Center
                    )
                }
            }
            if (state.loading) {
                Box(
                    modifier = Modifier
                        .padding(padding)
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            if (state.videos.isNotEmpty()) {
                val textState = remember { mutableStateOf(TextFieldValue("")) }
                Column(modifier = Modifier.padding(padding)) {
                    SearchView(state = textState)
                    val searchedText = textState.value.text
                    val filter = if (searchedText.isEmpty()) {
                        state.videos
                    } else {
                        state.videos.filter {
                            it.nombre.contains(searchedText, ignoreCase = true)
                        }
                    }
                    if (filter.isNotEmpty()) {
                        LazyVerticalGrid(
                            columns = GridCells.Adaptive(180.dp),
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                            verticalArrangement = Arrangement.spacedBy(4.dp),
                            contentPadding = PaddingValues(4.dp)
                        ) {
                            items(filter) { video ->
                                VideoItem(
                                    video = video,
                                    onClick = {
                                        onclickVideo(it)
                                    }
                                )
                            }
                        }
                    } else {
                        Text(
                            text = "No se encontraron videos",
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun VideoItem(video: Video, onClick: (String) -> Unit) {
    Column(
        modifier = Modifier.clickable {
            onClick(video.id)
        }
    ) {
        Box {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(video.poster.url())
                    .diskCachePolicy(CachePolicy.ENABLED)
                    .build(),
                error = painterResource(id = R.drawable.broken_image),
                contentDescription = video.nombre,
                modifier = Modifier
                    .fillMaxSize()
                    .aspectRatio(1.0f),
            )
        }
        Text(
            text = video.nombre,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
    }
}
