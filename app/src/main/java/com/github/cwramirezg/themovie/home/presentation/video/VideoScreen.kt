package com.github.cwramirezg.themovie.home.presentation.video

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.github.cwramirezg.misrecetas.home.ui.components.TopAppBarView
import com.github.cwramirezg.themovie.R
import com.github.cwramirezg.themovie.home.domain.model.Video
import com.github.cwramirezg.themovie.home.domain.model.url200
import com.github.cwramirezg.themovie.ui.theme.TheMovieTheme
import timber.log.Timber

@Composable
fun VideoScreen(
    viewModel: VideoViewModel = hiltViewModel(),
    onclickVideo: (String) -> Unit
) {
    Timber.d("Videosscreen")

    val videos = viewModel.videos.collectAsLazyPagingItems()

    TheMovieTheme {
        Surface(
            modifier = Modifier.fillMaxWidth()
        ) {
            Timber.d("TheMovieTheme")
            Scaffold(
                topBar = {
                    TopAppBarView(
                        textTitle = "Videos",
                        onClickHome = { },
                        imageVectorHome = Icons.Default.Home
                    )
                }
            ) { padding ->
                Timber.d("Videos: ${videos.itemCount}")
                LazyColumn(
                    modifier = Modifier.padding(padding)
                ) {
                    items(videos.itemCount) { index ->
                        videos[index]?.let { quote ->
                            VideoItem(video = quote) {
                                onclickVideo(it)
                            }
                        }
                    }
                    when (val state = videos.loadState.refresh) {
                        is LoadState.Error -> {
                            Timber.d("Error refresh")
                            item {
                                Text(text = state.error.message.toString())
                            }
                        }

                        LoadState.Loading -> {
                            item {
                                Column(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center,
                                ) {
                                    Text(
                                        modifier = Modifier
                                            .padding(8.dp),
                                        text = "Refresh Loading"
                                    )
                                    CircularProgressIndicator()
                                }
                            }
                        }

                        is LoadState.NotLoading -> {}
                    }
                    when (val state = videos.loadState.append) {
                        is LoadState.Error -> {
                            Timber.d("Error append")
                            item {
                                Text(text = state.error.message.toString())
                            }
                        }

                        LoadState.Loading -> {
                            item {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center,
                                ) {
                                    Text(text = "Pagination Loading")

                                    CircularProgressIndicator()
                                }
                            }
                        }

                        is LoadState.NotLoading -> {}
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
                    .data(video.poster.url200())
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
