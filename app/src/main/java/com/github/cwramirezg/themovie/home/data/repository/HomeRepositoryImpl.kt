package com.github.cwramirezg.themovie.home.data.repository

import android.net.ConnectivityManager
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.github.cwramirezg.themovie.home.data.local.HomeDao
import com.github.cwramirezg.themovie.home.data.mapper.toDomain
import com.github.cwramirezg.themovie.home.data.mapper.toLocal
import com.github.cwramirezg.themovie.home.data.remote.HomeApi
import com.github.cwramirezg.themovie.home.data.remote.VideosPagingSource
import com.github.cwramirezg.themovie.home.domain.model.Video
import com.github.cwramirezg.themovie.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber

class HomeRepositoryImpl(
    private val dao: HomeDao,
    private val api: HomeApi,
    private val connectivityManager: ConnectivityManager
) : HomeRepository {
    override fun getAllVideos(): Flow<List<Video>> {
        return dao.getAllVideos().map { videos -> videos.map { it.toDomain() } }
    }

    override suspend fun getVideos(): List<Video> {
        return api.getVideos(1).toDomain()
    }

    override suspend fun insertVideos(videos: List<Video>) {
        dao.insertVideo(videos.map { it.toLocal() })
    }

    override suspend fun getVideoById(id: String): Video {
        return dao.getVideoById(id).toDomain()
    }

    override suspend fun countVideos(): Int {
        return dao.countVideos()
    }

    override fun getVideosByPage(): Flow<PagingData<Video>> {
        Timber.d("getVideosByPage")
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { VideosPagingSource(dao, api, isNetworkAvailable()) }
        ).flow
    }

    @Suppress("DEPRECATION")
    override fun isNetworkAvailable(): Boolean {
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}