package com.github.cwramirezg.themovie.home.domain.model

import android.net.Uri
import timber.log.Timber

data class Video(
    val id: String = "",
    val poster: String = "",
    val nombre: String = "",
    val nota: String = "",
    val fechaLanzamiento: String = "",
    val resumen: String = ""
)

fun String.url200(): Uri {
    Timber.d("200:$this")
    return Uri.parse("https://image.tmdb.org/t/p/w200$this")
}

fun String.url500(): Uri {
    Timber.d("500:$this")
    return Uri.parse("https://image.tmdb.org/t/p/w500$this")
}