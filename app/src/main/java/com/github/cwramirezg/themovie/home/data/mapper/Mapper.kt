package com.github.cwramirezg.themovie.home.data.mapper

import com.github.cwramirezg.themovie.home.data.local.entity.VideoEntity
import com.github.cwramirezg.themovie.home.data.remote.dto.Result
import com.github.cwramirezg.themovie.home.data.remote.dto.VideoResponse
import com.github.cwramirezg.themovie.home.domain.model.Video

fun Result.toDomain(): Video {
    return Video(
        id = id.toString(),
        poster = poster_path,
        nombre = title,
        nota = vote_average.toString(),
        fechaLanzamiento = release_date,
        resumen = overview,
    )
}

fun VideoResponse.toDomain(): List<Video> {
    return this.results.map { it.toDomain() }
}

fun Result.toLocal(): VideoEntity {
    return VideoEntity(
        id = id.toString(),
        poster = poster_path,
        nombre = title,
        nota = vote_average.toString(),
        fechaLanzamiento = release_date,
        resumen = overview,
    )
}

fun VideoResponse.toLocal(): List<VideoEntity> {
    return this.results.map { it.toLocal() }
}

fun VideoEntity.toDomain(): Video {
    return Video(
        id = id,
        poster = poster,
        nombre = nombre,
        nota = nota,
        fechaLanzamiento = fechaLanzamiento,
        resumen = resumen,
    )
}

fun Video.toLocal(): VideoEntity {
    return VideoEntity(
        id = id,
        poster = poster,
        nombre = nombre,
        nota = nota,
        fechaLanzamiento = fechaLanzamiento,
        resumen = resumen,
    )
}