package com.github.cwramirezg.themovie.home.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VideoResponse(
    @SerialName("dates") val dates: Dates,
    @SerialName("page") val page: Int,
    @SerialName("results") val results: List<Result>,
    @SerialName("total_pages") val total_pages: Int,
    @SerialName("total_results") val total_results: Int
)