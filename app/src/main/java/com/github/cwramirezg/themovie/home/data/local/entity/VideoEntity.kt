package com.github.cwramirezg.themovie.home.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class VideoEntity (
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val poster: String,
    val nombre: String,
    val nota: String,
    val fechaLanzamiento: String,
    val resumen: String
)