package com.github.cwramirezg.themovie.home.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.github.cwramirezg.themovie.home.data.local.HomeDao
import com.github.cwramirezg.themovie.home.data.mapper.toDomain
import com.github.cwramirezg.themovie.home.data.mapper.toLocal
import com.github.cwramirezg.themovie.home.domain.model.Video
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class VideosPagingSource(
    private val dao: HomeDao,
    private val api: HomeApi,
    private val isConnected: Boolean,
    private val apiKey: String
) : PagingSource<Int, Video>() {
    override fun getRefreshKey(state: PagingState<Int, Video>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Video> {
        return try {
            if (!isConnected) {
                withContext(Dispatchers.IO) {
                    val videos = dao.getVideos().map { it.toDomain() }
                    LoadResult.Page(
                        data = videos,
                        prevKey = null,
                        nextKey = null
                    )
                }
            } else {
                val page = params.key ?: 1
                val videosDto = api.getVideos(page, apiKey)
                val videosDomain = videosDto.toDomain()
                val videosEntity = videosDto.toLocal()
                withContext(Dispatchers.IO) {
                    dao.insertVideo(videosEntity)
                }
                LoadResult.Page(
                    data = videosDomain,
                    prevKey = if (page == 1) null else page.minus(1),
                    nextKey = if (videosDomain.isEmpty()) null else page.plus(1)
                )
            }

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}