package com.github.cwramirezg.themovie.home.data.remote

import com.github.cwramirezg.themovie.home.data.remote.dto.VideoResponse
import retrofit2.http.GET

interface HomeApi {
    companion object {
        const val BASE_URL = "https://api.themoviedb.org/"
    }

    @GET("/3/movie/upcoming?page=1&api_key=aab779e3d79fd88afd1de96ec6fe7383")
    suspend fun getVideos(): VideoResponse

}