package com.example.androidpractice.anime_list.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite_anime")
data class AnimeDbEntity(
    @PrimaryKey val id: Int,
    val url: String,
    val imageUrl: String?,
    val title: String,
    val type: String,
    val episodes: Int,
    val status: String,
    val airedFrom: String?,
    val airedTo: String?,
    val duration: String,
    val score: Double,
    val synopsis: String,
    val genres: String
)