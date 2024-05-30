package com.github.cwramirezg.themovie.home.data.repository

import com.github.cwramirezg.themovie.home.data.local.HomeDao
import com.github.cwramirezg.themovie.home.data.mapper.toDomain
import com.github.cwramirezg.themovie.home.data.mapper.toLocal
import com.github.cwramirezg.themovie.home.data.remote.HomeApi
import com.github.cwramirezg.themovie.home.domain.model.Video
import com.github.cwramirezg.themovie.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class HomeRepositoryImpl(
    private val dao: HomeDao,
    private val api: HomeApi
) : HomeRepository {
    override fun getAllVideos(): Flow<List<Video>> {
        return dao.getAllVideos().map { videos -> videos.map { it.toDomain() } }
    }

    override suspend fun getVideos(): List<Video> {
        return api.getVideos().toDomain()
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
}