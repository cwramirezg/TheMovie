package com.github.cwramirezg.themovie.navigation

import kotlinx.serialization.Serializable

@Serializable
object Login

@Serializable
object Video

@Serializable
data class Detail(val id: String)