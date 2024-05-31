package com.github.cwramirezg.themovie.home.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Dates(
    @SerialName("maximum") val maximum: String,
    @SerialName("minimum") val minimum: String
)