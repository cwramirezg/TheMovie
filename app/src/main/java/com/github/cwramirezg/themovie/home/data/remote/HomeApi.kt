package com.github.cwramirezg.themovie.home.data.remote

import com.github.cwramirezg.themovie.home.data.remote.dto.VideoResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeApi {
    companion object {
        const val BASE_URL = "https://api.themoviedb.org/"
    }

    @GET("/3/movie/upcoming")
    suspend fun getVideos(
        @Query("page") page: Int,
        @Query("api_key") language: String = "aab779e3d79fd88afd1de96ec6fe7383"
    ): VideoResponse

}