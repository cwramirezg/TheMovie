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
        @Query("api_key") apiKey: String
    ): VideoResponse

}