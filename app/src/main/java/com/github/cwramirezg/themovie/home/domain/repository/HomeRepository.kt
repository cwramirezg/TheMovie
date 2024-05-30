package com.github.cwramirezg.themovie.home.domain.repository

import androidx.paging.PagingData
import com.github.cwramirezg.themovie.home.domain.model.Video
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    fun getAllVideos(): Flow<List<Video>>
    suspend fun getVideos(): List<Video>
    suspend fun insertVideos(videos: List<Video>)
    suspend fun getVideoById(id: String): Video
    suspend fun countVideos(): Int

    fun getVideosByPage(): Flow<PagingData<Video>>

    fun isNetworkAvailable(): Boolean
}