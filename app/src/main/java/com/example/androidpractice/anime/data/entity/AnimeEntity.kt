package com.example.androidpractice.anime.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite_anime")
data class AnimeEntity(
    @PrimaryKey
    @ColumnInfo(name = "mal_id") val malId: Int,

    @ColumnInfo(name = "url") val url: String,

    @ColumnInfo(name = "image_url") val imageUrl: String?,

    @ColumnInfo(name = "title") val title: String,

    @ColumnInfo(name = "type") val type: String,

    @ColumnInfo(name = "episodes") val episodes: Int,

    @ColumnInfo(name = "status") val status: String,

    @ColumnInfo(name = "aired_from") val airedFrom: String?,

    @ColumnInfo(name = "aired_to") val airedTo: String?,

    @ColumnInfo(name = "duration") val duration: String,

    @ColumnInfo(name = "rating") val rating: String?,

    @ColumnInfo(name = "score") val score: Double,

    @ColumnInfo(name = "synopsis") val synopsis: String,

    @ColumnInfo(name = "genres") val genres: String,
)
