package com.example.androidpractice.core.common.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.androidpractice.anime.data.dao.AnimeDao
import com.example.androidpractice.anime.data.entity.AnimeEntity

@Database(
    entities = [AnimeEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun animeDao(): AnimeDao
}