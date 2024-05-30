package com.github.cwramirezg.themovie.home.domain.model

data class Video(
    val id: String,
    val poster: String,
    val nombre: String,
    val nota: String,
    val fechaLanzamiento: String,
    val resumen: String
)

fun String.url(): String {
    return "https://image.tmdb.org/t/p/w200/$this"
}