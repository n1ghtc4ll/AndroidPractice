package com.example.androidpractice.anime_list.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.androidpractice.anime_list.data.dao.AnimeDao
import com.example.androidpractice.anime_list.data.entity.AnimeDbEntity

@Database(entities = [AnimeDbEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun animeDao(): AnimeDao
}