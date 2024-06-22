package com.github.cwramirezg.themovie.home.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.cwramirezg.themovie.home.data.local.entity.VideoEntity

@Database(entities = [VideoEntity::class], version = 1, exportSchema = false)
abstract class HomeDatabase : RoomDatabase() {
    abstract val dao: HomeDao
}