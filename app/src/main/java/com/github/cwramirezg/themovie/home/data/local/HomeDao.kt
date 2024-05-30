package com.github.cwramirezg.themovie.home.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.github.cwramirezg.themovie.home.data.local.entity.VideoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HomeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertVideo(videoEntity: VideoEntity)

    @Query("SELECT * FROM VideoEntity WHERE id = :id LIMIT 1")
    fun getVideoById(id: String): VideoEntity

    @Query("SELECT * FROM VideoEntity")
    fun getAllVideos(): Flow<List<VideoEntity>>

    @Query("SELECT COUNT(*) FROM VideoEntity")
    fun countVideos(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertVideo(videoEntity: List<VideoEntity>)

}