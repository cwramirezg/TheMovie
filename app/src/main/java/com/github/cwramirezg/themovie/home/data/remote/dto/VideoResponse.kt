package com.github.cwramirezg.themovie.home.data.remote.dto

import com.squareup.moshi.Json

data class VideoResponse(
    @Json(name = "dates") val dates: Dates,
    @Json(name = "page") val page: Int,
    @Json(name = "results") val results: List<Result>,
    @Json(name = "total_pages") val total_pages: Int,
    @Json(name = "total_results") val total_results: Int
)